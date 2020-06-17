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
package net.sourceforge.plantuml.sequencediagram;

import net.sourceforge.plantuml.graphic.SymbolContext;

public class LifeEvent extends AbstractEvent implements Event {

	private final Participant p;
	private final LifeEventType type;
	private final SymbolContext backcolor;

	public LifeEvent(Participant p, LifeEventType type, SymbolContext backcolor) {
		this.p = p;
		this.type = type;
		this.backcolor = backcolor;
	}

	@Override
	public String toString() {
		return "LifeEvent:" + p + " " + type;
	}

	public Participant getParticipant() {
		return p;
	}

	public LifeEventType getType() {
		return type;
	}

	public SymbolContext getSpecificColors() {
		return backcolor;
	}

	public boolean dealWith(Participant someone) {
		return this.p == someone;
	}

	public boolean isActivate() {
		return type == LifeEventType.ACTIVATE;
	}

	public boolean isDeactivateOrDestroy() {
		return type == LifeEventType.DEACTIVATE || type == LifeEventType.DESTROY;
	}

	@Deprecated
	public boolean isDestroy() {
		return type == LifeEventType.DESTROY;
	}

	public boolean isDestroy(Participant p) {
		return this.p == p && type == LifeEventType.DESTROY;
	}

	private AbstractMessage message;

	public void setMessage(AbstractMessage message) {
		this.message = message;
	}

	public AbstractMessage getMessage() {
		return message;
	}

}
