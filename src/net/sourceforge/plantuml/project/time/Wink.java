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
package net.sourceforge.plantuml.project.time;

import net.sourceforge.plantuml.project.Value;

public class Wink implements Value, Comparable<Wink> {

	private final int wink;

	public Wink(int wink) {
		this.wink = wink;
	}

	@Override
	public String toString() {
		return "(Wink +" + wink + ")";
	}

	public Wink increment() {
		return new Wink(wink + 1);
	}

	public Wink decrement() {
		return new Wink(wink - 1);
	}

	public final int getWink() {
		return wink;
	}

	public int compareTo(Wink other) {
		return this.wink - other.wink;
	}

	public String toShortString() {
		return "" + (wink + 1);
	}

	public static Wink min(Wink wink1, Wink wink2) {
		if (wink2.wink < wink1.wink) {
			return wink2;
		}
		return wink1;
	}

	public static Wink max(Wink wink1, Wink wink2) {
		if (wink2.wink > wink1.wink) {
			return wink2;
		}
		return wink1;
	}

}
