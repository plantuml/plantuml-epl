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
package net.sourceforge.plantuml.salt.element;

import net.sourceforge.plantuml.awt.geom.Dimension2D;
import java.util.ArrayList;
import java.util.Collection;

import net.sourceforge.plantuml.Dimension2DDouble;
import net.sourceforge.plantuml.ISkinSimple;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.ugraphic.UFont;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.ULine;
import net.sourceforge.plantuml.ugraphic.URectangle;
import net.sourceforge.plantuml.ugraphic.UTranslate;

public class ElementMenuPopup extends AbstractElement {

	private final Collection<ElementMenuEntry> entries = new ArrayList<>();
	private final UFont font;
	private final ISkinSimple spriteContainer;

	public ElementMenuPopup(UFont font, ISkinSimple spriteContainer) {
		this.font = font;
		this.spriteContainer = spriteContainer;
	}

	public void addEntry(String s) {
		entries.add(new ElementMenuEntry(s, font, spriteContainer));
	}

	public Dimension2D getPreferredDimension(StringBounder stringBounder, double x, double y) {
		double w = 0;
		double h = 0;
		for (ElementMenuEntry entry : entries) {
			final Dimension2D dim = entry.getPreferredDimension(stringBounder, x, y);
			w = Math.max(w, dim.getWidth());
			h += dim.getHeight();
		}
		return new Dimension2DDouble(w, h);
	}

	public void drawU(UGraphic ug, int zIndex, Dimension2D dimToUse) {
		if (zIndex != 1)
			return;

		ug = ug.apply(getBlack());

		ug.apply(getColorDD().bg()).draw(new URectangle(dimToUse.getWidth(), dimToUse.getHeight()));

		double y1 = 0;
		for (ElementMenuEntry entry : entries) {
			final double h = entry.getPreferredDimension(ug.getStringBounder(), 0, y1).getHeight();
			if (entry.getText().equals("-"))
				ug.apply(UTranslate.dy(y1 + h / 2)).draw(ULine.hline(dimToUse.getWidth()));
			else
				entry.drawU(ug.apply(UTranslate.dy(y1)), zIndex, dimToUse);

			y1 += h;
		}
	}

}
