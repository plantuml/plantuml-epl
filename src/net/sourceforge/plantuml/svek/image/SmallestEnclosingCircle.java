/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2023, Arnaud Roques
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
package net.sourceforge.plantuml.svek.image;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class SmallestEnclosingCircle {

	private final List<Point2D> all = new ArrayList<>();
	private Circle lastSolution;

	public void append(Point2D pt) {
		if (all.contains(pt) == false) {
			all.add(pt);
		}
		this.lastSolution = null;
	}

	public Circle getCircle() {
		if (lastSolution == null) {
			lastSolution = findSec(all.size(), all, 0, new ArrayList<>(all));
		}
		return lastSolution;
	}

	private Circle findSec(int n, List<Point2D> p, int m, List<Point2D> b) {
		Circle sec = new Circle();

		// Compute the Smallest Enclosing Circle defined by B
		if (m == 1) {
			sec = new Circle(b.get(0));
		} else if (m == 2) {
			sec = new Circle(b.get(0), b.get(1));
		} else if (m == 3) {
			return Circle.getCircle(b.get(0), b.get(1), b.get(2));
		}

		// Check if all the points in p are enclosed
		for (int i = 0; i < n; i++) {
			if (sec.isOutside(p.get(i))) {
				// Compute B <--- B union P[i].
				b.set(m, p.get(i));
				// Recurse
				sec = findSec(i, p, m + 1, b);
			}
		}

		return sec;
	}

}
