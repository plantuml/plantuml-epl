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
package net.sourceforge.plantuml.wbs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.sourceforge.plantuml.Direction;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.mindmap.IdeaShape;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleBuilder;
import net.sourceforge.plantuml.style.StyleSignature;

final class WElement {

	private final Display label;
	private final int level;
	private final String stereotype;
	private final WElement parent;
	private final StyleBuilder styleBuilder;
	private final List<WElement> childrenLeft = new ArrayList<WElement>();
	private final List<WElement> childrenRight = new ArrayList<WElement>();
	private final IdeaShape shape;

	private StyleSignature getDefaultStyleDefinitionNode(int level) {
		final String depth = SName.depth(level);
		if (level == 0) {
			return StyleSignature.of(SName.root, SName.element, SName.wbsDiagram, SName.node, SName.rootNode)
					.add(stereotype).add(depth);
		}
		if (isLeaf()) {
			return StyleSignature.of(SName.root, SName.element, SName.wbsDiagram, SName.node, SName.leafNode)
					.add(stereotype).add(depth);
		}
		return StyleSignature.of(SName.root, SName.element, SName.wbsDiagram, SName.node).add(stereotype).add(depth);
	}

	public Style getStyle() {
		Style result = getDefaultStyleDefinitionNode(level).getMergedStyle(styleBuilder);
		for (WElement up = parent; up != null; up = up.parent) {
			final StyleSignature ss = up.getDefaultStyleDefinitionNode(level).addStar();
			final Style p = ss.getMergedStyle(styleBuilder);
			result = result.mergeWith(p);
		}
		return result;
	}

	public WElement(Display label, String stereotype, StyleBuilder styleBuilder) {
		this(0, label, stereotype, null, IdeaShape.BOX, styleBuilder);
	}

	private WElement(int level, Display label, String stereotype, WElement parent, IdeaShape shape,
			StyleBuilder styleBuilder) {
		this.label = label;
		this.level = level;
		this.parent = parent;
		this.shape = shape;
		this.styleBuilder = styleBuilder;
		this.stereotype = stereotype;
	}

	public boolean isLeaf() {
		return childrenLeft.size() == 0 && childrenRight.size() == 0;
	}

	public WElement createElement(int newLevel, Display newLabel, String stereotype, Direction direction,
			IdeaShape shape, StyleBuilder styleBuilder) {
		final WElement result = new WElement(newLevel, newLabel, stereotype, this, shape, styleBuilder);
		if (direction == Direction.LEFT) {
			this.childrenLeft.add(result);
		} else {
			this.childrenRight.add(result);
		}
		return result;
	}

	@Override
	public String toString() {
		return label.toString();
	}

	public final int getLevel() {
		return level;
	}

	public final Display getLabel() {
		return label;
	}

	public Collection<WElement> getChildren(Direction direction) {
		if (direction == Direction.LEFT) {
			return Collections.unmodifiableList(childrenLeft);
		}
		return Collections.unmodifiableList(childrenRight);
	}

	public WElement getParent() {
		return parent;
	}

	public final IdeaShape getShape() {
		return shape;
	}

	public final StyleBuilder getStyleBuilder() {
		return styleBuilder;
	}

}
