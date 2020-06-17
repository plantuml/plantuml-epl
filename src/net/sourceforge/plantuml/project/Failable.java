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
package net.sourceforge.plantuml.project;

public class Failable<O> {

	private final O data;
	private final String error;

	public static <O> Failable<O> ok(O data) {
		return new Failable<O>(data, null);
	}

	public static <O> Failable<O> error(String error) {
		return new Failable<O>(null, error);
	}

	private Failable(O data, String error) {
		if (data == null && error == null) {
			throw new IllegalArgumentException();
		}
		if (data != null && error != null) {
			throw new IllegalArgumentException();
		}
		this.data = data;
		this.error = error;
	}

	public O get() {
		if (data == null) {
			throw new IllegalStateException();
		}
		return data;
	}

	public boolean isFail() {
		return data == null;
	}

	public String getError() {
		if (error == null) {
			throw new IllegalStateException();
		}
		return error;
	}

}
