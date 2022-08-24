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
package net.sourceforge.plantuml.ugraphic.color;

import java.awt.Color;

public class ColorMapperIdentity extends AbstractColorMapper implements ColorMapper {

	public Color toColor(HColor color) {
		if (color == null)
			return null;

		if (color instanceof HColorNone)
			return new Color(0, 0, 0, 0);

		if (color instanceof HColorGradient)
			return toColor(((HColorGradient) color).getColor1());

		if (color instanceof HColorMiddle)
			return ((HColorMiddle) color).getMappedColor(this);

		if (color instanceof HColorScheme)
			throw new IllegalStateException();

		if (color instanceof HColorAutomagic)
			throw new IllegalStateException();

		return ((HColorSimple) color).getColor999();
	}
}
