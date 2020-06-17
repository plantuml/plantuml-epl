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
package net.sourceforge.plantuml.svek.extremity;

import java.awt.geom.Point2D;

import net.sourceforge.plantuml.graphic.UDrawable;

public abstract class Extremity implements UDrawable {


	protected double manageround(double angle) {
		final double deg = angle * 180.0 / Math.PI;
		if (isCloseTo(0, deg)) {
			return 0;
		}
		if (isCloseTo(90, deg)) {
			return 90.0 * Math.PI / 180.0;
		}
		if (isCloseTo(180, deg)) {
			return 180.0 * Math.PI / 180.0;
		}
		if (isCloseTo(270, deg)) {
			return 270.0 * Math.PI / 180.0;
		}
		if (isCloseTo(360, deg)) {
			return 0;
		}
		return angle;
	}

	private boolean isCloseTo(double value, double variable) {
		if (Math.abs(value - variable) < 0.05) {
			return true;
		}
		return false;
	}
	
	public abstract Point2D somePoint();
	
	public Point2D isTooSmallSoGiveThePointCloserToThisOne(Point2D pt) {
		return null;
	}

}
