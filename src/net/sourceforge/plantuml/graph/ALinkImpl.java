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

public class ALinkImpl implements ALink {

	private final ANode node1;
	private final ANode node2;
	private final Object userData;
	private final int diffHeight;

	@Override
	public String toString() {
		return "" + node1 + " -> " + node2;
	}

	public ALinkImpl(ANode n1, ANode n2, int diffHeight, Object userData) {
		this.node1 = n1;
		this.node2 = n2;
		this.userData = userData;
		this.diffHeight = diffHeight;
	}

	public int getDiffHeight() {
		return diffHeight;
	}

	public ANode getNode1() {
		return node1;
	}

	public ANode getNode2() {
		return node2;
	}

	public final Object getUserData() {
		return userData;
	}

}
