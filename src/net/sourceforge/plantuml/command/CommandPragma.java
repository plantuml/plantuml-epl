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

import net.sourceforge.plantuml.LineLocation;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.UmlDiagram;
import net.sourceforge.plantuml.command.regex.IRegex;
import net.sourceforge.plantuml.command.regex.RegexConcat;
import net.sourceforge.plantuml.command.regex.RegexLeaf;
import net.sourceforge.plantuml.command.regex.RegexOptional;
import net.sourceforge.plantuml.command.regex.RegexResult;
import net.sourceforge.plantuml.cucadiagram.dot.GraphvizUtils;

public class CommandPragma extends SingleLineCommand2<UmlDiagram> {

	public CommandPragma() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandPragma.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("!pragma"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("NAME", "([A-Za-z_][A-Za-z_0-9]*)"), //
				new RegexOptional( //
						new RegexConcat( //
								RegexLeaf.spaceOneOrMore(), //
								new RegexLeaf("VALUE", "(.*)") //
						)), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(UmlDiagram system, LineLocation location, RegexResult arg) {
		final String name = StringUtils.goLowerCase(arg.get("NAME", 0));
		final String value = arg.get("VALUE", 0);
		system.getPragma().define(name, value);
		if (name.equalsIgnoreCase("graphviz_dot") && value.equalsIgnoreCase("jdot")) {
			system.setUseJDot(true);
		}
		if (name.equalsIgnoreCase("graphviz_dot") && value.equalsIgnoreCase(GraphvizUtils.VIZJS)) {
			system.getSkinParam().setUseVizJs(true);
		}
		return CommandExecutionResult.ok();
	}

}
