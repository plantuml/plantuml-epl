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
package net.sourceforge.plantuml.svek.extremity;

import java.awt.geom.Point2D;

import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.ULine;
import net.sourceforge.plantuml.ugraphic.UTranslate;
import net.sourceforge.plantuml.ugraphic.color.HColorUtils;

class ExtremityPlus extends Extremity {

	private final UEllipse circle;
	private final double px;
	private final double py;
	private static final double radius = 8;
	private final double angle;

	private ExtremityPlus(double x, double y, double angle) {
		this.angle = angle;
		this.circle = new UEllipse(2 * radius, 2 * radius);
		this.px = x;
		this.py = y;
	}
	
	@Override
	public Point2D somePoint() {
		return new Point2D.Double(px, py);
	}


	public static UDrawable create(Point2D p1, double angle) {
		final double x = p1.getX() - radius + radius * Math.sin(angle);
		final double y = p1.getY() - radius - radius * Math.cos(angle);
		return new ExtremityPlus(x, y, angle);
	}

	public void drawU(UGraphic ug) {
		ug.apply(HColorUtils.WHITE.bg()).apply(new UTranslate(px + 0, py + 0)).draw(circle);
		drawLine(ug, 0, 0, getPointOnCircle(angle - Math.PI / 2), getPointOnCircle(angle + Math.PI / 2));
		drawLine(ug, 0, 0, getPointOnCircle(angle), getPointOnCircle(angle + Math.PI));
	}

	private Point2D getPointOnCircle(double angle) {
		final double x = px + radius + radius * Math.cos(angle);
		final double y = py + radius + radius * Math.sin(angle);
		return new Point2D.Double(x, y);
	}

	static private void drawLine(UGraphic ug, double x, double y, Point2D p1, Point2D p2) {
		final double dx = p2.getX() - p1.getX();
		final double dy = p2.getY() - p1.getY();
		ug.apply(new UTranslate(x + p1.getX(), y + p1.getY())).draw(new ULine(dx, dy));

	}

}
