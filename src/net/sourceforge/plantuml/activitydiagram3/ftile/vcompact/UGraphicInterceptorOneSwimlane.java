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

import java.util.Collections;
import java.util.List;
import java.util.Set;

import net.sourceforge.plantuml.activitydiagram3.ftile.Connection;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.activitydiagram3.gtile.GConnection;
import net.sourceforge.plantuml.activitydiagram3.gtile.GPoint;
import net.sourceforge.plantuml.activitydiagram3.gtile.Gtile;
import net.sourceforge.plantuml.graphic.UGraphicDelegator;
import net.sourceforge.plantuml.ugraphic.UChange;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.ULine;
import net.sourceforge.plantuml.ugraphic.UShape;
import net.sourceforge.plantuml.ugraphic.color.HColors;

public class UGraphicInterceptorOneSwimlane extends UGraphicDelegator {

	private final Swimlane swimlane;
	private final List<Swimlane> orderedList;

	public UGraphicInterceptorOneSwimlane(UGraphic ug, Swimlane swimlane, List<Swimlane> orderedList) {
		super(ug);
		this.swimlane = swimlane;
		this.orderedList = orderedList;
	}

	public void draw(UShape shape) {
		// System.err.println("inter=" + shape.getClass());
		if (shape instanceof Ftile) {
			final Ftile tile = (Ftile) shape;
			final Set<Swimlane> swinlanes = tile.getSwimlanes();
			final boolean contained = swinlanes.contains(swimlane);
			if (contained) {
				tile.drawU(this);
				// drawGoto();
			}
		} else if (shape instanceof Gtile) {
			final Gtile tile = (Gtile) shape;
			final Set<Swimlane> swinlanes = tile.getSwimlanes();
			final boolean contained = swinlanes.contains(swimlane);
			if (contained)
				tile.drawU(this);

		} else if (shape instanceof GConnection) {
			final GConnection connection = (GConnection) shape;
			final List<GPoint> hooks = connection.getHooks();
			final GPoint point0 = hooks.get(0);
			final GPoint point1 = hooks.get(1);

			if (point0.match(swimlane) && point1.match(swimlane))
				connection.drawU(this);
			
		} else if (shape instanceof Connection) {
			final Connection connection = (Connection) shape;
			final Ftile tile1 = connection.getFtile1();
			final Ftile tile2 = connection.getFtile2();
			final boolean contained1 = tile1 == null || tile1.getSwimlaneOut() == null
					|| tile1.getSwimlaneOut() == swimlane;
			final boolean contained2 = tile2 == null || tile2.getSwimlaneIn() == null
					|| tile2.getSwimlaneIn() == swimlane;

			if (contained1 && contained2) {
				connection.drawU(this);
			}
		} else {
			getUg().draw(shape);
			// System.err.println("Drawing " + shape);
		}

	}

	private void drawGoto() {
		final UGraphic ugGoto = getUg().apply(HColors.GREEN).apply(HColors.GREEN.bg());
		ugGoto.draw(new ULine(100, 100));
	}

	public UGraphic apply(UChange change) {
		return new UGraphicInterceptorOneSwimlane(getUg().apply(change), swimlane, orderedList);
	}

	public final Swimlane getSwimlane() {
		return swimlane;
	}

	public final List<Swimlane> getOrderedListOfAllSwimlanes() {
		return Collections.unmodifiableList(orderedList);
	}

}
