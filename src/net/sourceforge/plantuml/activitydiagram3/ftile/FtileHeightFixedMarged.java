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
package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import net.sourceforge.plantuml.activitydiagram3.LinkRendering;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UTranslate;

public class FtileHeightFixedMarged extends AbstractFtile {

	private final Ftile tile;
	private final double ymargin1;
	private final double ymargin2;

	public FtileHeightFixedMarged(double ymargin1, Ftile tile, double ymargin2) {
		super(tile.skinParam());
		this.tile = tile;
		this.ymargin1 = ymargin1;
		this.ymargin2 = ymargin2;
	}
	
	@Override
	public Collection<Ftile> getMyChildren() {
		return Collections.singleton(tile);
		// return tile.getMyChildren();
	}


	@Override
	public LinkRendering getInLinkRendering() {
		return tile.getInLinkRendering();
	}

	@Override
	public LinkRendering getOutLinkRendering() {
		return tile.getOutLinkRendering();
	}

	public Set<Swimlane> getSwimlanes() {
		return tile.getSwimlanes();
	}

	public Swimlane getSwimlaneIn() {
		return tile.getSwimlaneIn();
	}

	public Swimlane getSwimlaneOut() {
		return tile.getSwimlaneOut();
	}

	@Override
	protected FtileGeometry calculateDimensionFtile(StringBounder stringBounder) {
		final FtileGeometry dim = tile.calculateDimension(stringBounder);
		return dim.translate(getTranslate(stringBounder)).fixedHeight(ymargin1 + dim.getHeight() + ymargin2);
	}

	private UTranslate getTranslate(StringBounder stringBounder) {
		return UTranslate.dy(ymargin1);
	}

	public void drawU(UGraphic ug) {
		ug.apply(getTranslate(ug.getStringBounder())).draw(tile);
	}

}
