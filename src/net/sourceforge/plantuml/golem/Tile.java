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
package net.sourceforge.plantuml.golem;

import net.sourceforge.plantuml.awt.geom.Dimension2D;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import net.sourceforge.plantuml.Dimension2DDouble;
import net.sourceforge.plantuml.SpriteContainerEmpty;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.AbstractTextBlock;
import net.sourceforge.plantuml.graphic.FontConfiguration;
import net.sourceforge.plantuml.graphic.HorizontalAlignment;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UFont;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.URectangle;
import net.sourceforge.plantuml.ugraphic.UTranslate;
import net.sourceforge.plantuml.ugraphic.color.HColors;

public class Tile extends AbstractTextBlock implements TextBlock {

	private static double SIZE = 40;
	private final int num;

	private final UFont numberFont = UFont.monospaced(11);
	private final FontConfiguration fc = FontConfiguration.blackBlueTrue(numberFont);
	private final Map<TileGeometry, TileArea> geometries;

	Tile(int num) {
		this.num = num;
		final Map<TileGeometry, TileArea> tmp = new EnumMap<TileGeometry, TileArea>(TileGeometry.class);
		for (TileGeometry g : TileGeometry.values()) {
			tmp.put(g, new TileArea(this, g));
		}
		this.geometries = Collections.unmodifiableMap(tmp);
	}

	public TileArea getArea(TileGeometry geometry) {
		return this.geometries.get(geometry);
	}

	public void drawU(UGraphic ug) {
		ug = ug.apply(HColors.BLACK);
		final TextBlock n = Display.create("" + num).create(fc, HorizontalAlignment.LEFT, new SpriteContainerEmpty());
		final Dimension2D dimNum = n.calculateDimension(ug.getStringBounder());
		final Dimension2D dimTotal = calculateDimension(ug.getStringBounder());
		final double diffx = dimTotal.getWidth() - dimNum.getWidth();
		final double diffy = dimTotal.getHeight() - dimNum.getHeight();
		final double radius = Math.max(dimNum.getWidth(), dimNum.getHeight());
		final double diffx2 = dimTotal.getWidth() - radius;
		final double diffy2 = dimTotal.getHeight() - radius;
		n.drawU(ug.apply(new UTranslate((diffx / 2), (diffy / 2))));
		ug.draw(new URectangle(SIZE, SIZE));
		ug.apply(new UTranslate(diffx2 / 2, diffy2 / 2)).draw(new UEllipse(radius, radius));
	}

	public Dimension2D calculateDimension(StringBounder stringBounder) {
		return new Dimension2DDouble(SIZE, SIZE);
	}
}
