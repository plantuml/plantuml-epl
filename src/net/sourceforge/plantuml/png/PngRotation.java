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
package net.sourceforge.plantuml.png;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import net.sourceforge.plantuml.Log;

public class PngRotation {

	public static BufferedImage process(BufferedImage im) {

		Log.info("Rotation");

		final BufferedImage newIm = new BufferedImage(im.getHeight(), im.getWidth(), BufferedImage.TYPE_INT_RGB);
		final Graphics2D g2d = newIm.createGraphics();

		final AffineTransform at = new AffineTransform(0, 1, 1, 0, 0, 0);
		at.concatenate(new AffineTransform(-1, 0, 0, 1, im.getWidth(), 0));
		g2d.setTransform(at);

		g2d.drawImage(im, 0, 0, null);
		g2d.dispose();

		return newIm;
	}

}
