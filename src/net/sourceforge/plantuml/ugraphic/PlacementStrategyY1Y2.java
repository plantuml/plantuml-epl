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
package net.sourceforge.plantuml.ugraphic;

import net.sourceforge.plantuml.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.graphic.TextBlock;

public class PlacementStrategyY1Y2 extends AbstractPlacementStrategy {

	public PlacementStrategyY1Y2(StringBounder stringBounder) {
		super(stringBounder);
	}

	public Map<TextBlock, Point2D> getPositions(double width, double height) {
		final double usedHeight = getSumHeight();
		//double maxWidth = getMaxWidth();

		final double space = (height - usedHeight) / (getDimensions().size() + 1);
		final Map<TextBlock, Point2D> result = new LinkedHashMap<TextBlock, Point2D>();
		double y = space;
		for (Map.Entry<TextBlock, Dimension2D> ent : getDimensions().entrySet()) {
			final double x = (width - ent.getValue().getWidth()) / 2;
			result.put(ent.getKey(), new Point2D.Double(x, y));
			y += ent.getValue().getHeight() + space;
		}
		return result;
	}

}
