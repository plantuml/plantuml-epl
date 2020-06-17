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
package net.sourceforge.plantuml.graph;

import java.util.Comparator;
import java.util.Map;

public class LenghtLinkComparator implements Comparator<ALink> {

	private final Map<ANode, Integer> cols;

	public LenghtLinkComparator(Map<ANode, Integer> cols) {
		this.cols = cols;
	}

	public int compare(ALink link1, ALink link2) {
		return (int) Math.signum(getLenght(link1) - getLenght(link2));
	}

	private double getLenght(ALink link) {
		final ANode n1 = link.getNode1();
		final ANode n2 = link.getNode2();
		final int deltaRow = n2.getRow() - n1.getRow();
		final int deltaCol = cols.get(n2) - cols.get(n1);
		return deltaRow * deltaRow + deltaCol * deltaCol;
	}

}
