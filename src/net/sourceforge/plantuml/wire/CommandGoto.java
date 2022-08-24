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
package net.sourceforge.plantuml.wire;

import net.sourceforge.plantuml.LineLocation;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.command.regex.IRegex;
import net.sourceforge.plantuml.command.regex.RegexConcat;
import net.sourceforge.plantuml.command.regex.RegexLeaf;
import net.sourceforge.plantuml.command.regex.RegexResult;

public class CommandGoto extends SingleLineCommand2<WireDiagram> {

	public CommandGoto() {
		super(false, getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandGoto.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("INDENT", "([\\s\\t]*)"), //
				new RegexLeaf("goto"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("\\("), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("X", "(\\d+)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf(","), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("Y", "(\\d+)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("\\)"), //
				RegexLeaf.spaceZeroOrMore(), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(WireDiagram diagram, LineLocation location, RegexResult arg) {
		final String indent = arg.get("INDENT", 0);
		final double x = Double.parseDouble(arg.get("X", 0));
		final double y = Double.parseDouble(arg.get("Y", 0));
		return diagram.wgoto(indent, x, y);
	}

}
