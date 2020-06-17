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

class RealMiddle2 extends RealMoveable {

	private final RealMoveable p1;
	private final RealMoveable p2;

	RealMiddle2(RealMoveable p1, RealMoveable p2) {
		super(p1.getLine(), "middle");
		assert p1.getLine() == p2.getLine();
		this.p1 = p1;
		this.p2 = p2;
	}

	@Override
	double getCurrentValueInternal() {
		return (p1.getCurrentValue() + p2.getCurrentValue()) / 2;
	}

	// public Real addFixed(double diff) {
	// return new RealMiddle2(p1, p2, delta + diff);
	// }

	public Real addAtLeast(double delta) {
		throw new UnsupportedOperationException();
	}

	public void ensureBiggerThan(Real other) {
		getLine().addForce(new PositiveForce(other, this, 0));
	}

	@Override
	void move(double delta) {
		p1.move(delta / 2);
		p2.move(delta / 2);
	}

}
