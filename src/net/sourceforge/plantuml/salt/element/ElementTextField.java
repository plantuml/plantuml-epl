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
package net.sourceforge.plantuml.salt.element;

import java.awt.geom.Dimension2D;

import net.sourceforge.plantuml.Dimension2DDouble;
import net.sourceforge.plantuml.ISkinSimple;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.ugraphic.UFont;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.ULine;
import net.sourceforge.plantuml.ugraphic.UTranslate;

public class ElementTextField extends AbstractElementText implements Element {

	public ElementTextField(String text, UFont font, ISkinSimple spriteContainer) {
		super(text, font, true, spriteContainer);
	}

	public Dimension2D getPreferredDimension(StringBounder stringBounder, double x, double y) {
		final Dimension2D dim = getTextDimensionAt(stringBounder, x);
		return Dimension2DDouble.delta(dim, 6, 2);
	}

	public void drawU(UGraphic ug, int zIndex, Dimension2D dimToUse) {
		if (zIndex != 0) {
			return;
		}
		drawText(ug, 3, 0);
		final Dimension2D dim = getPreferredDimension(ug.getStringBounder(), 0, 0);
		final Dimension2D textDim = getTextDimensionAt(ug.getStringBounder(), 0);
		ug.apply(new UTranslate(1, textDim.getHeight())).draw(ULine.hline(dim.getWidth() - 3));
		final double y3 = textDim.getHeight() - 3;
		ug.apply(new UTranslate(1, y3)).draw(ULine.vline(2));
		ug.apply(new UTranslate(3 + textDim.getWidth() + 1, y3)).draw(ULine.vline(2));
	}

}
