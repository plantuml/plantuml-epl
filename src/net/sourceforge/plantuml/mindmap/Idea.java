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
package net.sourceforge.plantuml.mindmap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.style.MergeStrategy;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleBuilder;
import net.sourceforge.plantuml.style.StyleSignatureBasic;
import net.sourceforge.plantuml.ugraphic.color.HColor;
import net.sourceforge.plantuml.wbs.WElement;

class Idea {

	private final Display label;
	private final int level;
	private final Idea parent;
	private final List<Idea> children = new ArrayList<>();
	private final IdeaShape shape;
	private final HColor backColor;
	private final StyleBuilder styleBuilder;
	private final String stereotype;

	private StyleSignatureBasic getDefaultStyleDefinitionNode(int level) {
		final String depth = SName.depth(level);
		if (level == 0) {
			return StyleSignatureBasic.of(SName.root, SName.element, SName.mindmapDiagram, SName.node, SName.rootNode)
					.add(stereotype).add(depth);
		}
		if (shape == IdeaShape.NONE && children.size() == 0) {
			return StyleSignatureBasic
					.of(SName.root, SName.element, SName.mindmapDiagram, SName.node, SName.leafNode, SName.boxless)
					.add(stereotype).add(depth);
		}
		if (shape == IdeaShape.NONE) {
			return StyleSignatureBasic.of(SName.root, SName.element, SName.mindmapDiagram, SName.node, SName.boxless)
					.add(stereotype).add(depth);
		}
		if (children.size() == 0) {
			return StyleSignatureBasic.of(SName.root, SName.element, SName.mindmapDiagram, SName.node, SName.leafNode)
					.add(stereotype).add(depth);
		}
		return StyleSignatureBasic.of(SName.root, SName.element, SName.mindmapDiagram, SName.node).add(stereotype)
				.add(depth);
	}

	private static final int STEP_BY_PARENT = WElement.STEP_BY_PARENT;

	public Style getStyle() {
		int deltaPriority = STEP_BY_PARENT * 1000;
		Style result = styleBuilder.getMergedStyleSpecial(getDefaultStyleDefinitionNode(level), deltaPriority);
		for (Idea up = parent; up != null; up = up.parent) {
			final StyleSignatureBasic ss = up.getDefaultStyleDefinitionNode(level).addStar();
			deltaPriority -= STEP_BY_PARENT;
			final Style styleParent = styleBuilder.getMergedStyleSpecial(ss, deltaPriority);
			result = result.mergeWith(styleParent, MergeStrategy.OVERWRITE_EXISTING_VALUE);
		}
		return result;
	}

	public Style getStyleArrow() {
		final String depth = SName.depth(level);
		final StyleSignatureBasic defaultStyleDefinitionArrow = StyleSignatureBasic
				.of(SName.root, SName.element, SName.mindmapDiagram, SName.arrow).add(stereotype).add(depth);
		return defaultStyleDefinitionArrow.getMergedStyle(styleBuilder);
	}

	public static Idea createIdeaSimple(StyleBuilder styleBuilder, HColor backColor, Display label, IdeaShape shape,
			String stereotype) {
		return new Idea(styleBuilder, backColor, 0, null, label, shape, stereotype);
	}

	public Idea createIdea(StyleBuilder styleBuilder, HColor backColor, int newLevel, Display newDisplay,
			IdeaShape newShape, String stereotype) {
		final Idea result = new Idea(styleBuilder, backColor, newLevel, this, newDisplay, newShape, stereotype);
		this.children.add(result);
		return result;
	}

	private Idea(StyleBuilder styleBuilder, HColor backColor, int level, Idea parent, Display label, IdeaShape shape,
			String stereotype) {
		this.backColor = backColor;
		this.styleBuilder = styleBuilder;
		this.label = label;
		this.level = level;
		this.parent = parent;
		this.shape = shape;
		this.stereotype = stereotype;
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

	public Collection<Idea> getChildren() {
		return Collections.unmodifiableList(children);
	}

	public boolean hasChildren() {
		return children.size() > 0;
	}

	public Idea getParent() {
		return parent;
	}

	public final IdeaShape getShape() {
		return shape;
	}

	public final HColor getBackColor() {
		return backColor;
	}

	public final StyleBuilder getStyleBuilder() {
		return styleBuilder;
	}

	public final String getStereotype() {
		return stereotype;
	}

}
