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
package net.sourceforge.plantuml.tim.iterator;

import java.util.List;

import net.sourceforge.plantuml.StringLocated;
import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;
import net.sourceforge.plantuml.tim.TLineType;

public class CodeIteratorLongComment extends AbstractCodeIterator {

	private final List<StringLocated> logs;

	public CodeIteratorLongComment(CodeIterator source, List<StringLocated> logs) {
		super(source);
		this.logs = logs;
	}

	public StringLocated peek() throws EaterException, EaterExceptionLocated {
		while (true) {
			if (source.peek() == null) {
				return null;
			}
			if (source.peek().getType() != TLineType.COMMENT_LONG_START) {
				return source.peek();
			}
			StringLocated s = null;
			while ((s = source.peek()) != null && s.getTrimmed().getString().endsWith("'/") == false) {
				logs.add(s);
				source.next();
			}
			assert source.peek() == null || s.getTrimmed().getString().endsWith("'/");
			if (source.peek() != null) {
				assert s.getTrimmed().getString().endsWith("'/");
				logs.add(source.peek());
				source.next();
			}
		}

	}
}
