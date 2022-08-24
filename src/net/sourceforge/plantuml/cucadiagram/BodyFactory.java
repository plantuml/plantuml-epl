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
package net.sourceforge.plantuml.cucadiagram;

import java.util.List;
import java.util.Set;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.ISkinSimple;
import net.sourceforge.plantuml.LineBreakStrategy;
import net.sourceforge.plantuml.graphic.FontConfiguration;
import net.sourceforge.plantuml.graphic.HorizontalAlignment;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.skin.VisibilityModifier;
import net.sourceforge.plantuml.style.Style;

public class BodyFactory {

	public final static boolean BODY3 = false;

	public static Bodier createLeaf(LeafType type, Set<VisibilityModifier> hides) {
		if (type.isLikeClass() || type == LeafType.OBJECT)
			return new BodierLikeClassOrObject(type, hides);

		return new BodierSimple();
	}

	public static Bodier createGroup(Set<VisibilityModifier> hides) {
		return new BodierSimple();
	}

	public static TextBlock create1(HorizontalAlignment align, List<CharSequence> rawBody, ISkinParam skinParam,
			Stereotype stereotype, ILeaf entity, Style style) {
		return new BodyEnhanced1(align, rawBody, skinParam, entity, style);
	}

	public static TextBlock create2(HorizontalAlignment align, Display display, ISkinParam skinParam,
			Stereotype stereotype, ILeaf entity, Style style) {
		return new BodyEnhanced1(align, display, skinParam, entity, style);
	}

	public static TextBlock create3(Display rawBody, ISkinSimple skinParam, HorizontalAlignment align,
			FontConfiguration titleConfig, LineBreakStrategy lineBreakStrategy, Style style) {
		return new BodyEnhanced2(rawBody, skinParam, align, titleConfig, lineBreakStrategy, style);
	}

}
