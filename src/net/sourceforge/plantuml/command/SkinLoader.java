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

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.StringLocated;
import net.sourceforge.plantuml.UmlDiagram;
import net.sourceforge.plantuml.command.regex.Matcher2;
import net.sourceforge.plantuml.command.regex.MyPattern;
import net.sourceforge.plantuml.command.regex.Pattern2;

public class SkinLoader {

	public final static Pattern2 p1 = MyPattern
			.cmpile("^([\\w.]*(?:\\<\\<.*\\>\\>)?[\\w.]*)[%s]+(?:(\\{)|(.*))$|^\\}?$");

	final private List<String> context = new ArrayList<String>();
	final private UmlDiagram diagram;

	public SkinLoader(UmlDiagram diagram) {
		this.diagram = diagram;
	}

	private void push(String s) {
		context.add(s);
	}

	private void pop() {
		context.remove(context.size() - 1);
	}

	private String getFullParam() {
		final StringBuilder sb = new StringBuilder();
		for (String s : context) {
			sb.append(s);
		}
		return sb.toString();
	}

	public CommandExecutionResult execute(BlocLines lines, final String group1) {

		if (group1 != null) {
			this.push(group1);
		}

		lines = lines.subExtract(1, 1);
		lines = lines.trim().removeEmptyLines();

		for (StringLocated s : lines) {
			assert s.getString().length() > 0;

			if (s.getString().equals("}")) {
				this.pop();
				continue;
			}
			final Matcher2 m = p1.matcher(s.getString());
			if (m.find() == false) {
				throw new IllegalStateException();
			}
			if (m.group(2) != null) {
				this.push(m.group(1));
			} else if (m.group(3) != null) {
				final String key = this.getFullParam() + m.group(1);
				diagram.setParam(key, m.group(3));
			} else {
				throw new IllegalStateException("." + s.getString() + ".");
			}
		}

		return CommandExecutionResult.ok();
	}

}
