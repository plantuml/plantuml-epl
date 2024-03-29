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
package net.sourceforge.plantuml.api;

import java.util.concurrent.atomic.AtomicBoolean;

import net.sourceforge.plantuml.log.Logme;

public final class TimeoutExecutor {

	private final long ms;

	public TimeoutExecutor(long ms) {
		this.ms = ms;
	}

	public boolean executeNow(MyRunnable task) {
		final MyThread mainThread = new MyThread(task);
		boolean done = false;
		try {
			mainThread.start();
			mainThread.join(ms);
		} catch (InterruptedException e) {
			System.err.println("TimeoutExecutorA " + e);
			Logme.error(e);
			return false;
		} finally {
			done = mainThread.done.get();
			if (done == false) {
				task.cancelJob();
				mainThread.interrupt();
			}
		}
		return done;
	}

	class MyThread extends Thread {
		private final MyRunnable task;
		private final AtomicBoolean done = new AtomicBoolean(false);

		private MyThread(MyRunnable task) {
			this.task = task;
		}

		@Override
		public void run() {
			try {
				task.runJob();
				done.set(true);
			} catch (InterruptedException e) {
				System.err.println("TimeoutExecutorB " + e);
				Logme.error(e);
			}
		}

	}
}
