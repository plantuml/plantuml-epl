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
package net.sourceforge.plantuml.geom.kinetic;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class Quadrant {

	static final private int SIZE = 100;

	private final int x;
	private final int y;

	public Quadrant(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Quadrant(Point2DCharge pt) {
		this((int) pt.getX() / SIZE, (int) pt.getY() / SIZE);
	}

	@Override
	public boolean equals(Object obj) {
		final Quadrant other = (Quadrant) obj;
		return x == other.x && y == other.y;
	}

	@Override
	public int hashCode() {
		return x * 3571 + y;
	}

	@Override
	public String toString() {
		return "" + x + "-" + y;
	}

	public Collection<Quadrant> neighbourhood() {
		final Collection<Quadrant> result = Arrays.asList(new Quadrant(x - 1, y - 1), new Quadrant(x, y - 1),
				new Quadrant(x + 1, y - 1), new Quadrant(x - 1, y), this, new Quadrant(x + 1, y), new Quadrant(x - 1,
						y + 1), new Quadrant(x, y + 1), new Quadrant(x + 1, y + 1));
		assert new HashSet<Quadrant>(result).size() == 9;
		return result;
	}

}
