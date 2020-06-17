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
package net.sourceforge.plantuml.asciiart;

import java.awt.geom.Dimension2D;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.skin.ComponentType;
import net.sourceforge.plantuml.skin.Context2D;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.txt.UGraphicTxt;

public class ComponentTextDivider extends AbstractComponentText {

	private final ComponentType type;
	private final Display stringsToDisplay;
	private final FileFormat fileFormat;

	public ComponentTextDivider(ComponentType type, Display stringsToDisplay, FileFormat fileFormat) {
		this.type = type;
		this.stringsToDisplay = stringsToDisplay;
		this.fileFormat = fileFormat;
	}

	public void drawU(UGraphic ug, Area area, Context2D context) {
		final Dimension2D dimensionToUse = area.getDimensionToUse();
		final UmlCharArea charArea = ((UGraphicTxt) ug).getCharArea();
		final int width = (int) dimensionToUse.getWidth();
		final int textWidth = StringUtils.getWcWidth(stringsToDisplay);
		// final int height = (int) dimensionToUse.getHeight();

		final int textPos = (width - textWidth - 1) / 2;
		final String desc = " " + stringsToDisplay.get(0).toString();

		if (fileFormat == FileFormat.UTXT) {
			charArea.drawHLine('\u2550', 2, 0, width, '\u2502', '\u256a');
			charArea.drawStringLR(desc, textPos, 2);
			
			charArea.drawHLine('\u2550', 1, textPos - 1, textPos + desc.length() + 1, '\u2502', '\u2567');
			charArea.drawHLine('\u2550', 3, textPos - 1, textPos + desc.length() + 1, '\u2502', '\u2564');
			
			charArea.drawStringTB("\u2554\u2563\u255a", textPos - 1, 1);
			charArea.drawStringTB("\u2557\u2560\u255d", textPos + desc.length(), 1);
		} else {
			charArea.drawHLine('=', 2, 0, width);
			charArea.drawStringLR(desc, textPos, 2);
			charArea.drawHLine('=', 1, textPos - 1, textPos + desc.length() + 1);
			charArea.drawHLine('=', 3, textPos - 1, textPos + desc.length() + 1);
		}
	}

	public double getPreferredHeight(StringBounder stringBounder) {
		return StringUtils.getHeight(stringsToDisplay) + 4;
	}

	public double getPreferredWidth(StringBounder stringBounder) {
		return StringUtils.getWcWidth(stringsToDisplay) + 2;
	}

}
