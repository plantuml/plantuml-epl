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
package net.sourceforge.plantuml.command.regex;

import java.util.Collections;
import java.util.Map;

public class RegexResult {

	private final Map<String, RegexPartialMatch> data;

	public RegexResult(Map<String, RegexPartialMatch> data) {
		this.data = Collections.unmodifiableMap(data);
	}

	@Override
	public String toString() {
		return data.toString();
	}

	public RegexPartialMatch get(String key) {
		return data.get(key);
	}

	public String get(String key, int num) {
		final RegexPartialMatch reg = data.get(key);
		if (reg == null) {
			return null;
		}
		return reg.get(num);
	}

	public String getLazzy(String key, int num) {
		for (Map.Entry<String, RegexPartialMatch> ent : data.entrySet()) {
			if (ent.getKey().startsWith(key) == false) {
				continue;
			}
			final RegexPartialMatch match = ent.getValue();
			if (num >= match.size()) {
				continue;
			}
			if (match.get(num) != null) {
				return ent.getValue().get(num);
			}
		}
		return null;
	}

	public int size() {
		return data.size();
	}

}
