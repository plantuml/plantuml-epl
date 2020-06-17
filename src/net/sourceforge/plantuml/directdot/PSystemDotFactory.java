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
package net.sourceforge.plantuml.directdot;

import net.sourceforge.plantuml.command.PSystemBasicFactory;
import net.sourceforge.plantuml.core.DiagramType;

public class PSystemDotFactory extends PSystemBasicFactory<PSystemDot> {

	private StringBuilder data;

	public PSystemDotFactory(DiagramType diagramType) {
		super(diagramType);
	}

	@Override
	public PSystemDot init(String startLine) {
		data = null;
		return null;
	}

	@Override
	public PSystemDot executeLine(PSystemDot system, String line) {
		if (system == null && line.matches("(strict\\s+)?(di)?graph\\s+\\w+\\s*\\{")) {
			data = new StringBuilder(line);
			data.append("\n");
			return new PSystemDot(data.toString());
		}
		if (data == null || system == null) {
			return null;
		}
		data.append(line);
		data.append("\n");
		return new PSystemDot(data.toString());
	}
}
