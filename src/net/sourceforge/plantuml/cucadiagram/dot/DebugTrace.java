/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2020, Arnaud Roques
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
package net.sourceforge.plantuml.cucadiagram.dot;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import net.sourceforge.plantuml.security.SFile;

public class DebugTrace {

	private static final SFile out = new SFile("debug" + System.currentTimeMillis() + ".txt");

	private static PrintWriter pw;

	private synchronized static PrintWriter getPrintWriter() {
		if (pw == null) {
			try {
				pw = out.createPrintWriter();
			} catch (FileNotFoundException e) {

			}
		}
		return pw;
	}

	public synchronized static void DEBUG(String s) {
		final PrintWriter pw = getPrintWriter();
		pw.println(s);
		pw.flush();
	}

	public synchronized static void DEBUG(String s, Throwable t) {
		DEBUG(s);
		t.printStackTrace(pw);
		pw.flush();
	}

}
