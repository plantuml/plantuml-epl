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

import java.awt.geom.Dimension2D;

import net.sourceforge.plantuml.ugraphic.UEmpty;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UShapeIgnorableForCompression;
import net.sourceforge.plantuml.ugraphic.UTranslate;
import net.sourceforge.plantuml.ugraphic.comp.CompressionMode;

public class SpecialText implements UShapeIgnorableForCompression {

	private final TextBlock title;

	public SpecialText(TextBlock title) {
		this.title = title;
	}

	public boolean isIgnoreForCompressionOn(CompressionMode mode) {
		return true;
	}

	public void drawWhenCompressed(UGraphic ug, CompressionMode mode) {
		final Dimension2D dim = title.calculateDimension(ug.getStringBounder());
		ug.apply(UTranslate.dx(dim.getWidth())).draw(new UEmpty(1, 1));
	}

	public final TextBlock getTitle() {
		return title;
	}

}
