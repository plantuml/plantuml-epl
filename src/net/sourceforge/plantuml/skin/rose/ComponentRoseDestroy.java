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
package net.sourceforge.plantuml.skin.rose;

import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.skin.AbstractComponent;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.ULine;
import net.sourceforge.plantuml.ugraphic.UStroke;
import net.sourceforge.plantuml.ugraphic.UTranslate;
import net.sourceforge.plantuml.ugraphic.color.HColor;

public class ComponentRoseDestroy extends AbstractComponent {

	private final HColor foregroundColor;

	public ComponentRoseDestroy(Style style, HColor foregroundColor) {
		super(style);
		this.foregroundColor = foregroundColor;
	}

	private final int crossSize = 9;

	@Override
	protected void drawInternalU(UGraphic ug, Area area) {
		ug = ug.apply(new UStroke(2)).apply(foregroundColor);

		ug.draw(new ULine(2 * crossSize, 2 * crossSize));
		ug.apply(UTranslate.dy(2 * crossSize)).draw(new ULine(2 * crossSize, -2 * crossSize));
	}

	@Override
	public double getPreferredHeight(StringBounder stringBounder) {
		return crossSize * 2;
	}

	@Override
	public double getPreferredWidth(StringBounder stringBounder) {
		return crossSize * 2;
	}

}
