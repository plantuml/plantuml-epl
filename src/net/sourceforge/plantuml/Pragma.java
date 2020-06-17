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
package net.sourceforge.plantuml;

import java.util.LinkedHashMap;
import java.util.Map;

public class Pragma {

	private final Map<String, String> values = new LinkedHashMap<String, String>();

	public void define(String name, String value) {
		values.put(name, value);
	}

	public boolean isDefine(String name) {
		return values.containsKey(name);
	}

	public void undefine(String name) {
		values.remove(name);
	}

	public String getValue(String name) {
		return values.get(name);
	}

	public boolean horizontalLineBetweenDifferentPackageAllowed() {
		return isDefine("horizontallinebetweendifferentpackageallowed");
	}

	public boolean backToLegacyPackage() {
		return isDefine("backtolegacypackage");
	}

	public boolean useNewPackage() {
		return isDefine("usenewpackage");
	}

	public boolean useVerticalIf() {
		final String teoz = getValue("useverticalif");
		return "true".equalsIgnoreCase(teoz) || "on".equalsIgnoreCase(teoz);
	}

	public boolean useTeozLayout() {
		final String teoz = getValue("teoz");
		return "true".equalsIgnoreCase(teoz) || "on".equalsIgnoreCase(teoz);
	}

}
