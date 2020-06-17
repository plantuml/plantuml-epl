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
package net.sourceforge.plantuml.cute;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class CrossingSimple {

	// http://mathworld.wolfram.com/Circle-LineIntersection.html

	private final double radius;
	private final InfiniteLine line;

	public CrossingSimple(double radius, InfiniteLine line) {
		this.radius = radius;
		this.line = line;
	}

	private double pow2(double x) {
		return x * x;
	}

	private double sgn(double x) {
		if (x < 0) {
			return -1;
		}
		return 1;
	}

	public List<Point2D> intersection() {
		final List<Point2D> result = new ArrayList<Point2D>();
		final double delta = pow2(radius * line.getDr()) - pow2(line.getDiscriminant());

		if (delta < 0) {
			return result;
		}

		double x;
		double y;

		x = (line.getDiscriminant() * line.getDeltaY() + sgn(line.getDeltaY()) * line.getDeltaX() * Math.sqrt(delta))
				/ pow2(line.getDr());
		y = (-line.getDiscriminant() * line.getDeltaX() + Math.abs(line.getDeltaY()) * Math.sqrt(delta))
				/ pow2(line.getDr());
		result.add(new Point2D.Double(x, y));

		x = (line.getDiscriminant() * line.getDeltaY() - sgn(line.getDeltaY()) * line.getDeltaX() * Math.sqrt(delta))
				/ pow2(line.getDr());
		y = (-line.getDiscriminant() * line.getDeltaX() - Math.abs(line.getDeltaY()) * Math.sqrt(delta))
				/ pow2(line.getDr());
		result.add(new Point2D.Double(x, y));

		return result;
	}

}
