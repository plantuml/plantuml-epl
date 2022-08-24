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
package net.sourceforge.plantuml.activitydiagram3.ftile.vcompact.cond;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.Direction;
import net.sourceforge.plantuml.activitydiagram3.Branch;
import net.sourceforge.plantuml.activitydiagram3.ftile.AbstractConnection;
import net.sourceforge.plantuml.activitydiagram3.ftile.Arrows;
import net.sourceforge.plantuml.activitydiagram3.ftile.Connection;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileGeometry;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileUtils;
import net.sourceforge.plantuml.activitydiagram3.ftile.Hexagon;
import net.sourceforge.plantuml.activitydiagram3.ftile.Snake;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.graphic.Rainbow;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.graphic.VerticalAlignment;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UPolygon;
import net.sourceforge.plantuml.ugraphic.UTranslate;

public class FtileSwitchWithManyLinks extends FtileSwitchWithDiamonds {

	private final Rainbow arrowColor;
	private final double margin = 10;

	public FtileSwitchWithManyLinks(List<Ftile> tiles, List<Branch> branches, Swimlane in, Ftile diamond1,
			Ftile diamond2, StringBounder stringBounder, Rainbow arrowColor) {
		super(tiles, branches, in, diamond1, diamond2, stringBounder);
		this.arrowColor = arrowColor;
	}

	class ConnectionHorizontalThenVertical extends AbstractConnection {

		private final Branch branch;

		public ConnectionHorizontalThenVertical(Ftile tile, Branch branch) {
			super(diamond1, tile);
			this.branch = branch;
		}

		public void drawU(UGraphic ug) {
			final StringBounder stringBounder = ug.getStringBounder();
			final Point2D p1 = getP1(stringBounder);
			final Point2D p2 = getP2(stringBounder);

			final double x1 = p1.getX();
			final double y1 = p1.getY();
			final double x2 = p2.getX();
			final double y2 = p2.getY();

			final Snake snake = Snake.create(skinParam(), arrowColor, Arrows.asToDown())
					.withLabel(branch.getTextBlockPositive(), arrowHorizontalAlignment());
			snake.addPoint(x1, y1);

			if (isLast() && p1.getX() > p2.getX()) {
				final FtileGeometry dimDiamond1 = diamond1.calculateDimension(stringBounder);
				snake.addPoint(x1 + Hexagon.hexagonHalfSize, y1);
				snake.addPoint(x1 + Hexagon.hexagonHalfSize, y1 + dimDiamond1.getHeight());
				snake.addPoint(x2, y1 + dimDiamond1.getHeight());
			} else {
				snake.addPoint(x2, y1);
			}

			snake.addPoint(x2, y2);

			ug.draw(snake);
		}

		private Point2D getP1(StringBounder stringBounder) {
			final FtileGeometry dimDiamond1 = diamond1.calculateDimension(stringBounder);
			final Point2D pt;
			if (getFtile2() == tiles.get(0))
				pt = dimDiamond1.getPointD();
			else if (isLast())
				pt = dimDiamond1.getPointB();
			else
				throw new IllegalStateException();

			return getTranslateDiamond1(stringBounder).getTranslated(pt);
		}

		private boolean isLast() {
			return getFtile2() == tiles.get(tiles.size() - 1);
		}

		private Point2D getP2(final StringBounder stringBounder) {
			return getTranslateOf(getFtile2(), stringBounder)
					.getTranslated(getFtile2().calculateDimension(stringBounder).getPointIn());
		}

	}

	class ConnectionVerticalThenHorizontal extends AbstractConnection {

		private final TextBlock outLabel;

		public ConnectionVerticalThenHorizontal(Ftile tile, TextBlock outLabel) {
			super(tile, diamond2);
			this.outLabel = outLabel;
		}

