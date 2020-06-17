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
package net.sourceforge.plantuml.activitydiagram.command;

import net.sourceforge.plantuml.LineLocation;
import net.sourceforge.plantuml.activitydiagram.ActivityDiagram;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.command.regex.IRegex;
import net.sourceforge.plantuml.command.regex.RegexConcat;
import net.sourceforge.plantuml.command.regex.RegexLeaf;
import net.sourceforge.plantuml.command.regex.RegexResult;
import net.sourceforge.plantuml.cucadiagram.EntityUtils;

public class CommandInnerConcurrent extends SingleLineCommand2<ActivityDiagram> {

	public CommandInnerConcurrent() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandInnerConcurrent.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("--"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("NAME", "(.*)"), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(ActivityDiagram diagram, LineLocation location, RegexResult arg) {
		if (EntityUtils.groupRoot(diagram.getCurrentGroup())) {
			return CommandExecutionResult.error("No inner activity");
		}
		diagram.concurrentActivity(arg.get("NAME", 0));

		return CommandExecutionResult.ok();
	}

}
