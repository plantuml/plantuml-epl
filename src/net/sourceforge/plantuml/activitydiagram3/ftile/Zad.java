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
package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.ugraphic.MinMax;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.color.HColorUtils;

public class Zad {

	private final List<MinMax> rectangles = new ArrayList<MinMax>();

	public void add(MinMax rect) {
		// System.err.println("add " + rect);
		this.rectangles.add(rect);

	}

	public void drawDebug(UGraphic ug) {
		ug = ug.apply(HColorUtils.BLUE.bg()).apply(HColorUtils.RED_LIGHT);
		for (MinMax minMax : rectangles) {
			System.err.println("minmax=" + minMax);
			minMax.drawGrey(ug);
		}

	}

	public boolean doesHorizontalCross(Snake snake) {
		for (MinMax minMax : rectangles) {
			if (snake.doesHorizontalCross(minMax)) {
				return true;
			}
		}
		return false;
	}
}
