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
package net.sourceforge.plantuml.cucadiagram;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

class Election {

	private final Map<String, Member> all = new HashMap<String, Member>();

	public void addCandidate(String display, Member candidate) {
		all.put(display, candidate);

	}

	private Member getCandidate(String shortName) {
		List<Member> list = getAllCandidateContains(shortName);
		if (list.size() == 1) {
			return list.get(0);
		}
		list = getAllCandidateContainsStrict(shortName);
		if (list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	private List<Member> getAllCandidateContains(String shortName) {
		final List<Member> result = new ArrayList<Member>();
		for (Map.Entry<String, Member> ent : all.entrySet()) {
			if (ent.getKey().contains(shortName)) {
				result.add(ent.getValue());
			}
		}
		return result;
	}

	private List<Member> getAllCandidateContainsStrict(String shortName) {
		final List<Member> result = new ArrayList<Member>();
		for (Map.Entry<String, Member> ent : all.entrySet()) {
			final String key = ent.getKey();
			if (key.matches(".*\\b" + shortName + "\\b.*")) {
				result.add(ent.getValue());
			}
		}
		return result;
	}

	public Map<Member, String> getAllElected(Collection<String> shortNames) {
		final Map<Member, String> memberWithPort = new HashMap<Member, String>();
		for (String shortName : new HashSet<String>(shortNames)) {
			final Member m = getCandidate(shortName);
			if (m != null) {
				memberWithPort.put(m, shortName);
				shortNames.remove(shortName);
			}
		}
		return Collections.unmodifiableMap(memberWithPort);
	}

}
