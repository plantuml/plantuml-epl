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
package net.sourceforge.plantuml.postit;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sourceforge.plantuml.graphic.StringBounder;

public class AreaLayoutFixedWidth implements AreaLayout {

	private final double width;

	public AreaLayoutFixedWidth(double width) {
		this.width = width;
	}

	public Map<PostIt, Point2D> getPositions(Collection<PostIt> all, StringBounder stringBounder) {
		double x = 0;
		double y = 0;
		double maxY = 0;
		final Map<PostIt, Point2D> result = new LinkedHashMap<PostIt, Point2D>();

		for (PostIt p : all) {
			final Dimension2D dim = p.getDimension(stringBounder);
			if (x + dim.getWidth() > width) {
				x = 0;
				y = maxY;
			}
			result.put(p, new Point2D.Double(x, y));
			x += dim.getWidth();
			maxY = Math.max(maxY, y + dim.getHeight());
		}

		return Collections.unmodifiableMap(result);
	}

}
