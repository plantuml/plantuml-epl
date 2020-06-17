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
package net.sourceforge.plantuml.real;

class PositiveForce {

	private final Real fixedPoint;
	private final RealMoveable movingPoint;
	private final double minimunDistance;
	private final boolean trace = false;
	private final Throwable creationPoint;

	public PositiveForce(Real fixedPoint, RealMoveable movingPoint, double minimunDistance) {
		if (fixedPoint == movingPoint) {
			throw new IllegalArgumentException();
		}
		this.fixedPoint = fixedPoint;
		this.movingPoint = movingPoint;
		this.minimunDistance = minimunDistance;
		this.creationPoint = new Throwable();
		this.creationPoint.fillInStackTrace();
	}

	@Override
	public String toString() {
		return "PositiveForce fixed=" + fixedPoint + " moving=" + movingPoint + " min=" + minimunDistance;
	}

	public boolean apply() {
		if (trace) {
			System.err.println("apply " + this);
		}
		final double movingPointValue = movingPoint.getCurrentValue();
		final double fixedPointValue;
		try {
			fixedPointValue = fixedPoint.getCurrentValue();
		} catch (IllegalStateException e) {
			System.err.println("Pb with force " + this);
			System.err.println("This force has been created here:");
			creationPoint.printStackTrace();
			System.err.println("The fixed point has been created here: " + fixedPoint);
			fixedPoint.printCreationStackTrace();
			throw e;
		}
		final double distance = movingPointValue - fixedPointValue;
		final double diff = distance - minimunDistance;
		if (diff >= 0) {
			if (trace) {
				System.err.println("Not using ");
			}
			return false;
		}
		if (trace) {
			System.err.println("moving " + (-diff) + " " + movingPoint);
		}
		movingPoint.move(-diff);
		return true;
	}

}
