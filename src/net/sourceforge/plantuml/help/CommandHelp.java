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
package net.sourceforge.plantuml.help;

import net.sourceforge.plantuml.LineLocation;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.command.regex.IRegex;
import net.sourceforge.plantuml.command.regex.RegexConcat;
import net.sourceforge.plantuml.command.regex.RegexLeaf;
import net.sourceforge.plantuml.command.regex.RegexResult;

public class CommandHelp extends SingleLineCommand2<Help> {

	public CommandHelp() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandHelp.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("help"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(Help diagram, LineLocation location, RegexResult arg) {
		diagram.add("<b>General help");
		diagram.add(" ");
		diagram.add("The code of this command is located in <i>net.sourceforge.plantuml.help</i> package.");
		diagram.add("You may improve it on <i>https://github.com/plantuml/plantuml/tree/master/src/net/sourceforge/plantuml/help</i>");
		diagram.add(" ");
		diagram.add(" There are some other help command:");
		diagram.add("* help types");
		diagram.add("* help keywords");
		diagram.add("* help preprocessors");
		diagram.add("* help colors");
		diagram.add("* help font");
		diagram.add("* help skinparams");

		return CommandExecutionResult.ok();
	}
}
