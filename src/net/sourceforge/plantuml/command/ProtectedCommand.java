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
package net.sourceforge.plantuml.command;

import java.util.Objects;

import net.sourceforge.plantuml.Log;
import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.version.Version;

public class ProtectedCommand<S extends Diagram> implements Command<S> {

	private final Command<S> cmd;

	public ProtectedCommand(Command<S> cmd) {
		this.cmd = Objects.requireNonNull(cmd);
	}

	public CommandExecutionResult execute(S system, BlocLines lines) {
		try {
			final CommandExecutionResult result = cmd.execute(system, lines);
			// if (result.isOk()) {
			// // TRACECOMMAND
			// System.err.println("CMD = " + cmd.getClass());
			// }
			return result;
		} catch (Throwable t) {
			Log.error("Error " + t);
			Logme.error(t);
			String msg = "You should send a mail to plantuml@gmail.com or post to http://plantuml.com/qa with this log (V"
					+ Version.versionString() + ")";
			Log.error(msg);
			msg += " " + t.toString();
			return CommandExecutionResult.error(msg, t);
		}
	}

	public CommandControl isValid(BlocLines lines) {
		return cmd.isValid(lines);
	}

	public String[] getDescription() {
		return cmd.getDescription();
	}

}
