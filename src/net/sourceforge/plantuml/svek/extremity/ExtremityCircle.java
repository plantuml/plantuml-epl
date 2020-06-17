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
import net.sourceforge.plantuml.ugraphic.UStroke;
import net.sourceforge.plantuml.ugraphic.UTranslate;
import net.sourceforge.plantuml.ugraphic.color.HColorUtils;

class ExtremityCircle extends Extremity {

	private static final double radius = 6;
	private final Point2D dest;
	private final boolean fill;

	@Override
	public Point2D somePoint() {
		return dest;
	}

	public static UDrawable create(Point2D center, boolean fill, double angle) {
		return new ExtremityCircle(center.getX(), center.getY(), fill, angle);
	}

	private ExtremityCircle(double x, double y, boolean fill, double angle) {
		this.dest = new Point2D.Double(x - radius * Math.cos(angle + Math.PI / 2), y - radius
				* Math.sin(angle + Math.PI / 2));
		this.fill = fill;
		// contact = new Point2D.Double(p1.getX() - xContact * Math.cos(angle + Math.PI / 2), p1.getY() - xContact
		// * Math.sin(angle + Math.PI / 2));
	}

	public void drawU(UGraphic ug) {

		ug = ug.apply(new UStroke(1.5));
		if (fill) {
			ug = ug.apply(HColorUtils.changeBack(ug));
		} else {
			ug = ug.apply(HColorUtils.WHITE.bg());
		}

		ug = ug.apply(new UTranslate(dest.getX() - radius, dest.getY() - radius));
		ug.draw(new UEllipse(radius * 2, radius * 2));
	}

}
