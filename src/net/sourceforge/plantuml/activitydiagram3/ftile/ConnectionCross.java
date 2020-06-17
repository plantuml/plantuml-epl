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

import net.sourceforge.plantuml.ugraphic.UGraphic;

public class ConnectionCross extends AbstractConnection {

	private final Connection connection;

	public ConnectionCross(Connection connection) {
		super(connection.getFtile1(), connection.getFtile2());
		this.connection = connection;
	}

	public void drawU(UGraphic ug) {
		if (connection instanceof ConnectionTranslatable) {
			final ConnectionTranslatable conn = (ConnectionTranslatable) connection;

			final Swimlane swimlane1 = getFtile1().getSwimlaneOut();
			final Swimlane swimlane2 = getFtile2().getSwimlaneIn();
			if (swimlane1 == null) {
				return;
				// throw new IllegalStateException("" + getFtile1().getClass());
			}
			if (swimlane2 == null) {
				return;
				// throw new IllegalStateException("" + getFtile2().getClass());
			}
			conn.drawTranslate(ug, swimlane1.getTranslate(), swimlane2.getTranslate());
		}
	}
}
