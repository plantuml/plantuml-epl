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
package net.sourceforge.plantuml.skin;

import net.sourceforge.plantuml.graphic.SymbolContext;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.graphic.USymbol;

public enum ActorStyle {

	STICKMAN, AWESOME;

	public USymbol toUSymbol() {
		if (this == STICKMAN) {
			return USymbol.ACTOR_STICKMAN;
		} else if (this == AWESOME) {
			return USymbol.ACTOR_AWESOME;
		}
		throw new IllegalStateException();
	}

	public TextBlock getTextBlock(SymbolContext symbolContext) {
		if (this == STICKMAN) {
			return new ActorStickMan(symbolContext);
		} else if (this == AWESOME) {
			return new ActorAwesome(symbolContext);
		}
		throw new IllegalStateException();
	}

}
