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
package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.StringLocated;
import net.sourceforge.plantuml.command.regex.Matcher2;
import net.sourceforge.plantuml.command.regex.MyPattern;
import net.sourceforge.plantuml.command.regex.Pattern2;
import net.sourceforge.plantuml.core.Diagram;

public abstract class CommandMultilinesBracket<S extends Diagram> implements Command<S> {

	private final Pattern2 starting;
	
	public CommandMultilinesBracket(String patternStart) {
		if (patternStart.startsWith("(?i)^") == false || patternStart.endsWith("$") == false) {
			throw new IllegalArgumentException("Bad pattern " + patternStart);
		}
		this.starting = MyPattern.cmpile(patternStart);
	}

	protected boolean isCommandForbidden() {
		return false;
	}
	
	public String[] getDescription() {
		return new String[] { "BRACKET: " + starting.pattern() };
	}

	protected void actionIfCommandValid() {
	}

	protected final Pattern2 getStartingPattern() {
		return starting;
	}

	final public CommandControl isValid(BlocLines lines) {
		if (isCommandForbidden()) {
			return CommandControl.NOT_OK;
		}
		final Matcher2 m1 = starting.matcher(lines.getFirst().getTrimmed().getString());
		if (m1.matches() == false) {
			return CommandControl.NOT_OK;
		}
		if (lines.size() == 1) {
			return CommandControl.OK_PARTIAL;
		}

		int level = 1;
		for (StringLocated cs : lines.subExtract(1, 0)) {
			final String s = cs.getTrimmed().getString();
			if (isLineConsistent(s, level) == false) {
				return CommandControl.NOT_OK;
			}
			if (s.endsWith("{")) {
				level++;
			}
			if (s.endsWith("}")) {
				level--;
			}
			if (level < 0) {
				return CommandControl.NOT_OK;
			}
		}

		if (level != 0) {
			return CommandControl.OK_PARTIAL;
		}

		actionIfCommandValid();
		return CommandControl.OK;
	}

	protected abstract boolean isLineConsistent(String line, int level);
}
