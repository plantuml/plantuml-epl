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

import java.util.Set;
import java.util.TreeSet;

public class ExpandTransform implements PiecewiseAffineTransform {

	private final Set<Expand> all = new TreeSet<Expand>();

	@Override
	public String toString() {
		return all.toString();
	}

	public void addExpandIncludingLimit(double position, double extend) {
		this.all.add(new Expand(ExpandType.INCLUDING_LIMIT, position, extend));
	}

	public void addExpandExcludingLimit(double position, double extend) {
		this.all.add(new Expand(ExpandType.EXCLUDING_LIMIT, position, extend));
	}

	public double transform(final double init) {
		double result = init;
		for (Expand expand : all) {
			if (ExpandType.INCLUDING_LIMIT == expand.getType() && init >= expand.getPosition()) {
				result += expand.getExtend();
			}
			if (ExpandType.EXCLUDING_LIMIT == expand.getType() && init > expand.getPosition()) {
				result += expand.getExtend();
			}
		}
		return result;
	}

}
