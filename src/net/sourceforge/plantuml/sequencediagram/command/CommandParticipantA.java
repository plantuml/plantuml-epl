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
package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.UrlBuilder;
import net.sourceforge.plantuml.command.regex.IRegex;
import net.sourceforge.plantuml.command.regex.RegexConcat;
import net.sourceforge.plantuml.command.regex.RegexLeaf;
import net.sourceforge.plantuml.command.regex.RegexOptional;
import net.sourceforge.plantuml.graphic.color.ColorParser;

public class CommandParticipantA extends CommandParticipant {

	public CommandParticipantA() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandParticipantA.class.getName(), RegexLeaf.start(), //
				getRegexType(), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexOptional( //
						new RegexConcat( //
								new RegexLeaf("FULL", "[%g]([^%g]+)[%g]"), //
								RegexLeaf.spaceOneOrMore(), //
								new RegexLeaf("as"), //
								RegexLeaf.spaceOneOrMore() //
						)), //
				new RegexLeaf("CODE", "([\\p{L}0-9_.@]+)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("STEREO", "(\\<\\<.*\\>\\>)?"), //
				RegexLeaf.spaceZeroOrMore(), //
				getOrderRegex(), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("URL", "(" + UrlBuilder.getRegexp() + ")?"), //
				RegexLeaf.spaceZeroOrMore(), //
				ColorParser.exp1(), RegexLeaf.end());
	}
}
