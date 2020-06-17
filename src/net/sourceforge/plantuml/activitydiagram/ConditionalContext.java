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
package net.sourceforge.plantuml.activitydiagram;

import net.sourceforge.plantuml.Direction;
import net.sourceforge.plantuml.cucadiagram.IEntity;
import net.sourceforge.plantuml.cucadiagram.LeafType;

public class ConditionalContext {

	private final IEntity branch;
	private final Direction direction;
	private final ConditionalContext parent;

	public ConditionalContext(ConditionalContext parent, IEntity branch, Direction direction) {
		if (branch == null) {
			throw new IllegalArgumentException("branch is null");
		}
		if (branch.getLeafType() != LeafType.BRANCH) {
			throw new IllegalArgumentException();
		}
		this.branch = branch;
		this.direction = direction;
		this.parent = parent;
	}

	public Direction getDirection() {
		return direction;
	}

	public final ConditionalContext getParent() {
		return parent;
	}

	public final IEntity getBranch() {
		return branch;
	}

}
