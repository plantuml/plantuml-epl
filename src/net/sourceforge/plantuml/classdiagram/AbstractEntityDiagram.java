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
package net.sourceforge.plantuml.classdiagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.sourceforge.plantuml.ISkinSimple;
import net.sourceforge.plantuml.core.DiagramDescription;
import net.sourceforge.plantuml.cucadiagram.CucaDiagram;

public abstract class AbstractEntityDiagram extends CucaDiagram {

	public AbstractEntityDiagram(ISkinSimple orig) {
		super(orig);
	}

	final protected List<String> getDotStrings() {
		final List<String> def = Arrays.asList("nodesep=.35;", "ranksep=0.8;", "edge [fontsize=11,labelfontsize=11];",
				"node [fontsize=11,height=.35,width=.55];");
		if (getPragma().isDefine("graphattributes") == false) {
			return def;
		}
		final String attribute = getPragma().getValue("graphattributes");
		final List<String> result = new ArrayList<String>(def);
		result.add(attribute);
		return Collections.unmodifiableList(result);
	}

	final public DiagramDescription getDescription() {
		final StringBuilder result = new StringBuilder("(" + getLeafssize() + " entities");
		if (getSource() != null) {
			final String id = getSource().getId();
			if (id != null) {
				result.append(", ");
				result.append(id);
			}
		}
		result.append(")");
		return new DiagramDescription(result.toString());
	}

}
