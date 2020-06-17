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
package net.sourceforge.plantuml.hector;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Skeleton {

	private final List<Pin> pins;
	private final List<PinLink> pinLinks;
	private final SortedSet<Integer> rows = new TreeSet<Integer>();

	public Skeleton(List<Pin> pins, List<PinLink> pinLinks) {
		this.pins = pins;
		this.pinLinks = pinLinks;
		int uid = 0;
		for (Pin pin : pins) {
			pin.setUid(uid++);
			rows.add(pin.getRow());
		}
	}

	public SortedSet<Integer> getRows() {
		return rows;
	}

	public List<Pin> getPins() {
		return pins;
	}

	public Collection<Pin> getPinsOfRow(int row) {
		final Set<Pin> result = new LinkedHashSet<Pin>();
		for (Pin pin : pins) {
			if (pin.getRow() == row) {
				result.add(pin);
			}
		}
		return result;
	}
	
	public List<PinLink> getPinLinks() {
		return pinLinks;
	}



}
