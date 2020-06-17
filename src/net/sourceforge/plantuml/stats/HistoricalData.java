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
package net.sourceforge.plantuml.stats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import net.sourceforge.plantuml.version.Version;

public class HistoricalData {

	final private Preferences prefs;
	private ParsedGenerated current;
	final private List<ParsedGenerated> historical = new ArrayList<ParsedGenerated>();

	HistoricalData(Preferences prefs) {
		this.prefs = prefs;
		try {
			historical.addAll(reload());
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		Collections.sort(historical, getIdComparator());
	}

	public void reset() {
		char currentCode = (char) ('A' + historical.size());
		if (historical.size() > 7) {
			final ParsedGenerated last = historical.get(0);
			final String lastName = last.parsed().getName();
			currentCode = lastName.charAt("histo.".length());
		}
		this.current = ParsedGenerated.loadDated(prefs, "histo." + currentCode);
		this.current.reset();
		final long maxId = getMaxId();
		this.current.parsedDated().setComment(Long.toString(maxId + 1, 36) + "/" + Version.versionString());
	}

	private long getMaxId() {
		long v = 0;
		for (ParsedGenerated histo : historical) {
			v = Math.max(v, histo.getId());
		}
		return v;
	}

	private Comparator<? super ParsedGenerated> getIdComparator() {
		return new Comparator<ParsedGenerated>() {
			public int compare(ParsedGenerated v1, ParsedGenerated v2) {
				final long time1 = v1.getId();
				final long time2 = v2.getId();
				if (time1 > time2) {
					return 1;
				}
				if (time1 < time2) {
					return -1;
				}
				return 0;
			}
		};
	}

	private List<ParsedGenerated> reload() throws BackingStoreException {
		final List<ParsedGenerated> result = new ArrayList<ParsedGenerated>();
		final int length = "histo.".length();
		for (String key : prefs.keys()) {
			if (key.startsWith("histo.") && key.endsWith(".p.saved")) {
				final String name = key.substring(length, length + 1);
				final ParsedGenerated load = ParsedGenerated.loadDated(prefs, "histo." + name);
				result.add(load);
			}
		}
		return result;
	}

	public ParsedGenerated current() {
		return current;
	}

	public List<ParsedGenerated> getHistorical() {
		return Collections.unmodifiableList(historical);
	}

}
