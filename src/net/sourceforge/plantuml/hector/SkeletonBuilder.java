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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SkeletonBuilder {

	private List<PinLinksContinuousSet> sets = new ArrayList<PinLinksContinuousSet>();

	public void add(PinLink pinLink) {
		addInternal(pinLink);
		merge();

	}

	private void merge() {
		for (int i = 0; i < sets.size() - 1; i++) {
			for (int j = i + 1; j < sets.size(); j++) {
				if (sets.get(i).doesTouch(sets.get(j))) {
					sets.get(i).addAll(sets.get(j));
					sets.remove(j);
					return;
				}
			}
		}
	}

	private void addInternal(PinLink pinLink) {
		for (PinLinksContinuousSet set : sets) {
			if (set.doesTouch(pinLink)) {
				set.add(pinLink);
				return;
			}
		}
		final PinLinksContinuousSet newSet = new PinLinksContinuousSet();
		newSet.add(pinLink);
		sets.add(newSet);
	}

	public List<Skeleton> createSkeletons() {
		final List<Skeleton> result = new ArrayList<Skeleton>();

		for (PinLinksContinuousSet set : sets) {
			result.add(set.createSkeleton());
		}

		return Collections.unmodifiableList(result);
	}
}
