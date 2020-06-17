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
package net.sourceforge.plantuml.posimo;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

import net.sourceforge.plantuml.graphic.StringBounder;

public class MargedBlock {

	private final Block block;
	private final IEntityImageBlock imageBlock;
	private final double marginDecorator;
	private final Dimension2D imageDimension;

	static private int uid = 1;

	public MargedBlock(StringBounder stringBounder, IEntityImageBlock imageBlock, double marginDecorator, Cluster parent) {
		this.imageBlock = imageBlock;
		this.marginDecorator = marginDecorator;
		this.imageDimension = imageBlock.getDimension(stringBounder);
		this.block = new Block(uid++, imageDimension.getWidth() + 2 * marginDecorator, imageDimension.getHeight() + 2
				* marginDecorator, parent);
	}

	public Block getBlock() {
		return block;
	}

	public double getMarginDecorator() {
		return marginDecorator;
	}

	public IEntityImageBlock getImageBlock() {
		return imageBlock;
	}

	public Positionable getImagePosition() {
		return new Positionable() {

			public Dimension2D getSize() {
				return imageDimension;
			}

			public Point2D getPosition() {
				final Point2D pos = block.getPosition();
				return new Point2D.Double(pos.getX() + marginDecorator, pos.getY() + marginDecorator);
			}

			public void moveSvek(double deltaX, double deltaY) {
				throw new UnsupportedOperationException();
			}
		};
	}

}
