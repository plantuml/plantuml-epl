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
package net.sourceforge.plantuml.activitydiagram3;

import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.Rainbow;

public class LinkRendering {

	private final Rainbow rainbow;
	private final Display display;

	public LinkRendering(Rainbow rainbow) {
		this(rainbow, Display.NULL);
	}

	private LinkRendering(Rainbow rainbow, Display display) {
		if (rainbow == null) {
			throw new IllegalArgumentException();
		}
		this.rainbow = rainbow;
		this.display = display;
	}

	public static LinkRendering none() {
		return new LinkRendering(Rainbow.none());
	}

	public LinkRendering withDisplay(Display display) {
		return new LinkRendering(rainbow, display);
	}

	public Display getDisplay() {
		return display;
	}

	public Rainbow getRainbow() {
		return rainbow;
	}

	public Rainbow getRainbow(Rainbow defaultColor) {
		if (rainbow.size() == 0) {
			return defaultColor;
		}
		return rainbow;
	}

	public boolean isNone() {
		return Display.isNull(display) && rainbow.size() == 0;
	}

	@Override
	public String toString() {
		return super.toString() + " " + display + " " + rainbow;
	}
}
