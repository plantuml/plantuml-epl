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
package net.sourceforge.plantuml.creole;

import net.sourceforge.plantuml.awt.geom.Dimension2D;
import net.sourceforge.plantuml.ugraphic.MinMax;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.URectangle;
import net.sourceforge.plantuml.ugraphic.UTranslate;
import net.sourceforge.plantuml.ugraphic.color.HColors;

public class Position {

	private final double x;
	private final double y;
	private final Dimension2D dim;

	public Position(double x, double y, Dimension2D dim) {
		this.x = x;
		this.y = y;
		this.dim = dim;
//		if (dim.getHeight() == 0) {
//			throw new IllegalArgumentException();
//		}
//		if (dim.getWidth() == 0) {
//			throw new IllegalArgumentException();
//		}
	}

	@Override
	public String toString() {
		return "x=" + x + " y=" + y + " dim=" + dim;
	}

	public Position align(double height) {
		final double dy = height - dim.getHeight();
		return translateY(dy);
	}

	public final double getMinY() {
		return y;
	}

	public final double getMaxY() {
		return y + getHeight();
	}

	public UGraphic translate(UGraphic ug) {
		return ug.apply(new UTranslate(x, y));
	}

	public Position translateY(double dy) {
		return new Position(x, y + dy, dim);
	}

	public Position translateX(double dx) {
		return new Position(x + dx, y, dim);
	}

	public MinMax update(MinMax minMax) {
		return minMax.addPoint(x + dim.getWidth(), y + dim.getHeight());
	}

	public void drawDebug(UGraphic ug) {
		ug = ug.apply(HColors.BLACK).apply(HColors.none().bg());
		ug = ug.apply(new UTranslate(x, y));
		ug.draw(new URectangle(dim));
	}

	public double getHeight() {
		return dim.getHeight();
	}

	public double getWidth() {
		return dim.getWidth();
	}

	public UTranslate getTranslate() {
		return new UTranslate(x, y);
	}

}
