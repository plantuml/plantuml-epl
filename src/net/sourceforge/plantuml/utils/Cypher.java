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
package net.sourceforge.plantuml.utils;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cypher {

	final private static Pattern p = Pattern.compile("[\\p{L}\\p{N}]+");

	private final SecureRandom rnd = new SecureRandom();
	private final Map<String, String> convert = new HashMap<String, String>();
	private final Set<String> except = new HashSet<String>();

	public synchronized String cypher(String s) {

		final Matcher m = p.matcher(s);
		final StringBuffer sb = new StringBuffer();
		while (m.find()) {
			final String word = m.group(0);
			m.appendReplacement(sb, changeWord(word));
		}
		m.appendTail(sb);

		return sb.toString();
	}

	private String changeWord(final String word) {
		final String lower = word.toLowerCase();
		if (except.contains(lower) || lower.matches("^([a-f0-9]{3}|[a-f0-9]{6})$")) {
			return word;
		}
		String res = convert.get(word);
		if (res != null) {
			return res;
		}
		int len = word.length();
		if (len < 4) {
			len = 4;
		}
		while (true) {
			final StringBuilder sb = new StringBuilder();
			for (int i = 0; i < len; i++) {
				final char letter = (char) ('a' + rnd.nextInt(26));
				sb.append(letter);
			}
			res = sb.toString();
			if (convert.containsValue(res) == false) {
				convert.put(word, res);
				return res;
			}
		}
	}

	public void addException(String word) {
		except.add(word.toLowerCase());
	}

}
