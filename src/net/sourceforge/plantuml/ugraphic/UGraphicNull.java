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

import java.io.IOException;
import java.io.OutputStream;

import net.sourceforge.plantuml.EnsureVisible;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.TikzFontDistortion;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.ugraphic.color.ColorMapperIdentity;

public class UGraphicNull extends AbstractUGraphic<String> implements EnsureVisible, UGraphic2 {

	@Override
	protected AbstractCommonUGraphic copyUGraphic() {
		return new UGraphicNull(this);
	}

	private UGraphicNull(UGraphicNull other) {
		super(other);
	}

	public UGraphicNull() {
		super(new ColorMapperIdentity(), "foo");
	}

	public StringBounder getStringBounder() {
		return FileFormat.PNG.getDefaultStringBounder(TikzFontDistortion.getDefault());
	}

	public void writeImageTOBEMOVED(OutputStream os, String metadata, int dpi) throws IOException {
	}

	public void ensureVisible(double x, double y) {
	}

}
