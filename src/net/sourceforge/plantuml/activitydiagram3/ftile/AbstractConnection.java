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
package net.sourceforge.plantuml.activitydiagram3.ftile;

import net.sourceforge.plantuml.graphic.HorizontalAlignment;

public abstract class AbstractConnection implements Connection {

	private final Ftile ftile1;
	private final Ftile ftile2;

	public AbstractConnection(Ftile ftile1, Ftile ftile2) {
		this.ftile1 = ftile1;
		this.ftile2 = ftile2;
	}

	@Override
	public String toString() {
		return "[" + ftile1 + "]->[" + ftile2 + "]";
	}

	final public Ftile getFtile1() {
		return ftile1;
	}

	final public Ftile getFtile2() {
		return ftile2;
	}

	final public HorizontalAlignment arrowHorizontalAlignment() {
		if (ftile1 != null) {
			return ftile1.arrowHorizontalAlignment();
		}
		if (ftile2 != null) {
			return ftile2.arrowHorizontalAlignment();
		}
		return HorizontalAlignment.LEFT;
	}

}
