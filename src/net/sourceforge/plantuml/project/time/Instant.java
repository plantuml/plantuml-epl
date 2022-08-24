/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2023, Arnaud Roques
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

public class Instant implements Comparable<Instant>, Value {

	private final long ms;

	public static Instant create(long ms) {
		return new Instant(ms);
	}

	public static Instant today() {
		return create(System.currentTimeMillis());
	}

	private Instant(long ms) {
		this.ms = ms;
	}

	public final long getMillis() {
		return ms;
	}

	@Override
	public String toString() {
		return "" + ms;
	}

	@Override
	public int hashCode() {
		return toLong().hashCode();
	}

	private Long toLong() {
		return Long.valueOf(ms);
	}

	@Override
	public boolean equals(Object obj) {
		final Instant other = (Instant) obj;
		return this.ms == other.ms;
	}

	public int compareTo(Instant other) {
		return toLong().compareTo(other.toLong());
	}

}
