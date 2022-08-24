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
package net.sourceforge.plantuml.project.solver;

import net.sourceforge.plantuml.project.Load;
import net.sourceforge.plantuml.project.core.TaskAttribute;
import net.sourceforge.plantuml.project.core3.Histogram;
import net.sourceforge.plantuml.project.core3.HistogramSimple;
import net.sourceforge.plantuml.project.core3.TaskLoad;
import net.sourceforge.plantuml.project.core3.TaskLoadImpl;
import net.sourceforge.plantuml.project.time.Instant;

public class Solver3 extends AbstractSolver {

	private final Histogram workLoad;

	public Solver3(Histogram workLoad) {
		this.workLoad = workLoad;
	}

	public TaskLoad solveForward(long totalLoad, long start) {
		final HistogramSimple resultLoad = new HistogramSimple();
		final TaskLoadImpl result = new TaskLoadImpl(resultLoad);

		start = workLoad.getNext(start - 1);
		result.setStart(start);
		long currentTime = start;
		while (totalLoad > 0) {
			final long tmpWorkLoad = workLoad.getValueAt(currentTime);
			final long nextChange = workLoad.getNext(currentTime);
			final long duration = nextChange - currentTime;
			if (duration <= 0)
				throw new IllegalArgumentException();

			final long partialLoad = duration * tmpWorkLoad;
			resultLoad.put(currentTime, tmpWorkLoad);

			if (partialLoad >= totalLoad) {
				final long end = currentTime + totalLoad / tmpWorkLoad;
				result.setEnd(end);
				resultLoad.put(end, 0);
				return result;
			}
			totalLoad -= partialLoad;
			currentTime = nextChange;
		}
		throw new UnsupportedOperationException();
	}

	public TaskLoad solveBackward(long totalLoad, long end) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected Instant computeEnd() {
		Instant start = (Instant) values.get(TaskAttribute.START);
		int fullLoad = ((Load) values.get(TaskAttribute.LOAD)).getFullLoad();
		final TaskLoad taskLoad = solveBackward(fullLoad, start.getMillis());
		return Instant.create(taskLoad.getEnd());
	}

	@Override
	protected Instant computeStart() {
		throw new UnsupportedOperationException();
	}

}
