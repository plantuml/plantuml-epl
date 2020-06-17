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

import java.util.HashSet;
import java.util.Set;

public class BoardExplorer {

	private final BoardCollection all = new BoardCollection(new KenavoCostComputer());

	public BoardExplorer(Board init) {
		all.add(init);
	}

	public double getBestCost() {
		return all.getBestCost();
	}

	public Board getBestBoard() {
		return all.getBestBoard();
	}

	public int collectionSize() {
		return all.size();
	}

	public boolean onePass() {
		final Board smallest = all.getAndSetExploredSmallest();
		if (smallest == null) {
			return true;
		}
		final Set<Board> moves = nextBoards(smallest);
		for (Board newBoard : moves) {
			if (all.contains(newBoard)) {
				continue;
			}
			all.add(newBoard);
		}
		return false;
	}

	public Set<Board> nextBoards(Board board) {
		final Set<Board> result = new HashSet<Board>();
		for (Move m : board.getAllPossibleMoves()) {
			final Board copy = board.copy();
			copy.applyMove(m);
			result.add(copy);
		}
		return result;
	}

}
