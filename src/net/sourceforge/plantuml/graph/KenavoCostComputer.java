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
package net.sourceforge.plantuml.graph;

import net.sourceforge.plantuml.geom.LineSegmentInt;

public class KenavoCostComputer implements CostComputer {

	public double getCost(Board board) {
		double result = 0;
		for (ALink link1 : board.getLinks()) {
			for (ALink link2 : board.getLinks()) {
				result += getCost(board, link1, link2);
			}
		}
		return result;
	}

	LineSegmentInt getLineSegment(Board board, ALink link) {
		final ANode n1 = link.getNode1();
		final ANode n2 = link.getNode2();
		return new LineSegmentInt(board.getCol(n1), n1.getRow(), board.getCol(n2), n2.getRow());
	}

	private double getCost(Board board, ALink link1, ALink link2) {
		final LineSegmentInt seg1 = getLineSegment(board, link1);
		final LineSegmentInt seg2 = getLineSegment(board, link2);

		final double len1 = getLength(link1, seg1, board);
		final double len2 = getLength(link2, seg2, board);

		return len1 * len2 * Math.exp(-seg1.getDistance(seg2));
	}

	private double getLength(ALink link, final LineSegmentInt seg, Board board) {
		double coef = 1;
		if (link.getNode1().getRow() == link.getNode2().getRow()
				&& board.getDirection(link) != board.getInitialDirection(link)) {
			coef = 1.1;
		}

		return seg.getLength() * coef;
	}

}
