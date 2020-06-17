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

import java.util.Comparator;

public class SkeletonConfigurationComparator implements Comparator<SkeletonConfiguration> {

	private final SkeletonConfigurationEvaluator evaluator;

	public SkeletonConfigurationComparator(SkeletonConfigurationEvaluator evaluator) {
		this.evaluator = evaluator;
	}

	public int compare(SkeletonConfiguration sc1, SkeletonConfiguration sc2) {
		final double price1 = evaluator.getPrice(sc1);
		final double price2 = evaluator.getPrice(sc2);
		if (price1 > price2) {
			return 1;
		}
		if (price1 < price2) {
			return -1;
		}
		return 0;
	}

}
