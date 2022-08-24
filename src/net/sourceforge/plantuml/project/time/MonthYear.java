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

import java.util.Locale;

public class MonthYear implements Comparable<MonthYear> {

	private final int year;
	private final Month month;

	public static MonthYear create(int year, Month month) {
		return new MonthYear(year, month);
	}

	public String shortName(Locale locale) {
		return month.shortName(locale);
	}

	public String shortNameYYYY(Locale locale) {
		return month.shortName(locale) + " " + year;
	}

	public String longName(Locale locale) {
		return month.longName(locale);
	}

	public String longNameYYYY(Locale locale) {
		return month.longName(locale) + " " + year;
	}

	private MonthYear(int year, Month month) {
		this.year = year;
		this.month = month;
	}

	public int year() {
		return year;
	}

	public MonthYear next() {
		final Month newMonth = month.next();
		final int newYear = newMonth == Month.JANUARY ? year + 1 : year;
		return new MonthYear(newYear, newMonth);
	}

	public Month month() {
		return month;
	}

	private int internalNumber() {
		return year * 100 + month.ordinal();
	}

	@Override
	public String toString() {
		return "" + year + "/" + month;
	}

	@Override
	public int hashCode() {
		return year * 113 + month.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		final MonthYear other = (MonthYear) obj;
		return other.internalNumber() == this.internalNumber();
	}

	public int compareTo(MonthYear other) {
		return this.internalNumber() - other.internalNumber();
	}

}
