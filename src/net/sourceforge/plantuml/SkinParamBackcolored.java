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

import java.util.EnumMap;
import java.util.Map;

import net.sourceforge.plantuml.cucadiagram.Stereotype;
import net.sourceforge.plantuml.ugraphic.color.HColor;

public class SkinParamBackcolored extends SkinParamDelegator {

	final private HColor backColorElement;
	final private HColor backColorGeneral;
	final private boolean forceClickage;

	public SkinParamBackcolored(ISkinParam skinParam, HColor backColorElement) {
		this(skinParam, backColorElement, null, false);
	}

	public SkinParamBackcolored(ISkinParam skinParam, HColor backColorElement, boolean forceClickage) {
		this(skinParam, backColorElement, null, forceClickage);
	}

	public SkinParamBackcolored(ISkinParam skinParam, HColor backColorElement, HColor backColorGeneral) {
		this(skinParam, backColorElement, backColorGeneral, false);
	}

	@Override
	public String toString() {
		return super.toString() + " " + backColorElement + " " + backColorGeneral;
	}

	public SkinParamBackcolored(ISkinParam skinParam, HColor backColorElement, HColor backColorGeneral,
			boolean forceClickage) {
		super(skinParam);
		this.forceClickage = forceClickage;
		this.backColorElement = backColorElement;
		this.backColorGeneral = backColorGeneral;
	}

	@Override
	public HColor getBackgroundColor(boolean replaceTransparentByWhite) {
		if (backColorGeneral != null) {
			return backColorGeneral;
		}
		return super.getBackgroundColor(replaceTransparentByWhite);
	}

	@Override
	public HColor getHtmlColor(ColorParam param, Stereotype stereotype, boolean clickable) {
		if (param.isBackground() && backColorElement != null) {
			return backColorElement;
		}
		if (forceClickage) {
			final HColor c1 = super.getHtmlColor(param, stereotype, true);
			if (c1 != null) {
				return c1;
			}
			// clickable = true;
		}
		final HColor forcedColor = forced.get(param);
		if (forcedColor != null) {
			return forcedColor;
		}
		return super.getHtmlColor(param, stereotype, clickable);
	}

	private final Map<ColorParam, HColor> forced = new EnumMap<ColorParam, HColor>(ColorParam.class);

	public void forceColor(ColorParam param, HColor color) {
		forced.put(param, color);
	}

}
