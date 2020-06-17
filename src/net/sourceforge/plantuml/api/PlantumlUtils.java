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
package net.sourceforge.plantuml.api;

import java.util.List;

import net.sourceforge.plantuml.BlockUml;
import net.sourceforge.plantuml.SourceStringReader;
import net.sourceforge.plantuml.core.Diagram;

public class PlantumlUtils {

	public static boolean hasCMapData(String uml) {
		List<BlockUml> blocks = new SourceStringReader(uml).getBlocks();
		if (blocks.size() == 0) {
			uml = "@startuml\n" + uml + "\n@enduml";
			blocks = new SourceStringReader(uml).getBlocks();
			if (blocks.size() == 0) {
				return false;
			}
		}
		final BlockUml block = blocks.get(0);
		final Diagram diagram = block.getDiagram();
		return diagram.hasUrl();
	}

}
