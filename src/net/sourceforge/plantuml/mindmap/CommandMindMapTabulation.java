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
package net.sourceforge.plantuml.mindmap;

import net.sourceforge.plantuml.LineLocation;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.command.regex.IRegex;
import net.sourceforge.plantuml.command.regex.RegexConcat;
import net.sourceforge.plantuml.command.regex.RegexLeaf;
import net.sourceforge.plantuml.command.regex.RegexOptional;
import net.sourceforge.plantuml.command.regex.RegexResult;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.ugraphic.color.HColor;

public class CommandMindMapTabulation extends SingleLineCommand2<MindMapDiagram> {

	public CommandMindMapTabulation() {
		super(false, getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandMindMapTabulation.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("TYPE", "([ \t]*[*])"), //
				new RegexOptional(new RegexLeaf("BACKCOLOR", "\\[(#\\w+)\\]")), //
				new RegexLeaf("SHAPE", "(_)?"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("LABEL", "([^%s].*)"), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(MindMapDiagram diagram, LineLocation location, RegexResult arg) {
		final String type = arg.get("TYPE", 0);
		final String label = arg.get("LABEL", 0);
		final String stringColor = arg.get("BACKCOLOR", 0);
		HColor backColor = null;
		if (stringColor != null) {
			backColor = diagram.getSkinParam().getIHtmlColorSet().getColorIfValid(stringColor);
		}
		return diagram.addIdea(backColor, type.length() - 1, Display.getWithNewlines(label),
				IdeaShape.fromDesc(arg.get("SHAPE", 0)));
	}

}
