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
package net.sourceforge.plantuml.hector2.graphic;

import java.awt.geom.Dimension2D;

import net.sourceforge.plantuml.Dimension2DDouble;
import net.sourceforge.plantuml.cucadiagram.CucaDiagram;
import net.sourceforge.plantuml.cucadiagram.IEntity;
import net.sourceforge.plantuml.cucadiagram.ILeaf;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.hector2.layering.Layer;
import net.sourceforge.plantuml.svek.GeneralImageBuilder;
import net.sourceforge.plantuml.svek.IEntityImage;

public class Foo1 {

	public static Dimension2D getMaxCellDimension(StringBounder stringBounder, Layer layer, CucaDiagram diagram) {
		Dimension2D result = new Dimension2DDouble(0, 0);
		for (IEntity ent : layer.entities()) {
			final IEntityImage image = computeImage((ILeaf) ent, diagram);
			final Dimension2D dim = image.calculateDimension(stringBounder);
			result = Dimension2DDouble.max(result, dim);
		}
		return result;
	}

	private static IEntityImage computeImage(final ILeaf leaf, CucaDiagram diagram) {
		final IEntityImage image = GeneralImageBuilder.createEntityImageBlock(leaf, diagram.getSkinParam(),
				false, diagram, null, null, null, diagram.getLinks());
		return image;
	}

}
