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
package net.sourceforge.plantuml.activitydiagram3.ftile.vcompact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sourceforge.plantuml.Dimension2DDouble;
import net.sourceforge.plantuml.activitydiagram3.ftile.AbstractFtile;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileGeometry;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.awt.geom.Dimension2D;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UTranslate;

class FtileForkInnerOverlapped extends AbstractFtile {

	private final List<Ftile> forks = new ArrayList<>();

	public FtileForkInnerOverlapped(List<Ftile> forks) {
		super(forks.get(0).skinParam());
		for (Ftile ftile : forks) {
			this.forks.add(ftile);
		}
	}

	public Swimlane getSwimlaneIn() {
		return forks.get(0).getSwimlaneIn();
	}

	public Swimlane getSwimlaneOut() {
		return getSwimlaneIn();
	}

	public Set<Swimlane> getSwimlanes() {
		return mergeSwimlanes(forks);
	}

	public static Set<Swimlane> mergeSwimlanes(List<Ftile> tiles) {
		final Set<Swimlane> result = new HashSet<>();
		for (Ftile tile : tiles) {
			result.addAll(tile.getSwimlanes());
		}
		return Collections.unmodifiableSet(result);
	}

	public void drawU(UGraphic ug) {
		// final StringBounder stringBounder = ug.getStringBounder();

		final double xpos = 0;
		for (Ftile ftile : forks) {
			ug.apply(UTranslate.dx(xpos)).draw(ftile);
			// final Dimension2D dim = ftile.calculateDimension(stringBounder);
			// xpos += dim.getWidth();
		}
	}

	@Override
	protected FtileGeometry calculateDimensionFtile(StringBounder stringBounder) {
		double height = 0;
		double width = 0;
		for (Ftile ftile : forks) {
			final Dimension2D dim = ftile.calculateDimension(stringBounder);
			// width += dim.getWidth();
			width = Math.max(width, dim.getWidth());
			if (dim.getHeight() > height) {
				height = dim.getHeight();
			}
		}
		final Dimension2D dimTotal = new Dimension2DDouble(width, height);
		return new FtileGeometry(dimTotal, dimTotal.getWidth() / 2, 0, dimTotal.getHeight());
	}

	public UTranslate getTranslateFor(Ftile searched, StringBounder stringBounder) {
		final double xpos = 0;
		for (Ftile ftile : forks) {
			if (ftile == searched) {
				return UTranslate.dx(xpos);
			}
			// final Dimension2D dim = ftile.calculateDimension(stringBounder);
			// xpos += dim.getWidth();
		}
		throw new IllegalArgumentException();
	}

}
