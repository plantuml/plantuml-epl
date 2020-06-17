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
package net.sourceforge.plantuml.skin;

public class ArrowDressing {

	private final ArrowHead head;
	private final ArrowPart part;
	// private final ArrowDecoration decoration;

	public String name() {
		return toString();
	}

	@Override
	public String toString() {
		return head.name();
	}

	private ArrowDressing(ArrowHead head, ArrowPart part) {
		if (head == null || part == null) {
			throw new IllegalArgumentException();
		}
		this.head = head;
		this.part = part;
	}

	public static ArrowDressing create() {
		return new ArrowDressing(ArrowHead.NONE, ArrowPart.FULL);
	}

	public ArrowDressing withHead(ArrowHead head) {
		return new ArrowDressing(head, part);
	}

	public ArrowDressing withPart(ArrowPart part) {
		return new ArrowDressing(head, part);
	}

	public ArrowHead getHead() {
		return head;
	}

	public ArrowPart getPart() {
		return part;
	}


}
