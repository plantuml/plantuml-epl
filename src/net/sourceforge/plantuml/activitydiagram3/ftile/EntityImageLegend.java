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
package net.sourceforge.plantuml.activitydiagram3.ftile;

import net.sourceforge.plantuml.ColorParam;
import net.sourceforge.plantuml.FontParam;
import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.LineParam;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.FontConfiguration;
import net.sourceforge.plantuml.graphic.HorizontalAlignment;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.graphic.TextBlockUtils;
import net.sourceforge.plantuml.skin.rose.Rose;
import net.sourceforge.plantuml.ugraphic.UStroke;
import net.sourceforge.plantuml.ugraphic.color.HColor;

public class EntityImageLegend {

	public static TextBlock create(Display note, ISkinParam skinParam) {

		final TextBlock textBlock = note.create(new FontConfiguration(skinParam, FontParam.LEGEND, null),
				HorizontalAlignment.LEFT, skinParam);
		final Rose rose = new Rose();
		final HColor legendBackgroundColor = rose.getHtmlColor(skinParam, ColorParam.legendBackground);
		final HColor legendColor = rose.getHtmlColor(skinParam, ColorParam.legendBorder);
		final UStroke stroke = skinParam.getThickness(LineParam.legendBorder, null);

		final int cornersize = 10;
		final TextBlock result = TextBlockUtils.bordered(textBlock, stroke, legendColor, legendBackgroundColor,
				cornersize);
		return TextBlockUtils.withMargin(result, 8, 8);
	}

}