		public void drawU(UGraphic ug) {
			final StringBounder stringBounder = ug.getStringBounder();
			final FtileGeometry geo = getFtile1().calculateDimension(stringBounder);
			if (geo.hasPointOut() == false)
				return;

			final Point2D p1 = getP1(stringBounder);
			final double x1 = p1.getX();
			final double y1 = p1.getY();

			final FtileGeometry dimDiamond2 = diamond2.calculateDimension(stringBounder);
			final Point2D ptA = getTranslateDiamond2(stringBounder).getTranslated(dimDiamond2.getPointA());
			final Point2D ptB = getTranslateDiamond2(stringBounder).getTranslated(dimDiamond2.getPointB());
			final Point2D ptD = getTranslateDiamond2(stringBounder).getTranslated(dimDiamond2.getPointD());
			final Point2D p2;
			final UPolygon arrow;
			final Direction direction;
			if (x1 < ptD.getX()) {
				p2 = ptD;
				arrow = Arrows.asToRight();
				direction = Direction.RIGHT;
			} else if (x1 > ptB.getX()) {
				p2 = ptB;
				arrow = Arrows.asToLeft();
				direction = Direction.LEFT;
			} else {
				p2 = ptA;
				arrow = Arrows.asToDown();
				direction = Direction.DOWN;
			}

			final double x2 = p2.getX();
			final double y2 = p2.getY();

			final Snake snake = Snake.create(skinParam(), arrowColor, arrow).withLabel(outLabel,
					VerticalAlignment.CENTER);
			snake.addPoint(x1, y1);
			if (direction == Direction.LEFT && x2 > x1 - 10) {
				snake.addPoint(x1, y2 - 8);
				snake.addPoint(x1 + 12, y2 - 8);
				snake.addPoint(x1 + 12, y2);
			} else {
				snake.addPoint(x1, y2);
			}
			snake.addPoint(x2, y2);
			ug.draw(snake);
		}

		private Point2D getP1(StringBounder stringBounder) {
			return getTranslateOf(getFtile1(), stringBounder)
					.getTranslated(getFtile1().calculateDimension(stringBounder).getPointOut());
		}

	}

	class ConnectionVerticalTop extends AbstractConnection {

		private final Branch branch;

		public ConnectionVerticalTop(Ftile tile, Branch branch) {
			super(diamond1, tile);
			this.branch = branch;
		}

		public void drawU(UGraphic ug) {
			final StringBounder stringBounder = ug.getStringBounder();
			final FtileGeometry dimDiamond1 = diamond1.calculateDimension(stringBounder);
			final UTranslate translateDiamond1 = getTranslateDiamond1(stringBounder);
			final Point2D p1b = translateDiamond1.getTranslated(dimDiamond1.getPointB());
			final Point2D p1c = translateDiamond1.getTranslated(dimDiamond1.getPointC());
			final Point2D p1d = translateDiamond1.getTranslated(dimDiamond1.getPointD());

			final Point2D p2 = getP2(stringBounder);
			final double x2 = p2.getX();
			final double y2 = p2.getY();

			final Snake snake = Snake.create(skinParam(), arrowColor, Arrows.asToDown())
					.withLabel(branch.getTextBlockPositive(), VerticalAlignment.CENTER);
			if (x2 < p1d.getX() - margin || x2 > p1b.getX() + margin) {
				snake.addPoint(x2, p1d.getY());
				snake.addPoint(x2, y2);
			} else {
				final double x1 = p1c.getX();
				final double y1 = p1c.getY();

				final double ym = (y1 * 2 + y2) / 3;
				snake.addPoint(x1, y1);
				snake.addPoint(x1, ym);
				snake.addPoint(x2, ym);
				snake.addPoint(x2, y2);
			}
			ug.draw(snake);
		}

		private Point2D getP2(final StringBounder stringBounder) {
			return getTranslateOf(getFtile2(), stringBounder)
					.getTranslated(getFtile2().calculateDimension(stringBounder).getPointIn());
		}

	}

	class ConnectionVerticalBottom extends AbstractConnection {

		final private TextBlock outLabel;

		public ConnectionVerticalBottom(Ftile tile, TextBlock textBlock) {
			super(tile, diamond2);
			this.outLabel = textBlock;
		}

