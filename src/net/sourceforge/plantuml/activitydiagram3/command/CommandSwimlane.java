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
package net.sourceforge.plantuml.activitydiagram3.command;

import net.sourceforge.plantuml.LineLocation;
import net.sourceforge.plantuml.activitydiagram3.ActivityDiagram3;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.command.regex.IRegex;
import net.sourceforge.plantuml.command.regex.RegexConcat;
import net.sourceforge.plantuml.command.regex.RegexLeaf;
import net.sourceforge.plantuml.command.regex.RegexResult;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.color.ColorParser;
import net.sourceforge.plantuml.ugraphic.color.HColor;

public class CommandSwimlane extends SingleLineCommand2<ActivityDiagram3> {

	public CommandSwimlane() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandSwimlane.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("\\|"), //
				ColorParser.exp6(), //
				new RegexLeaf("SWIMLANE", "([^|]+)"), //
				new RegexLeaf("\\|"), //
				new RegexLeaf("LABEL", "([^|]+)?"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(ActivityDiagram3 diagram, LineLocation location, RegexResult arg) {
		final HColor color = diagram.getSkinParam().getIHtmlColorSet().getColorIfValid(arg.get("COLOR", 0));
		final String name = arg.get("SWIMLANE", 0);
		final Display label = Display.getWithNewlines(arg.get("LABEL", 0));
		return diagram.swimlane(name, color, label);
	}

}
