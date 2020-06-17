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
package net.sourceforge.plantuml.preproc;

public class DefineVariable {

	private final String name;
	private final String defaultValue;

	public DefineVariable(String name) {
		name = name.trim();
		final int idx = name.indexOf('=');
		if (idx == -1) {
			this.name = name;
			this.defaultValue = null;
		} else {
			this.name = name.substring(0, idx).trim();
			final String right = name.substring(idx + 1).trim();
			this.defaultValue = right.substring(1, right.length() - 1);
		}
	}

	public String getName() {
		return name;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public DefineVariable removeDefault() {
		if (defaultValue == null) {
			throw new IllegalStateException();
		}
		return new DefineVariable(name);
	}

}
