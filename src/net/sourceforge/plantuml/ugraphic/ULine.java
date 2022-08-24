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
package net.sourceforge.plantuml.ugraphic;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class ULine extends AbstractShadowable implements UShapeSized {

	private final double dx;
	private final double dy;

	public ULine(Point2D p1, Point2D p2) {
		this(p2.getX() - p1.getX(), p2.getY() - p1.getY());
	}

	public ULine(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public ULine rotate(double theta) {
		if (theta == 0)
			return this;
		final AffineTransform rot = AffineTransform.getRotateInstance(theta);
		final Point2D result = rot.transform(new Point2D.Double(dx, dy), null);
		return new ULine(result.getX(), result.getY());
	}

	public static ULine hline(double dx) {
		return new ULine(dx, 0);
	}

	public static ULine vline(double dy) {
		return new ULine(0, dy);
	}

	@Override
	public String toString() {
		return "ULine dx=" + dx + " dy=" + dy;
	}

	public double getDX() {
		return dx;
	}

	public double getDY() {
		return dy;
	}

	public double getLength() {
		return Math.sqrt(dx * dx + dy * dy);
	}

	public double getWidth() {
		return dx;
	}

	public double getHeight() {
		return dy;
	}

}
