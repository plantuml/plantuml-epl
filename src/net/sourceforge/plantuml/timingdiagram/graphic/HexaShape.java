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
package net.sourceforge.plantuml.timingdiagram.graphic;

import net.sourceforge.plantuml.graphic.SymbolContext;
import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UPolygon;

public class HexaShape implements UDrawable {

	private final double width;
	private final double height;
	private final SymbolContext context;

	private final double delta = 12;

	private HexaShape(double width, double height, SymbolContext context) {
		this.width = width;
		this.height = height;
		this.context = context;
	}

	public static HexaShape create(double width, double height, SymbolContext context) {
		return new HexaShape(width, height, context);
	}

	public void drawU(UGraphic ug) {
		ug = context.apply(ug);
		final UPolygon polygon = new UPolygon();
		polygon.addPoint(delta, 0);
		polygon.addPoint(width - delta, 0);
		polygon.addPoint(width, height / 2);
		polygon.addPoint(width - delta, height);
		polygon.addPoint(delta, height);
		polygon.addPoint(0, height / 2);
		ug.draw(polygon);

	}

}
