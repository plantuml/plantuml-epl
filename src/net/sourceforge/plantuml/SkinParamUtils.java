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
package net.sourceforge.plantuml;

import net.sourceforge.plantuml.cucadiagram.Stereotype;
import net.sourceforge.plantuml.skin.rose.Rose;
import net.sourceforge.plantuml.ugraphic.UFont;
import net.sourceforge.plantuml.ugraphic.color.HColor;

public class SkinParamUtils {

	private static final Rose rose = new Rose();

	public static UFont getFont(ISkinParam skinParam, FontParam fontParam, Stereotype stereo) {
		return skinParam.getFont(stereo, false, fontParam);
	}

	public static HColor getFontColor(ISkinParam skinParam, FontParam fontParam, Stereotype stereo) {
		return skinParam.getFontHtmlColor(stereo, fontParam);
	}

	public static HColor getColor(ISkinParam skinParam, Stereotype stereo, ColorParam... colorParam) {
		return rose.getHtmlColor(skinParam, stereo, colorParam);
	}
	
}
