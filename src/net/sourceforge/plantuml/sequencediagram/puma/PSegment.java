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
package net.sourceforge.plantuml.sequencediagram.puma;

public class PSegment {

	private final double minsize;
	private double startx;
	private double endx;

	public PSegment(double minsize) {
		this.minsize = minsize;
		this.startx = 0;
		this.endx = minsize;
	}

	public double getMinsize() {
		return minsize;
	}

	public void push(double delta) {
		this.startx += delta;
		this.endx += delta;
	}

	public String getDebugPosition() {
		return "" + ((int) startx) + "-" + ((int) endx);
	}

	public double getPosition(double position) {
		if (position == 0) {
			return startx;
		}
		if (position == 1) {
			return endx;
		}
		throw new UnsupportedOperationException();
	}

}
