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
package net.sourceforge.plantuml.preproc2;

import java.io.IOException;

import net.sourceforge.plantuml.StringLocated;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.core.DiagramType;
import net.sourceforge.plantuml.preproc.ReadLine;
import net.sourceforge.plantuml.utils.StartUtils;

public class ReadFilterMergeLines implements ReadFilter {

	public ReadLine applyFilter(final ReadLine source) {
		return new ReadLine() {

			private boolean manageEndingBackslash = true;

			public void close() throws IOException {
				source.close();
			}

			public StringLocated readLine() throws IOException {
				StringLocated result = source.readLine();
				if (result != null && StartUtils.isArobaseStartDiagram(result.getString())
						&& isDitaa(result.getString())) {
					this.manageEndingBackslash = false;
				}
				if (result != null && StartUtils.isArobaseEndDiagram(result.getString())) {
					this.manageEndingBackslash = true;
				}

				ReadLine sourceWithoutComment = null;

				while (result != null && manageEndingBackslash && StringUtils.endsWithBackslash(result.getString())) {
					if (sourceWithoutComment == null) {
						sourceWithoutComment = new ReadFilterQuoteComment().applyFilter(source);
					}
					final StringLocated next = sourceWithoutComment.readLine();
					if (next == null) {
						break;
					} else {
						result = result.mergeEndBackslash(next);
					}
				}
				return result;
			}

			private boolean isDitaa(String string) {
				return DiagramType.getTypeFromArobaseStart(StringUtils.trinNoTrace((string))) == DiagramType.DITAA;
			}
		};
	}

}
