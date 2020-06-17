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
package net.sourceforge.plantuml.project.timescale;

import net.sourceforge.plantuml.project.time.Day;
import net.sourceforge.plantuml.project.time.DayOfWeek;
import net.sourceforge.plantuml.project.time.GCalendar;
import net.sourceforge.plantuml.project.time.Wink;

public class UnusedTimeScaleWithoutWeekEnd implements TimeScale {

	private final double scale = 16.0;
	private final GCalendar calendar;

	public UnusedTimeScaleWithoutWeekEnd(GCalendar calendar) {
		if (calendar == null) {
			throw new IllegalArgumentException();
		}
		this.calendar = calendar;
	}

	public double getStartingPosition(Wink instant) {
		double result = 0;
		Wink current = (Wink) instant;
		while (current.getWink() > 0) {
			current = current.decrement();
			result += getWidth(current);
		}
		return result;
	}

	public double getWidth(Wink instant) {
		final Day day = calendar.toDayAsDate((Wink) instant);
		final DayOfWeek dayOfWeek = day.getDayOfWeek();
		if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
			return 1;
		}
		return scale;
	}

	public double getEndingPosition(Wink instant) {
		throw new UnsupportedOperationException();
	}

}
