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
package net.sourceforge.plantuml.compositediagram;

import net.sourceforge.plantuml.ISkinSimple;
import net.sourceforge.plantuml.UmlDiagramType;
import net.sourceforge.plantuml.classdiagram.AbstractEntityDiagram;
import net.sourceforge.plantuml.cucadiagram.Code;
import net.sourceforge.plantuml.cucadiagram.IEntity;
import net.sourceforge.plantuml.cucadiagram.Ident;
import net.sourceforge.plantuml.cucadiagram.LeafType;
import net.sourceforge.plantuml.graphic.USymbol;

public class CompositeDiagram extends AbstractEntityDiagram {

	public CompositeDiagram(ISkinSimple skinParam) {
		super(skinParam);
	}

	@Override
	public IEntity getOrCreateLeaf(Ident ident, Code code, LeafType type, USymbol symbol) {
		checkNotNull(ident);
		// final Ident idNewLong = buildLeafIdent(id);
		if (type == null) {
			if (isGroup(code)) {
				return getGroup(code);
			}
			return getOrCreateLeafDefault(ident, code, LeafType.BLOCK, symbol);
		}
		return getOrCreateLeafDefault(ident, code, type, symbol);
	}

	@Override
	public UmlDiagramType getUmlDiagramType() {
		return UmlDiagramType.COMPOSITE;
	}

}
