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
package net.sourceforge.plantuml.sequencediagram.graphic;

import java.util.Collection;
import java.util.Collections;

import net.sourceforge.plantuml.graphic.StringBounder;

public class ParticipantBoxSimple implements Pushable {

	private double pos = 0;
	private final String name;

	public ParticipantBoxSimple(double pos) {
		this(pos, null);
	}

	public ParticipantBoxSimple(double pos, String name) {
		this.pos = pos;
		this.name = name;
	}

	@Override
	public String toString() {
		return name == null ? super.toString() : name;
	}

	public double getCenterX(StringBounder stringBounder) {
		return pos;
	}

	public void pushToLeft(double deltaX) {
		pos += deltaX;
	}
	
	public double getPreferredWidth(StringBounder stringBounder) {
		return 0;
	}

	public Collection<Segment> getDelays(StringBounder stringBounder) {
		return Collections.emptyList();
	}


}
