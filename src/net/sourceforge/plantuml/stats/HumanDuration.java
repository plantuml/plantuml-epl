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
package net.sourceforge.plantuml.stats;

class HumanDuration {

	private final long duration;

	public HumanDuration(long duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		long time = duration;
//		final long ms = time % 1000L;
		time = time / 1000;
		final long sec = time % 60;
		time = time / 60;
		final long min = time % 60;
		time = time / 60;
		final long hour = time % 24;
		final long day = time / 24;
		if (day > 0) {
			return String.format("%dd %02dh%02dm%02ds", day, hour, min, sec);
		}
		if (hour > 0) {
			return String.format("%dh%02dm%02ds", hour, min, sec);
		}
		if (min > 0) {
			return String.format("%dm%02ds", min, sec);
		}
		return String.format("%ds", sec);
	}

}
