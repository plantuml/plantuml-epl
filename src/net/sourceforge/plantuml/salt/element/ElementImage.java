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
import java.util.List;

import net.sourceforge.plantuml.Dimension2DDouble;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UPixel;
import net.sourceforge.plantuml.ugraphic.UTranslate;

public class ElementImage extends AbstractElement {

	private final List<String> img;

	public ElementImage(List<String> img) {
		this.img = img;
	}

	public Dimension2D getPreferredDimension(StringBounder stringBounder, double x, double y) {
		return new Dimension2DDouble(img.get(0).length(), img.size());
	}

	public void drawU(UGraphic ug, int zIndex, Dimension2D dimToUse) {
		if (zIndex != 0) {
			return;
		}
		final int w = img.get(0).length();
		final int h = img.size();
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				final char c = img.get(j).charAt(i);
				if (c == 'X') {
					ug.apply(new UTranslate(i, j)).draw(new UPixel());
				}
			}
		}
	}
}
