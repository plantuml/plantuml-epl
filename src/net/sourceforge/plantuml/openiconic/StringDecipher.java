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
package net.sourceforge.plantuml.openiconic;

public class StringDecipher {

	public static String decipher(String path) {
		path = path.trim();
		path = path.replace(',', ' ');
		path = path.replaceAll("\\s+", " ");
		path = path.replaceAll("([^e\\s])-", "$1 -");
		path = path.replaceAll("([a-df-zA-Z])(\\S)", "$1 $2");
		path = path.replaceAll("(\\S)([a-df-zA-Z])", "$1 $2");
		path = path.replaceAll("([a-df-zA-Z])(\\S)", "$1 $2");
		while (path.matches(".*\\.\\d+\\..*")) {
			path = path.replaceAll("(\\.\\d+)\\.", "$1 .");
		}
		return path;
	}

}
