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
package net.sourceforge.plantuml.braille;

import net.sourceforge.plantuml.api.ThemeStyle;
import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.ULine;
import net.sourceforge.plantuml.ugraphic.UTranslate;
import net.sourceforge.plantuml.ugraphic.color.HColorSet;
import net.sourceforge.plantuml.ugraphic.color.HColors;

public class BrailleDrawer implements UDrawable {

	private final BrailleGrid grid;
	private final double step = 9;
	private final double spotSize = 5;

	public BrailleDrawer(BrailleGrid grid) {
		this.grid = grid;
	}

	public void drawU(UGraphic ug) {
		ug = ug.apply(HColorSet.instance().getColorOrWhite(ThemeStyle.LIGHT_REGULAR, "#F0F0F0"));
		for (int x = grid.getMinX(); x <= grid.getMaxX(); x++) {
			ug.apply(UTranslate.dx(x * step + spotSize + 1))
					.draw(ULine.vline((grid.getMaxY() - grid.getMinY()) * step));
		}
		for (int y = grid.getMinY(); y <= grid.getMaxY(); y++) {
			ug.apply(UTranslate.dy(y * step + spotSize + 1))
					.draw(ULine.hline((grid.getMaxX() - grid.getMinX()) * step));
		}
		ug = ug.apply(HColors.BLACK).apply(HColors.BLACK.bg());
		for (int x = grid.getMinX(); x <= grid.getMaxX(); x++) {
			for (int y = grid.getMinY(); y <= grid.getMaxY(); y++) {
				if (grid.getState(x, y)) {
					drawCircle(ug, x, y);
				}
			}
		}
	}

	private void drawCircle(UGraphic ug, int x, int y) {
		final double cx = x * step;
		final double cy = y * step;
		ug.apply(new UTranslate(cx, cy)).draw(new UEllipse(spotSize, spotSize));
	}

}
