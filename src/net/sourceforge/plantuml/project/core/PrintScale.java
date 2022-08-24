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
package net.sourceforge.plantuml.project.core;

public enum PrintScale {
	DAILY(1), WEEKLY(4), MONTHLY(15), QUARTERLY(40), YEARLY(60);

	private final double defaultScale;

	private PrintScale(int compress) {
		this.defaultScale = 1.0 / compress;
	}

	public final double getDefaultScale() {
		return defaultScale;
	}

	static public PrintScale fromString(String value) {
		if (value.startsWith("w")) {
			return WEEKLY;
		}
		if (value.startsWith("m")) {
			return MONTHLY;
		}
		if (value.startsWith("q")) {
			return QUARTERLY;
		}
		if (value.startsWith("y")) {
			return YEARLY;
		}
		return DAILY;
	}

}
