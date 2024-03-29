/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2023, Arnaud Roques
 *
 * Project Info:  https://plantuml.com
 * 
 * If you like this project or if you find it useful, you can support us at:
 * 
 * https://plantuml.com/patreon (only 1$ per month!)
 * https://plantuml.com/paypal
 * 
 * This file is part of PlantUML.
 *
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC
 * LICENSE ("AGREEMENT"). [Eclipse Public License - v 1.0]
 * 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES
 * RECIPIENT'S ACCEPTANCE OF THIS AGREEMENT.
 * 
 * You may obtain a copy of the License at
 * 
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 *
 * Original Author:  Arnaud Roques
 */
package net.sourceforge.plantuml;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class ProgressBar {

	private static final java.util.logging.Logger logger;

	static {
		logger = java.util.logging.Logger.getLogger("com.plantuml.ProgressBar");
		logger.setUseParentHandlers(false);
		logger.addHandler(new StdErrHandler());
	}

	private static boolean enable;
	private static String last = null;
	private static final AtomicInteger total = new AtomicInteger();
	private static final AtomicInteger done = new AtomicInteger();

	private synchronized static void print(String message) {
		logger.log(Level.INFO, buildClearMessage() + message);
		last = message;
	}

	public synchronized static void clear() {
		logger.log(Level.INFO, buildClearMessage());
		last = null;
	}

	private static String buildClearMessage() {
		if (last != null) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < last.length(); i++) {
				sb.append("\b");
			}
			for (int i = 0; i < last.length(); i++) {
				sb.append(" ");
			}
			for (int i = 0; i < last.length(); i++) {
				sb.append("\b");
			}
			return sb.toString();
		}
		return "";
	}

	public static void incTotal(int nb) {
		total.addAndGet(nb);
		printBar(done.intValue(), total.intValue());
	}

	private synchronized static void printBar(int done, int total) {
		if (enable == false) {
			return;
		}
		if (total == 0) {
			return;
		}
		print("[" + getBar(done, total) + "] " + done + "/" + total);
	}

	private static String getBar(int done, int total) {
		final StringBuilder sb = new StringBuilder();
		final int width = 30;
		final int value = width * done / total;
		for (int i = 0; i < width; i++) {
			sb.append(i < value ? '#' : ' ');
		}
		return sb.toString();
	}

	public static void incDone(boolean error) {
		done.incrementAndGet();
		printBar(done.intValue(), total.intValue());
	}

	public static void setEnable(boolean value) {
		enable = value;
	}

	private static class StdErrHandler extends Handler {
		public StdErrHandler() {
		}

		@Override
		public void publish(LogRecord record) {
			String message = record.getMessage();
			System.err.print(message);
			this.flush();
		}

		@Override
		public void flush() {
			System.err.flush();
		}

		/**
		 * Override {@code StreamHandler.close} to do a flush but not to close the output stream.
		 * That is, we do <b>not</b> close {@code System.err}.
		 */
		@Override
		public void close() {
			flush();
		}
	}
}
