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

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.ULine;
import net.sourceforge.plantuml.ugraphic.UTranslate;
import net.sourceforge.plantuml.ugraphic.color.HColorUtils;

class ExtremityHalfArrow extends Extremity {

	private final ULine line;
	private final ULine otherLine;
	private final Point2D contact;

	@Override
	public Point2D somePoint() {
		return contact;
	}

	public ExtremityHalfArrow(Point2D p1, double angle, Point2D center) {
		angle = manageround(angle);
		final AffineTransform rotate = AffineTransform.getRotateInstance(angle + Math.PI / 2);
		final int xWing = 9;
		final int yAperture = 4;
		final Point2D other = new Point2D.Double(-xWing, -yAperture);
		rotate.transform(other, other);
		this.contact = p1;
		this.line = new ULine(center.getX() - contact.getX(), center.getY() - contact.getY());
		this.otherLine = new ULine(other.getX(), other.getY());
	}

	public ExtremityHalfArrow(Point2D p0, double angle) {
		throw new UnsupportedOperationException();
	}

	public void drawU(UGraphic ug) {
		ug = ug.apply(HColorUtils.changeBack(ug));
		if (line != null && line.getLength() > 2) {
			ug.apply(new UTranslate(contact.getX(), contact.getY())).draw(line);
			ug.apply(new UTranslate(contact.getX(), contact.getY())).draw(otherLine);
		}
	}

}
