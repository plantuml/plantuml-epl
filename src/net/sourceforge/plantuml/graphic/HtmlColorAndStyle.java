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
package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.SkinParam;
import net.sourceforge.plantuml.cucadiagram.LinkStyle;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleSignature;
import net.sourceforge.plantuml.ugraphic.color.HColor;
import net.sourceforge.plantuml.ugraphic.color.HColorSet;

public class HtmlColorAndStyle {

	private final HColor arrowHeadColor;
	private final HColor arrowColor;
	private final LinkStyle style;

	@Override
	public String toString() {
		return arrowColor + " " + style;
	}

	public HtmlColorAndStyle(HColor color, HColor arrowHeadColor) {
		this(color, LinkStyle.NORMAL(), arrowHeadColor);
	}

	public HtmlColorAndStyle(HColor arrowColor, LinkStyle style, HColor arrowHeadColor) {
		if (arrowColor == null) {
			throw new IllegalArgumentException();
		}
		this.arrowColor = arrowColor;
		this.arrowHeadColor = arrowHeadColor == null ? arrowColor : arrowHeadColor;
		this.style = style;
	}

	public HColor getArrowColor() {
		return arrowColor;
	}

	public HColor getArrowHeadColor() {
		return arrowHeadColor;
	}

	public LinkStyle getStyle() {
		return style;
	}

	static final public StyleSignature getDefaultStyleDefinitionArrow() {
		return StyleSignature.of(SName.root, SName.element, SName.activityDiagram, SName.arrow);
	}

	public static HtmlColorAndStyle build(ISkinParam skinParam, String definition) {
		HColor arrowColor;
		HColor arrowHeadColor = null;
		if (SkinParam.USE_STYLES()) {
			final Style style = getDefaultStyleDefinitionArrow().getMergedStyle(skinParam.getCurrentStyleBuilder());
			arrowColor = style.value(PName.LineColor).asColor(skinParam.getIHtmlColorSet());
		} else {
			arrowColor = Rainbow.build(skinParam).getColors().get(0).arrowColor;
			arrowColor = Rainbow.build(skinParam).getColors().get(0).arrowHeadColor;
		}
		LinkStyle style = LinkStyle.NORMAL();
		final HColorSet set = skinParam.getIHtmlColorSet();
		for (String s : definition.split(",")) {
			final LinkStyle tmpStyle = LinkStyle.fromString1(s);
			if (tmpStyle.isNormal() == false) {
				style = tmpStyle;
				continue;
			}
			final HColor tmpColor = set.getColorIfValid(s);
			if (tmpColor != null) {
				arrowColor = tmpColor;
			}
		}
		return new HtmlColorAndStyle(arrowColor, style, arrowHeadColor);
	}

}
