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
package net.sourceforge.plantuml.hcl;

import java.util.Objects;

public class HclTerm {

	private final SymbolType type;
	private final String data;

	public HclTerm(SymbolType type) {
		this.type = type;
		this.data = null;
	}

	public HclTerm(SymbolType type, String data) {
		this.type = type;
		this.data = Objects.requireNonNull(data);
	}

	@Override
	public String toString() {
		if (data == null)
			return type.toString();

		return type + "(" + data + ")";
	}

	public SymbolType getType() {
		return type;
	}

	public String getData() {
		return data;
	}

	public boolean is(SymbolType type) {
		return this.type == type;
	}

	public boolean is(SymbolType type1, SymbolType type2) {
		return this.type == type1 || this.type == type2;
	}

}
