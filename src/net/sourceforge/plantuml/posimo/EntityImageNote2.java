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
package net.sourceforge.plantuml.posimo;

import java.awt.geom.Dimension2D;
import java.util.Collection;

import net.sourceforge.plantuml.Dimension2DDouble;
import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.cucadiagram.IEntity;
import net.sourceforge.plantuml.cucadiagram.Link;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.skin.Component;
import net.sourceforge.plantuml.skin.ComponentType;
import net.sourceforge.plantuml.skin.SimpleContext2D;
import net.sourceforge.plantuml.skin.rose.Rose;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UTranslate;

public class EntityImageNote2 extends AbstractEntityImage2 {

	private final Component comp;

	public EntityImageNote2(IEntity entity, ISkinParam skinParam, Collection<Link> links) {
		super(entity, skinParam);

		final Rose skin = new Rose();

		comp = skin.createComponent(null, ComponentType.NOTE, null, skinParam, entity.getDisplay());

	}

	@Override
	public Dimension2D getDimension(StringBounder stringBounder) {
		final double height = comp.getPreferredHeight(stringBounder);
		final double width = comp.getPreferredWidth(stringBounder);
		return new Dimension2DDouble(width, height);
	}

	public void drawU(UGraphic ug, double xTheoricalPosition, double yTheoricalPosition, double marginWidth,
			double marginHeight) {
		ug = ug.apply(new UTranslate(xTheoricalPosition, yTheoricalPosition));
		comp.drawU(ug, new Area(getDimension(ug.getStringBounder())), new SimpleContext2D(false));

	}

}
