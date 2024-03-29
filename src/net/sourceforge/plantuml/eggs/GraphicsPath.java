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
package net.sourceforge.plantuml.eggs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import net.sourceforge.plantuml.EmptyImageBuilder;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.api.ImageDataSimple;
import net.sourceforge.plantuml.core.ImageData;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.png.PngIO;
import net.sourceforge.plantuml.ugraphic.UChange;
import net.sourceforge.plantuml.ugraphic.UMotif;
import net.sourceforge.plantuml.ugraphic.color.ColorMapper;
import net.sourceforge.plantuml.ugraphic.color.HColors;
import net.sourceforge.plantuml.ugraphic.g2d.UGraphicG2d;

public class GraphicsPath {

	private final String path;
	private final ColorMapper colorMapper;

	public GraphicsPath(ColorMapper colorMapper, String path) {
		this.path = path;
		this.colorMapper = colorMapper;
	}

	public ImageData writeImage(OutputStream os) throws IOException {
		final BufferedImage im = createImage();
		PngIO.write(im, os, 96);
		return new ImageDataSimple(im.getWidth(), im.getHeight());
	}

	private BufferedImage createImage() {
		final StringBounder stringBounder = FileFormat.PNG.getDefaultStringBounder();
		final EmptyImageBuilder builder = new EmptyImageBuilder(null, 50, 50, Color.WHITE, stringBounder);
		final BufferedImage im = builder.getBufferedImage();
		final Graphics2D g2d = builder.getGraphics2D();

		final UGraphicG2d ug = new UGraphicG2d(HColors.WHITE, colorMapper, stringBounder, g2d, 1.0);
		ug.setBufferedImage(im);
		final UMotif motif = new UMotif(path);
		motif.drawHorizontal(ug.apply((UChange) HColors.BLACK), 20, 20, 1);

		g2d.dispose();
		return im;
	}

}
