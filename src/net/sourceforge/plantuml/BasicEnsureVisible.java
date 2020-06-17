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
package net.sourceforge.plantuml;

public class BasicEnsureVisible implements EnsureVisible {

	private double minX = Double.MAX_VALUE;
	private double maxX = -Double.MAX_VALUE;
	private double minY = Double.MAX_VALUE;
	private double maxY = -Double.MAX_VALUE;

	public void ensureVisible(double x, double y) {
		if (x > maxX) {
			maxX = x;
		}
		if (x < minX) {
			minX = x;
		}
		if (y > maxY) {
			maxY = y;
		}
		if (y < minY) {
			minY = y;
		}
	}

	public boolean hasData() {
		return minX != Double.MAX_VALUE;
	}

	public String getCoords(double scale) {
		if (minX == Double.MAX_VALUE) {
			return "0,0,0,0";
		}
		final int x1 = (int) (minX * scale);
		final int y1 = (int) (minY * scale);
		final int x2 = (int) (maxX * scale);
		final int y2 = (int) (maxY * scale);
		return "" + x1 + "," + y1 + "," + x2 + "," + y2;
	}

	public double getSurface() {
		if (minX == Double.MAX_VALUE) {
			return 0;
		}
		return (maxX - minX) * (maxY - minY);
	}

}