		public void drawU(UGraphic ug) {
			final StringBounder stringBounder = ug.getStringBounder();
			final Point2D p1 = getP1(stringBounder);
			final FtileGeometry dimDiamond2 = diamond2.calculateDimension(stringBounder);
			final UTranslate translateDiamond2 = getTranslateDiamond2(stringBounder);
			final Point2D p2a = translateDiamond2.getTranslated(dimDiamond2.getPointA());
			final Point2D p2b = translateDiamond2.getTranslated(dimDiamond2.getPointB());
			final Point2D p2d = translateDiamond2.getTranslated(dimDiamond2.getPointD());

			final FtileGeometry dimDiamond1 = diamond1.calculateDimension(stringBounder);
			final UTranslate translateDiamond1 = getTranslateDiamond1(stringBounder);
			final Point2D p1b = translateDiamond1.getTranslated(dimDiamond1.getPointB());
			final Point2D p1c = translateDiamond1.getTranslated(dimDiamond1.getPointC());
			final Point2D p1d = translateDiamond1.getTranslated(dimDiamond1.getPointD());

			final double x1 = p1.getX();
			final double y1 = p1.getY();
			final double x2 = p2a.getX();
			final double y2 = p2a.getY();

			final double ym = (y1 + y2) / 2;

			final Snake snake = Snake.create(skinParam(), arrowColor, Arrows.asToDown()).withLabel(outLabel,
					VerticalAlignment.CENTER);

			if (x1 < p1d.getX() - margin || x1 > p1b.getX() + margin) {
				snake.addPoint(x1, y1);
				snake.addPoint(x1, p2d.getY());
			} else {
				snake.addPoint(x1, y1);
				snake.addPoint(x1, ym);
				snake.addPoint(x2, ym);
				snake.addPoint(x2, y2);
			}

			ug.draw(snake);
		}

		private Point2D getP1(StringBounder stringBounder) {
			return getTranslateOf(getFtile1(), stringBounder)
					.getTranslated(getFtile1().calculateDimension(stringBounder).getPointOut());
		}

	}

	@Override
	protected double getYdelta1a(StringBounder stringBounder) {
		double max = 10;
		for (Branch branch : branches) {
			max = Math.max(max, branch.getTextBlockPositive().calculateDimension(stringBounder).getHeight());
		}
		if (mode == Mode.BIG_DIAMOND) {
			final double diamondHeight = diamond1.calculateDimension(stringBounder).getHeight();
			max += diamondHeight / 2;
		}
		return max + 10;
	}

	public Ftile addLinks(StringBounder stringBounder) {
		final List<Connection> conns = new ArrayList<>();
		addIngoingArrows(conns);
		addOutgoingArrows(stringBounder, conns);
		return FtileUtils.addConnection(this, conns);
	}

	private void addIngoingArrows(final List<Connection> conns) {
		conns.add(new ConnectionHorizontalThenVertical(tiles.get(0), branches.get(0)));
		conns.add(new ConnectionHorizontalThenVertical(tiles.get(tiles.size() - 1), branches.get(tiles.size() - 1)));
		for (int i = 1; i < tiles.size() - 1; i++) {
			final Ftile tile = tiles.get(i);
			conns.add(new ConnectionVerticalTop(tile, branches.get(i)));
		}
	}

	private void addOutgoingArrows(StringBounder stringBounder, final List<Connection> conns) {
		final int firstOutgoingArrow = getFirstOutgoingArrow(stringBounder);
		final int lastOutgoingArrow = getLastOutgoingArrow(stringBounder);
		if (firstOutgoingArrow < tiles.size())
			conns.add(new ConnectionVerticalThenHorizontal(tiles.get(firstOutgoingArrow),
					branches.get(firstOutgoingArrow).getTextBlockSpecial()));
		if (lastOutgoingArrow > 0)
			conns.add(new ConnectionVerticalThenHorizontal(tiles.get(lastOutgoingArrow),
					branches.get(lastOutgoingArrow).getTextBlockSpecial()));
		for (int i = firstOutgoingArrow + 1; i < lastOutgoingArrow; i++) {
			final Ftile tile = tiles.get(i);
			if (tile.calculateDimension(stringBounder).hasPointOut())
				conns.add(new ConnectionVerticalBottom(tile, branches.get(i).getTextBlockSpecial()));

		}
	}

	private int getFirstOutgoingArrow(StringBounder stringBounder) {
		for (int i = 0; i < tiles.size() - 1; i++) {
			final Ftile tile = tiles.get(i);
			if (tile.calculateDimension(stringBounder).hasPointOut())
				return i;

		}
		return tiles.size();
	}

	private int getLastOutgoingArrow(StringBounder stringBounder) {
		for (int i = tiles.size() - 1; i >= 0; i--) {
			final Ftile tile = tiles.get(i);
			if (tile.calculateDimension(stringBounder).hasPointOut())
				return i;

		}
		return -1;
	}

}
