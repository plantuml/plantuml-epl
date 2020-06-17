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
package net.sourceforge.plantuml.project.core;

import net.sourceforge.plantuml.project.lang.Complement;
import net.sourceforge.plantuml.project.time.Wink;

public class TaskInstant implements Complement {

	private final Moment task;
	private final TaskAttribute attribute;
	private final int delta;

	public TaskInstant(Moment task, TaskAttribute attribute) {
		this(task, attribute, 0);
	}

	private TaskInstant(Moment task, TaskAttribute attribute, int delta) {
		this.task = task;
		this.attribute = attribute;
		this.delta = delta;
		if (attribute != TaskAttribute.START && attribute != TaskAttribute.END) {
			throw new IllegalArgumentException();
		}
	}

	public TaskInstant withDelta(int newDelta) {
		return new TaskInstant(task, attribute, newDelta);
	}

	private Wink manageDelta(Wink value) {
		if (delta > 0) {
			for (int i = 0; i < delta; i++) {
				value = value.increment();
			}
		}
		if (delta < 0) {
			for (int i = 0; i < -delta; i++) {
				value = value.decrement();
			}
		}
		return value;
	}

	public Wink getInstantPrecise() {
		if (attribute == TaskAttribute.START) {
			return manageDelta(task.getStart());
		}
		if (attribute == TaskAttribute.END) {
			return manageDelta(task.getEnd().increment());
		}
		throw new IllegalStateException();
	}

	public Wink getInstantTheorical() {
		if (attribute == TaskAttribute.START) {
			return manageDelta(task.getStart());
		}
		if (attribute == TaskAttribute.END) {
			return manageDelta(task.getEnd());
		}
		throw new IllegalStateException();
	}

	@Override
	public String toString() {
		return attribute.toString() + " of " + task;
	}

	public final Moment getMoment() {
		return task;
	}

	public final boolean isTask() {
		return task instanceof Task;
	}

	public final TaskAttribute getAttribute() {
		return attribute;
	}

}
