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

import java.awt.geom.Dimension2D;

import net.sourceforge.plantuml.Dimension2DDouble;

public class MinMaxMutable {

	private double maxX;
	private double maxY;
	private double minX;
	private double minY;

	public static MinMaxMutable getEmpty(boolean initToZero) {
		if (initToZero) {
			return new MinMaxMutable(0, 0, 0, 0);
		}
		return new MinMaxMutable(Double.MAX_VALUE, Double.MAX_VALUE, -Double.MAX_VALUE, -Double.MAX_VALUE);
	}

	public boolean isInfinity() {
		return minX == Double.MAX_VALUE;
	}

	@Override
	public String toString() {
		return "X=" + minX + " " + maxX + " Y=" + minY + " " + maxY;
	}

	private MinMaxMutable(double minX, double minY, double maxX, double maxY) {
		if (Double.isNaN(minX)) {
			throw new IllegalArgumentException();
		}
		if (Double.isNaN(maxX)) {
			throw new IllegalArgumentException();
		}
		if (Double.isNaN(minY)) {
			throw new IllegalArgumentException();
		}
		if (Double.isNaN(maxY)) {
			throw new IllegalArgumentException();
		}
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
	}

	public void addPoint(double x, double y) {
		if (Double.isNaN(x)) {
			throw new IllegalArgumentException();
		}
		if (Double.isNaN(y)) {
			throw new IllegalArgumentException();
		}
		this.maxX = Math.max(x, maxX);
		this.maxY = Math.max(y, maxY);
		this.minX = Math.min(x, minX);
		this.minY = Math.min(y, minY);
	}

	public static MinMaxMutable fromMax(double maxX, double maxY) {
		if (Double.isNaN(maxX)) {
			throw new IllegalArgumentException();
		}
		if (Double.isNaN(maxY)) {
			throw new IllegalArgumentException();
		}
		final MinMaxMutable result = MinMaxMutable.getEmpty(true);
		result.addPoint(maxX, maxY);
		return result;
	}

	public final double getMaxX() {
		return maxX;
	}

	public final double getMaxY() {
		return maxY;
	}

	public final double getMinX() {
		return minX;
	}

	public final double getMinY() {
		return minY;
	}

	public Dimension2D getDimension() {
		return new Dimension2DDouble(maxX - minX, maxY - minY);
	}

}
