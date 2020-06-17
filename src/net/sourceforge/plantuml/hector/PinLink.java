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
package net.sourceforge.plantuml.hector;

public class PinLink {

	private final Pin pin1;
	private final Pin pin2;
	private final Object userData;
	private final int length;

	public PinLink(Pin pin1, Pin pin2, int length, Object userData) {
		if (length < 1) {
			throw new IllegalArgumentException();
		}
		this.pin1 = pin1;
		this.pin2 = pin2;
		this.userData = userData;
		this.length = length;
	}

	public boolean contains(Pin pin) {
		return pin == pin1 || pin == pin2;
	}

	public boolean doesTouch(PinLink other) {
		return other.contains(pin1) || other.contains(pin2);
	}

	public Pin getPin1() {
		return pin1;
	}

	public Pin getPin2() {
		return pin2;
	}

	public int getLengthDot() {
		return length;
	}

	public int getLengthStandard() {
		return length - 1;
	}
}
