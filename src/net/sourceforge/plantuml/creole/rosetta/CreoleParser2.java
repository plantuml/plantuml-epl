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
package net.sourceforge.plantuml.creole.rosetta;

import net.sourceforge.plantuml.ISkinSimple;
import net.sourceforge.plantuml.creole.CreoleMode;
import net.sourceforge.plantuml.creole.Sheet;
import net.sourceforge.plantuml.creole.SheetBuilder;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.FontConfiguration;
import net.sourceforge.plantuml.graphic.HorizontalAlignment;

public class CreoleParser2 implements SheetBuilder {

	private final FontConfiguration fontConfiguration;
	private final ISkinSimple skinParam;
	private final HorizontalAlignment horizontalAlignment;
	private final CreoleMode creoleMode;
	private final FontConfiguration stereotype;

	public CreoleParser2(FontConfiguration fontConfiguration, HorizontalAlignment horizontalAlignment,
			ISkinSimple skinParam, CreoleMode creoleMode, FontConfiguration stereotype) {
		this.stereotype = stereotype;
		this.creoleMode = creoleMode;
		this.fontConfiguration = fontConfiguration;
		this.skinParam = skinParam;
		if (skinParam == null) {
			throw new IllegalArgumentException();
		}
		this.horizontalAlignment = horizontalAlignment;
	}

	public Sheet createSheet(Display display) {
		final Sheet sheet = new Sheet(horizontalAlignment);
		if (display.isWhite() == false) {
			final Rosetta rosetta = Rosetta.fromSyntax(WikiLanguage.CREOLE, display);
			for (String cs : rosetta.translateTo(WikiLanguage.UNICODE)) {
				final StripeRow row = StripeRow.parseUnicode(cs, fontConfiguration);
				sheet.add(row);
			}
		}
		return sheet;
	}

}
