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
package net.sourceforge.plantuml.timingdiagram.command;

import net.sourceforge.plantuml.LineLocation;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.command.regex.IRegex;
import net.sourceforge.plantuml.command.regex.RegexConcat;
import net.sourceforge.plantuml.command.regex.RegexLeaf;
import net.sourceforge.plantuml.command.regex.RegexOptional;
import net.sourceforge.plantuml.command.regex.RegexResult;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.color.ColorParser;
import net.sourceforge.plantuml.graphic.color.ColorType;
import net.sourceforge.plantuml.graphic.color.Colors;
import net.sourceforge.plantuml.timingdiagram.TimeTick;
import net.sourceforge.plantuml.timingdiagram.TimingDiagram;

public class CommandHighlight extends SingleLineCommand2<TimingDiagram> {

	public CommandHighlight() {
		super(getRegexConcat());
	}

	private static ColorParser color() {
		return ColorParser.simpleColor(ColorType.BACK);
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandHighlight.class.getName(), RegexLeaf.start(), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("highlight"), //
				RegexLeaf.spaceOneOrMore(), //
				TimeTickBuilder.expressionAtWithoutArobase("FROM"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("to"), //
				RegexLeaf.spaceOneOrMore(), //
				TimeTickBuilder.expressionAtWithoutArobase("TO"), //
				RegexLeaf.spaceZeroOrMore(), //
				color().getRegex(), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexOptional( //
						new RegexConcat( //
								new RegexLeaf(":"), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("CAPTION", "(.*)") //
						)), //
				RegexLeaf.end());
	}

	@Override
	final protected CommandExecutionResult executeArg(TimingDiagram diagram, LineLocation location, RegexResult arg) {
		final TimeTick tickFrom = TimeTickBuilder.parseTimeTick("FROM", arg, diagram);
		final TimeTick tickTo = TimeTickBuilder.parseTimeTick("TO", arg, diagram);
		final Display display = Display.getWithNewlines(arg.get("CAPTION", 0));
		final Colors colors = color().getColor(arg, diagram.getSkinParam().getIHtmlColorSet());
		return diagram.highlight(tickFrom, tickTo, display, colors);
	}

}
