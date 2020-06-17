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
package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.json.JsonArray;
import net.sourceforge.plantuml.tim.iterator.CodePosition;

public class ExecutionContextForeach {

	private final String varname;
	private final JsonArray jsonArray;
	private final CodePosition codePosition;
	private boolean skipMe;
	private int currentIndex;

	private ExecutionContextForeach(String varname, JsonArray jsonArray, CodePosition codePosition) {
		this.varname = varname;
		this.jsonArray = jsonArray;
		this.codePosition = codePosition;
	}

	public static ExecutionContextForeach fromValue(String varname, JsonArray jsonArray, CodePosition codePosition) {
		return new ExecutionContextForeach(varname, jsonArray, codePosition);
	}

	public void skipMeNow() {
		skipMe = true;
	}

	public final boolean isSkipMe() {
		return skipMe;
	}

	public CodePosition getStartForeach() {
		return codePosition;
	}

	public final int currentIndex() {
		return currentIndex;
	}

	public final void inc() {
		this.currentIndex++;
		if (currentIndex >= jsonArray.size()) {
			this.skipMe = true;
		}
	}

	public final String getVarname() {
		return varname;
	}

	public final JsonArray getJsonArray() {
		return jsonArray;
	}

}
