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
package net.sourceforge.plantuml.nwdiag.next;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NBox implements Staged {

	private final List<NBar> bars = new ArrayList<>();
	private final NTetris<NBar> tetris = new NTetris<>();

	public void add(NBar bar) {
		if (this.bars.contains(bar))
			return;

		this.bars.add(bar);
		this.tetris.add(bar);
	}

	@Override
	public NStage getStart() {
		NStage result = bars.get(0).getStart();
		for (int i = 1; i < bars.size(); i++)
			result = NStage.getMin(result, bars.get(i).getStart());

		return result;
	}

	@Override
	public NStage getEnd() {
		NStage result = bars.get(0).getEnd();
		for (int i = 1; i < bars.size(); i++)
			result = NStage.getMax(result, bars.get(i).getEnd());

		return result;
	}

	@Override
	public int getNWidth() {
		return tetris.getNWidth();
	}

	public Map<NBar, Integer> getPositions() {
		return tetris.getPositions();
	}

	@Override
	public boolean contains(NStage stage) {
		return stage.compareTo(getStart()) >= 0 && stage.compareTo(getEnd()) <= 0;
	}

}
