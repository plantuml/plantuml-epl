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
 * Contribution :  Hisashi Miyashita
 */
package net.sourceforge.plantuml.descdiagram.command;

import net.sourceforge.plantuml.Direction;
import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.cucadiagram.LinkArrow;
import net.sourceforge.plantuml.graphic.FontConfiguration;
import net.sourceforge.plantuml.graphic.HorizontalAlignment;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.graphic.TextBlockArrow;
import net.sourceforge.plantuml.graphic.TextBlockUtils;
import net.sourceforge.plantuml.graphic.VerticalAlignment;
import net.sourceforge.plantuml.svek.DirectionalTextBlock;
import net.sourceforge.plantuml.svek.GuideLine;

public class StringWithArrow {

	private final String label;
	private final LinkArrow linkArrow;

	public StringWithArrow(String completeLabel) {
		if (completeLabel == null) {
			this.linkArrow = LinkArrow.NONE_OR_SEVERAL;
			this.label = null;
			return;
		}
		completeLabel = StringUtils.eventuallyRemoveStartingAndEndingDoubleQuote(completeLabel, "\"");
		if (Display.hasSeveralGuideLines(completeLabel)) {
			this.linkArrow = LinkArrow.NONE_OR_SEVERAL;
			this.label = completeLabel;
		} else {

			if ("<".equals(completeLabel)) {
				this.linkArrow = LinkArrow.BACKWARD;
				this.label = null;
			} else if (">".equals(completeLabel)) {
				this.linkArrow = LinkArrow.DIRECT_NORMAL;
				this.label = null;
			} else if (completeLabel.startsWith("< ")) {
				this.linkArrow = LinkArrow.BACKWARD;
				this.label = StringUtils.trin(completeLabel.substring(2));
			} else if (completeLabel.startsWith("> ")) {
				this.linkArrow = LinkArrow.DIRECT_NORMAL;
				this.label = StringUtils.trin(completeLabel.substring(2));
			} else if (completeLabel.endsWith(" >")) {
				this.linkArrow = LinkArrow.DIRECT_NORMAL;
				this.label = StringUtils.trin(completeLabel.substring(0, completeLabel.length() - 2));
			} else if (completeLabel.endsWith(" <")) {
				this.linkArrow = LinkArrow.BACKWARD;
				this.label = StringUtils.trin(completeLabel.substring(0, completeLabel.length() - 2));
			} else {
				this.linkArrow = LinkArrow.NONE_OR_SEVERAL;
				this.label = completeLabel;
			}
		}
	}

	public final String getLabel() {
		return label;
	}

	public final LinkArrow getLinkArrow() {
		return linkArrow;
	}

	public final Display getDisplay() {
		return Display.getWithNewlines(label);
	}

	static public TextBlock addMagicArrow(TextBlock label, GuideLine guide, FontConfiguration font) {
		final TextBlock arrowRight = new TextBlockArrow(Direction.RIGHT, font);
		final TextBlock arrowLeft = new TextBlockArrow(Direction.LEFT, font);
		final TextBlock arrowUp = new TextBlockArrow(Direction.UP, font);
		final TextBlock arrowDown = new TextBlockArrow(Direction.DOWN, font);
		final TextBlock right = TextBlockUtils.mergeLR(label, arrowRight, VerticalAlignment.CENTER);
		final TextBlock left = TextBlockUtils.mergeLR(arrowLeft, label, VerticalAlignment.CENTER);
		final TextBlock up = TextBlockUtils.mergeTB(arrowUp, label, HorizontalAlignment.CENTER);
		final TextBlock down = TextBlockUtils.mergeTB(label, arrowDown, HorizontalAlignment.CENTER);
		return new DirectionalTextBlock(guide, right, left, up, down);
	}

	static private TextBlock addMagicArrow2(TextBlock label, GuideLine guide, FontConfiguration font) {
		final TextBlock arrowRight = new TextBlockArrow(Direction.RIGHT, font);
		final TextBlock arrowLeft = new TextBlockArrow(Direction.LEFT, font);
		final TextBlock arrowUp = new TextBlockArrow(Direction.UP, font);
		final TextBlock arrowDown = new TextBlockArrow(Direction.DOWN, font);
		final TextBlock right = TextBlockUtils.mergeLR(label, arrowRight, VerticalAlignment.CENTER);
		final TextBlock left = TextBlockUtils.mergeLR(arrowLeft, label, VerticalAlignment.CENTER);
		final TextBlock up = TextBlockUtils.mergeLR(arrowUp, label, VerticalAlignment.CENTER);
		final TextBlock down = TextBlockUtils.mergeLR(label, arrowDown, VerticalAlignment.CENTER);
		return new DirectionalTextBlock(guide, right, left, up, down);
	}

	public static TextBlock addSeveralMagicArrows(Display label, GuideLine guide, FontConfiguration font,
			HorizontalAlignment alignment, ISkinParam skinParam) {
		TextBlock result = TextBlockUtils.EMPTY_TEXT_BLOCK;
		for (CharSequence cs : label) {
			StringWithArrow tmp = new StringWithArrow(cs.toString());
			TextBlock block = tmp.getDisplay().create9(font, alignment, skinParam, skinParam.maxMessageSize());
			if (tmp.getLinkArrow() != LinkArrow.NONE_OR_SEVERAL) {
				block = StringWithArrow.addMagicArrow2(block, tmp.getLinkArrow().mute(guide), font);
			}
			result = TextBlockUtils.mergeTB(result, block, alignment);
		}
		return result;
	}

}
