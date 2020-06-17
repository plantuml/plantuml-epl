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
package net.sourceforge.plantuml.ugraphic.hand;

import java.util.Random;

import net.sourceforge.plantuml.graphic.UGraphicDelegator;
import net.sourceforge.plantuml.posimo.DotPath;
import net.sourceforge.plantuml.ugraphic.UChange;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.ULine;
import net.sourceforge.plantuml.ugraphic.UPath;
import net.sourceforge.plantuml.ugraphic.UPolygon;
import net.sourceforge.plantuml.ugraphic.URectangle;
import net.sourceforge.plantuml.ugraphic.UShape;
import net.sourceforge.plantuml.ugraphic.svg.UGraphicSvg;

public class UGraphicHandwritten extends UGraphicDelegator implements UGraphic {

	private final Random rnd = new Random(424242L);

	public UGraphicHandwritten(UGraphic ug) {
		super(ug);
		if (ug instanceof UGraphicSvg) {
			((UGraphicSvg) ug).enlargeClip();
		}
	}

	public void draw(UShape shape) {
		// http://www.ufonts.com/fonts/felt-tip-roman.html
		// http://webdesignledger.com/freebies/20-amazing-free-handwritten-fonts-for-your-designs
		if (shape instanceof ULine) {
			drawHand((ULine) shape);
		} else if (shape instanceof URectangle) {
			drawHand((URectangle) shape);
		} else if (shape instanceof UPolygon) {
			drawHand((UPolygon) shape);
		} else if (shape instanceof UEllipse) {
			drawHand((UEllipse) shape);
		} else if (shape instanceof DotPath) {
			drawHand((DotPath) shape);
		} else if (shape instanceof UPath) {
			drawHand((UPath) shape);
		} else {
			getUg().draw(shape);
		}
	}

	private void drawHand(UPath shape) {
		final UPathHand uline = new UPathHand(shape, rnd);
		getUg().draw(uline.getHanddrawn());
	}

	private void drawHand(DotPath shape) {
		final UDotPathHand uline = new UDotPathHand(shape, rnd);
		getUg().draw(uline.getHanddrawn());
	}

	private void drawHand(UPolygon shape) {
		final UPolygonHand hand = new UPolygonHand(shape, rnd);
		getUg().draw(hand.getHanddrawn());
	}

	private void drawHand(URectangle shape) {
		final URectangleHand hand = new URectangleHand(shape, rnd);
		getUg().draw(hand.getHanddrawn());
	}

	private void drawHand(ULine shape) {
		final ULineHand uline = new ULineHand(shape, rnd);
		getUg().draw(uline.getHanddrawn());
	}

	private void drawHand(UEllipse shape) {
		final UEllipseHand uline = new UEllipseHand(shape, rnd);
		getUg().draw(uline.getHanddrawn());
	}

	public UGraphic apply(UChange change) {
		return new UGraphicHandwritten(getUg().apply(change));
	}

}
