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
package net.sourceforge.plantuml.salt;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.api.ThemeStyle;
import net.sourceforge.plantuml.command.PSystemBasicFactory;
import net.sourceforge.plantuml.core.DiagramType;
import net.sourceforge.plantuml.core.UmlSource;

public class PSystemSaltFactory extends PSystemBasicFactory<PSystemSalt> {

	public PSystemSaltFactory(DiagramType diagramType) {
		super(diagramType);
	}

	@Override
	public PSystemSalt initDiagram(ThemeStyle style, UmlSource source, String startLine) {
		if (getDiagramType() == DiagramType.UML) {
			return null;
		} else if (getDiagramType() == DiagramType.SALT) {
			return new PSystemSalt(style, source);
		} else {
			throw new IllegalStateException(getDiagramType().name());
		}

	}

	@Override
	public PSystemSalt executeLine(ThemeStyle style, UmlSource source, PSystemSalt system, String line) {
		if (system == null && line.replace('\t', ' ').trim().equals("salt")) {
			return new PSystemSalt(style, source);
		}
		if (system == null) {
			return null;
		}
		system.add(StringUtils.trin(line));
		return system;
	}

}
