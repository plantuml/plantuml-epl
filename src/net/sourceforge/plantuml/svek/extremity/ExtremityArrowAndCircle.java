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
package net.sourceforge.plantuml.svek.extremity;

import java.awt.geom.Point2D;

import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UPolygon;
import net.sourceforge.plantuml.ugraphic.UStroke;
import net.sourceforge.plantuml.ugraphic.UTranslate;
import net.sourceforge.plantuml.ugraphic.color.HColor;
import net.sourceforge.plantuml.ugraphic.color.HColors;

class ExtremityArrowAndCircle extends Extremity {

	private UPolygon polygon = new UPolygon();
	private final Point2D contact;
	private final Point2D dest;
	private final double radius = 5;
	private final HColor backgroundColor;

	@Override
	public Point2D somePoint() {
		return contact;
	}

	public ExtremityArrowAndCircle(Point2D p1, double angle, Point2D center, HColor backgroundColor) {
		angle = manageround(angle);
		polygon.addPoint(0, 0);
		this.backgroundColor = backgroundColor;
		this.dest = new Point2D.Double(p1.getX(), p1.getY());
		final int xWing = 9;
		final int yAperture = 4;
		polygon.addPoint(-xWing, -yAperture);
		final int xContact = 5;
		polygon.addPoint(-xContact, 0);
		polygon.addPoint(-xWing, yAperture);
		polygon.addPoint(0, 0);
		polygon.rotate(angle + Math.PI / 2);
		polygon = polygon.translate(p1.getX() + radius * Math.sin(angle), p1.getY() - radius * Math.cos(angle));
		contact = new Point2D.Double(p1.getX() - xContact * Math.cos(angle + Math.PI / 2),
				p1.getY() - xContact * Math.sin(angle + Math.PI / 2));
	}

	public void drawU(UGraphic ug) {
		ug.apply(HColors.changeBack(ug)).draw(polygon);
		ug.apply(new UStroke(1.5)).apply(backgroundColor.bg())
				.apply(new UTranslate(dest.getX() - radius, dest.getY() - radius))
				.draw(new UEllipse(radius * 2, radius * 2));
	}

}
