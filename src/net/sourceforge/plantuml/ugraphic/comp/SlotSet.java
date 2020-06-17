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
package net.sourceforge.plantuml.ugraphic.comp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.URectangle;
import net.sourceforge.plantuml.ugraphic.UTranslate;

public class SlotSet implements Iterable<Slot> {

	private final List<Slot> all = new ArrayList<Slot>();

	public SlotSet filter(double start, double end) {
		final SlotSet result = new SlotSet();
		for (Slot slot : all) {
			final Slot intersec = slot.intersect(start, end);
			if (intersec != null) {
				result.all.add(intersec);
			}
		}
		return result;
	}

	public void addAll(SlotSet other) {
		this.all.addAll(other.all);
	}

	public void addSlot(double start, double end) {
		final List<Slot> collisions = new ArrayList<Slot>();
		Slot newSlot = new Slot(start, end);
		for (final Iterator<Slot> it = all.iterator(); it.hasNext();) {
			final Slot s = it.next();
			if (s.intersect(newSlot)) {
				it.remove();
				collisions.add(s);
			}
		}
		for (Slot s : collisions) {
			newSlot = newSlot.merge(s);
		}
		all.add(newSlot);
	}

	public SlotSet smaller(double margin) {
		final SlotSet result = new SlotSet();
		for (Slot sl : all) {
			if (sl.size() <= 2 * margin) {
				continue;
			}
			result.addSlot(sl.getStart() + margin, sl.getEnd() - margin);
		}
		return result;
	}

	@Override
	public String toString() {
		return all.toString();
	}

	public List<Slot> getSlots() {
		return Collections.unmodifiableList(all);
	}

	public Iterator<Slot> iterator() {
		return getSlots().iterator();
	}

	public SlotSet reverse() {
		final SlotSet result = new SlotSet();
		Collections.sort(all);
		Slot last = null;
		for (Slot slot : all) {
			if (last != null) {
				result.addSlot(last.getEnd(), slot.getStart());
			}
			last = slot;
		}
		return result;
	}

	public void drawDebugX(UGraphic ug, double size) {
		for (Slot slot : all) {
			final URectangle rect = new URectangle(slot.getEnd() - slot.getStart(), size);
			ug.apply(UTranslate.dx(slot.getStart())).draw(rect);
		}
	}

}
