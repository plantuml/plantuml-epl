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
package net.sourceforge.plantuml.project;

import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.project.core.TaskInstant;
import net.sourceforge.plantuml.project.lang.Complement;
import net.sourceforge.plantuml.project.time.Wink;
import net.sourceforge.plantuml.project.timescale.TimeScale;

public class GanttConstraint implements Complement {

	private final TaskInstant source;
	private final TaskInstant dest;

	public GanttConstraint(TaskInstant source, TaskInstant dest) {
		this.source = source;
		this.dest = dest;
	}

	@Override
	public String toString() {
		return source.toString() + " --> " + dest.toString();
	}

	public UDrawable getUDrawable(final TimeScale timeScale) {
		return new GanttArrow(timeScale, source, dest);
	}

	public boolean isHidden(Wink min, Wink max) {
		if (isHidden(source.getInstantPrecise(), min, max)) {
			return true;
		}
		if (isHidden(dest.getInstantPrecise(), min, max)) {
			return true;
		}
		return false;
	}

	private boolean isHidden(Wink now, Wink min, Wink max) {
		if (now.compareTo(min) < 0) {
			return true;
		}
		if (now.compareTo(max) > 0) {
			return true;
		}
		return false;
	}
}
