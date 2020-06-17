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
package net.sourceforge.plantuml.preproc2;

import java.io.IOException;
import java.util.List;

import net.sourceforge.plantuml.StringLocated;
import net.sourceforge.plantuml.preproc.ReadLine;
import net.sourceforge.plantuml.preproc.ReadLineList;
import net.sourceforge.plantuml.utils.StartUtils;

public class ReadFilterAddConfig implements ReadFilter {

	private final List<String> config;

	public ReadFilterAddConfig(List<String> config) {
		this.config = config;
	}

	public ReadLine applyFilter(final ReadLine raw) {

		return new ReadLine() {

			private ReadLine inserted;

			public void close() throws IOException {
				raw.close();
			}

			public StringLocated readLine() throws IOException {
				StringLocated result = null;
				if (inserted != null) {
					result = inserted.readLine();
					if (result == null) {
						inserted.close();
						inserted = null;
					} else {
						return result;
					}
				}
				result = raw.readLine();
				if (result != null && StartUtils.isArobaseStartDiagram(result.getString()) && config.size() > 0) {
					inserted = new ReadFilterQuoteComment().applyFilter(new ReadLineList(config, result.getLocation()));
				}
				return result;
			}
		};
	}

}
