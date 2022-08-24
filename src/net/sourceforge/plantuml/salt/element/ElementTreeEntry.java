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
package net.sourceforge.plantuml.salt.element;

import net.sourceforge.plantuml.awt.geom.Dimension2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.plantuml.Dimension2DDouble;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UTranslate;

public class ElementTreeEntry {

	private final Element firstElement;
	private final int level;
	private final List<Element> otherElements = new ArrayList<>();

	public ElementTreeEntry(int level, Element elmt) {
		this.firstElement = elmt;
		this.level = level;
	}

	public void addCell(Element elmt) {
		this.otherElements.add(elmt);
	}

	public Dimension2D getPreferredDimensionFirstCell(StringBounder stringBounder) {
		return Dimension2DDouble.delta(firstElement.getPreferredDimension(stringBounder, 0, 0), getXDelta(), 0);
	}

	public ListWidth getPreferredDimensionOtherCell(StringBounder stringBounder) {
		final ListWidth result = new ListWidth();
		for (Element element : otherElements) {
			result.add(element.getPreferredDimension(stringBounder, 0, 0).getWidth());
		}
		return result;
	}

	public double getXDelta() {
		return level * 10;
	}

	public void drawFirstCell(UGraphic ug, double x, double y) {
		firstElement.drawU(ug.apply(new UTranslate(x + getXDelta(), y)), 0, null);
	}

	public void drawSecondCell(UGraphic ug, double x, double y, ListWidth otherWidth, double margin) {
		final Iterator<Double> it = otherWidth.iterator();
		for (Element element : otherElements) {
			element.drawU(ug.apply(new UTranslate(x, y)), 0, null);
			x += it.next() + margin;
		}
	}

}
