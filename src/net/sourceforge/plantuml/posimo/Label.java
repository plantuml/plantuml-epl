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

import net.sourceforge.plantuml.Dimension2DDouble;

public class Label implements Positionable {

	private double width;
	private double height;
	private double x;
	private double y;

	public Label(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public final void setCenterX(double center) {
		this.x = center - width / 2;
	}

	public final void setCenterY(double center) {
		this.y = center - height / 2;
	}

	public Point2D getPosition() {
		return new Point2D.Double(x, y);
	}

	public Dimension2D getSize() {
		return new Dimension2DDouble(width, height);
	}

	public final void setWidth(double width) {
		this.width = width;
	}

	public final void setHeight(double height) {
		this.height = height;
	}

	public final void setX(double x) {
		this.x = x;
	}

	public final void setY(double y) {
		this.y = y;
	}
	
	public void moveSvek(double deltaX, double deltaY) {
		throw new UnsupportedOperationException();
	}


}
