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
package net.sourceforge.plantuml.ugraphic;

import net.sourceforge.plantuml.creole.Stencil;

public class UGraphicStencil extends AbstractUGraphicHorizontalLine {

	private final Stencil stencil;
	private final UStroke defaultStroke;

	public static UGraphic create(UGraphic ug, Stencil stencil, UStroke defaultStroke) {
		return new UGraphicStencil(ug, stencil, defaultStroke);
	}
	
	
	private UGraphicStencil(UGraphic ug, Stencil stencil, UStroke defaultStroke) {
		super(ug);
		this.stencil = stencil;
		this.defaultStroke = defaultStroke;
	}

	@Override
	protected AbstractUGraphicHorizontalLine copy(UGraphic ug) {
		return new UGraphicStencil(ug, stencil, defaultStroke);
	}

	@Override
	protected void drawHline(UGraphic ug, UHorizontalLine line, UTranslate translate) {
		line.drawLineInternal(ug, stencil, translate.getDy(), defaultStroke);
		// final UDrawable ud = stencil.convert(line, ug.getStringBounder());
		// ud.drawU(ug.apply(translate));
		// line.drawLine(ug.apply(translate), startingX, endingX, 0, defaultStroke);
	}

}
