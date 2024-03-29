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
package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.color.HColor;
import net.sourceforge.plantuml.ugraphic.color.HColors;

public class CenterBorderColor {

	private final HColor center;
	private final HColor border;
	private final String style;

	public CenterBorderColor(HColor center, HColor border) {
		this(center, border, null);
	}

	public CenterBorderColor(HColor center, HColor border, String style) {
		this.center = center;
		this.border = border;
		this.style = style;
	}

	public UGraphic apply(UGraphic ug) {
		if (isOk() == false) {
			throw new IllegalStateException();
		}
		ug = ug.apply(center.bg());
		if (border == null) {
			ug = ug.apply(center);
		} else {
			ug = ug.apply(border);
		}
		return ug;
	}

	public boolean isOk() {
		return center != null;
	}

	public final HColor getCenter() {
		return center;
	}

	public final String getStyle() {
		return style;
	}

	public CenterBorderColor unlinearTo(CenterBorderColor other, int completion) {
		final HColor newCenter = HColors.unlinear(this.center, other.center, completion);
		final HColor newBorder = HColors.unlinear(this.border, other.border, completion);

		return new CenterBorderColor(newCenter, newBorder, style);
	}
}
