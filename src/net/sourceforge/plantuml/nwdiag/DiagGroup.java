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
package net.sourceforge.plantuml.nwdiag;

import java.util.HashSet;
import java.util.Set;

import net.sourceforge.plantuml.ugraphic.color.HColor;

public class DiagGroup {

	private final String name;
	private final Network network;
	private final Set<String> elements = new HashSet<String>();
	private HColor color;

	@Override
	public String toString() {
		return name + " " + network + " " + elements;
	}

	public DiagGroup(String name, Network network) {
		this.name = name;
		this.network = network;
	}

	public final String getName() {
		return name;
	}

	public void addElement(String name) {
		this.elements.add(name);
	}

	public boolean matches(LinkedElement tested) {
		if (network != null && network != tested.getNetwork()) {
			return false;
		}
		return elements.contains(tested.getElement().getName());
	}

	public final HColor getColor() {
		return color;
	}

	public final void setColor(HColor color) {
		this.color = color;
	}

}
