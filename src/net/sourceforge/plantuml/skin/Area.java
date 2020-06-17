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
package net.sourceforge.plantuml.skin;

import java.awt.geom.Dimension2D;

import net.sourceforge.plantuml.Dimension2DDouble;

public class Area {

	private final Dimension2D dimensionToUse;
	private double deltaX1;

	@Override
	public String toString() {
		return dimensionToUse.toString() + " (" + deltaX1 + ")";
	}

	public Area(Dimension2D dimensionToUse) {
		this.dimensionToUse = dimensionToUse;
	}

	public Area(double with, double height) {
		this(new Dimension2DDouble(with, height));
	}

	public Dimension2D getDimensionToUse() {
		return dimensionToUse;
	}

	public void setDeltaX1(double deltaX1) {
		this.deltaX1 = deltaX1;
	}

	public final double getDeltaX1() {
		return deltaX1;
	}

}
