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

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class SegmentCutter {

	private final List<Point2D> intermediates = new ArrayList<Point2D>();

	public SegmentCutter(Point2D start, Point2D end, double maxDistance) {
		final double d = end.distance(start);
		if (d <= maxDistance) {
			intermediates.add(end);
			return;
		}
		int nb = 2;
		while (d / nb > maxDistance) {
			nb++;
		}
		final double deltaX = end.getX() - start.getX();
		final double deltaY = end.getY() - start.getY();
		for (int i = 1; i < nb; i++) {
			intermediates.add(new Point2D.Double(start.getX() + i * deltaX / nb, start.getY() + i * deltaY / nb));
		}
		intermediates.add(end);
	}

	public List<Point2D> intermediates() {
		return Collections.unmodifiableList(intermediates);
	}

}
