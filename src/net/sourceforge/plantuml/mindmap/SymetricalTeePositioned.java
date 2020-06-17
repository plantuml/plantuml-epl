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
package net.sourceforge.plantuml.mindmap;

import java.awt.geom.Line2D;

public class SymetricalTeePositioned {

	private final SymetricalTee tee;
	private double y;

	@Override
	public String toString() {
		return "y=" + y + " " + tee;
	}

	public SymetricalTeePositioned(SymetricalTee tee) {
		this(tee, 0);
	}

	private SymetricalTeePositioned(SymetricalTee tee, double y) {
		this.tee = tee;
		this.y = y;
	}

	public void moveSoThatSegmentA1isOn(double newY) {
		final double current = getSegmentA1().getY1();
		y += newY - current;
	}

	public void moveSoThatSegmentA2isOn(double newY) {
		final double current = getSegmentA2().getY1();
		y += newY - current;
	}

	public void move(double delta) {
		y += delta;
	}

	public Line2D getSegmentA1() {
		return new Line2D.Double(0, y - tee.getThickness1() / 2, tee.getElongation1(), y - tee.getThickness1() / 2);
	}

	public Line2D getSegmentB1() {
		return new Line2D.Double(0, y + tee.getThickness1() / 2, tee.getElongation1(), y + tee.getThickness1() / 2);
	}

	public Line2D getSegmentA2() {
		return new Line2D.Double(tee.getElongation1(), y - tee.getThickness2() / 2, tee.getElongation1()
				+ tee.getElongation2(), y - tee.getThickness2() / 2);
	}

	public Line2D getSegmentB2() {
		return new Line2D.Double(tee.getElongation1(), y + tee.getThickness2() / 2, tee.getElongation1()
				+ tee.getElongation2(), y + tee.getThickness2() / 2);
	}

	public double getMaxX() {
		return tee.getElongation1() + tee.getElongation2();
	}

	public double getMaxY() {
		return y + Math.max(tee.getThickness1() / 2, tee.getThickness2() / 2);
	}

	public double getMinY() {
		return y - Math.max(tee.getThickness1() / 2, tee.getThickness2() / 2);
	}

	public final double getY() {
		return y;
	}

	public SymetricalTeePositioned getMax(SymetricalTeePositioned other) {
		if (this.tee != other.tee) {
			throw new IllegalArgumentException();
		}
		if (other.y > this.y) {
			return other;
		}
		return this;
	}

}
