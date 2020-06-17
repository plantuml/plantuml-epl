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
package net.sourceforge.plantuml.cucadiagram;

import java.util.Collection;

import net.sourceforge.plantuml.cucadiagram.dot.Neighborhood;
import net.sourceforge.plantuml.graphic.USymbol;
import net.sourceforge.plantuml.skin.VisibilityModifier;
import net.sourceforge.plantuml.svek.IEntityImage;

public interface ILeaf extends IEntity {

	public EntityPosition getEntityPosition();

	public void setContainer(IGroup container);

	public boolean isTop();

	public void setTop(boolean top);

	public boolean hasNearDecoration();

	public void setNearDecoration(boolean nearDecoration);

	public int getXposition();

	public void setXposition(int pos);

	public IEntityImage getSvekImage();

	public String getGeneric();

	public boolean muteToType(LeafType newType, USymbol newSymbol);

	public void setGeneric(String generic);

	public void setSvekImage(IEntityImage svekImage);

	public void setNeighborhood(Neighborhood neighborhood);

	public Neighborhood getNeighborhood();

	public Collection<String> getPortShortNames();

	public void addPortShortName(String portShortName);

	public void setVisibilityModifier(VisibilityModifier visibility);

	public VisibilityModifier getVisibilityModifier();

}
