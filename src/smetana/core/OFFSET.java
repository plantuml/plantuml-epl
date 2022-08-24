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

package smetana.core;

public class OFFSET {

	private final String field;
	private final int sign;

	public int getSign() {
		return sign;
	}

	public OFFSET(String field) {
		this.field = field;
		this.sign = 1;
	}

	private OFFSET(int sign) {
		this.field = null;
		this.sign = sign;
	}

	@Override
	public String toString() {
		if (field == null)
			return "[" + sign + "]";
		return "[" + field + "]";
	}

	public static OFFSET externalHolder() {
		return new OFFSET(-1);
	}

	public static OFFSET zero() {
		return new OFFSET(0);
	}

	public OFFSET negative() {
		// Just to know when we go negative
		return this;
	}

	public String getField() {
		return field;
	}

}
