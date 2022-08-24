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
package net.sourceforge.plantuml.svek;

import java.util.Objects;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.cucadiagram.IEntity;
import net.sourceforge.plantuml.cucadiagram.Stereotype;
import net.sourceforge.plantuml.graphic.AbstractTextBlock;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.ugraphic.color.HColor;

public abstract class AbstractEntityImage extends AbstractTextBlock implements IEntityImage {

	private final IEntity entity;
	private final ISkinParam skinParam;

	public AbstractEntityImage(IEntity entity, ISkinParam skinParam) {
		this.entity = Objects.requireNonNull(entity);
		this.skinParam = Objects.requireNonNull(skinParam);
	}

	@Override
	public boolean isHidden() {
		return entity.isHidden();
	}

	protected final IEntity getEntity() {
		return entity;
	}

	protected final ISkinParam getSkinParam() {
		return skinParam;
	}

	@Override
	public final HColor getBackcolor() {
		return skinParam.getBackgroundColor();
	}

	protected final Stereotype getStereo() {
		return entity.getStereotype();
	}

	@Override
	public Margins getShield(StringBounder stringBounder) {
		return Margins.NONE;
	}

	@Override
	public double getOverscanX(StringBounder stringBounder) {
		return 0;
	}

}
