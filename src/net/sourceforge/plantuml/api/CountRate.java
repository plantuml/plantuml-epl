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
package net.sourceforge.plantuml.api;

public final class CountRate {

	private final MagicArray lastMinute = new MagicArray(60);
	private final MagicArray lastHour = new MagicArray(60);
	private final MagicArray lastDay = new MagicArray(140);

	public void increment() {
		final long now = System.currentTimeMillis();
		lastMinute.incKey(now / 1000L);
		lastHour.incKey(now / (60 * 1000L));
		lastDay.incKey(now / (10 * 60 * 1000L));
	}

	public void increment(int value) {
		final long now = System.currentTimeMillis();
		lastMinute.incKey(now / 1000L, value);
		lastHour.incKey(now / (60 * 1000L), value);
		lastDay.incKey(now / (10 * 60 * 1000L), value);
	}

	public long perMinute() {
		return lastMinute.getSum();
	}

	public long perHour() {
		return lastHour.getSum();
	}

	public long perDay() {
		return lastDay.getSum();
	}

	public long perMinuteMax() {
		return lastMinute.getMaxSum();
	}

	public long perHourMax() {
		return lastHour.getMaxSum();
	}

	public long perDayMax() {
		return lastDay.getMaxSum();
	}

}
