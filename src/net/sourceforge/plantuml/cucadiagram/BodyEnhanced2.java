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

import java.awt.geom.Dimension2D;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.FontParam;
import net.sourceforge.plantuml.ISkinSimple;
import net.sourceforge.plantuml.LineBreakStrategy;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.graphic.AbstractTextBlock;
import net.sourceforge.plantuml.graphic.FontConfiguration;
import net.sourceforge.plantuml.graphic.HorizontalAlignment;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.graphic.TextBlockLineBefore;
import net.sourceforge.plantuml.graphic.TextBlockUtils;
import net.sourceforge.plantuml.graphic.TextBlockVertical2;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class BodyEnhanced2 extends AbstractTextBlock implements TextBlock {

	private TextBlock area2;
	private final FontConfiguration titleConfig;
	private final Display rawBody2;
	private final ISkinSimple spriteContainer;

	private final HorizontalAlignment align;
	private final LineBreakStrategy lineBreakStrategy;

	// private final List<Url> urls = new ArrayList<Url>();

	public BodyEnhanced2(Display rawBody, FontParam fontParam, ISkinSimple spriteContainer, HorizontalAlignment align,
			FontConfiguration titleConfig, LineBreakStrategy lineBreakStrategy) {
		this.rawBody2 = rawBody;
		this.lineBreakStrategy = lineBreakStrategy;
		this.spriteContainer = spriteContainer;

		this.titleConfig = titleConfig;
		this.align = align;
	}

	private TextBlock decorate(StringBounder stringBounder, TextBlock b, char separator, TextBlock title) {
		if (separator == 0) {
			return b;
		}
		if (title == null) {
			return new TextBlockLineBefore(TextBlockUtils.withMargin(b, 0, 4), separator);
		}
		final Dimension2D dimTitle = title.calculateDimension(stringBounder);
		final TextBlock raw = new TextBlockLineBefore(TextBlockUtils.withMargin(b, 0, 6, dimTitle.getHeight() / 2, 4),
				separator, title);
		return TextBlockUtils.withMargin(raw, 0, 0, dimTitle.getHeight() / 2, 0);
	}

	public Dimension2D calculateDimension(StringBounder stringBounder) {
		return getArea(stringBounder).calculateDimension(stringBounder);
	}

	private TextBlock getArea(StringBounder stringBounder) {
		if (area2 != null) {
			return area2;
		}
		// urls.clear();
		final List<TextBlock> blocks = new ArrayList<TextBlock>();

		char separator = 0;
		TextBlock title = null;
		Display members2 = Display.empty();
		for (CharSequence s : rawBody2) {
			if (isBlockSeparator(s.toString())) {
				blocks.add(decorate(stringBounder, getTextBlock(members2, stringBounder), separator, title));
				separator = s.charAt(0);
				title = getTitle(s.toString(), spriteContainer);
				members2 = Display.empty();
			} else {
				members2 = members2.add(s);
			}
		}
		blocks.add(decorate(stringBounder, getTextBlock(members2, stringBounder), separator, title));

		if (blocks.size() == 1) {
			this.area2 = blocks.get(0);
		} else {
			this.area2 = new TextBlockVertical2(blocks, align);
		}

		return area2;
	}

	private TextBlock getTextBlock(Display members2, StringBounder stringBounder) {
		final TextBlock result = members2.create9(titleConfig, align, spriteContainer, lineBreakStrategy);
		return result;
	}

	public static boolean isBlockSeparator(String s) {
		if (s.startsWith("--") && s.endsWith("--")) {
			return true;
		}
		if (s.startsWith("==") && s.endsWith("==")) {
			return true;
		}
		if (s.startsWith("..") && s.endsWith("..")) {
			return true;
		}
		if (s.startsWith("__") && s.endsWith("__")) {
			return true;
		}
		return false;
	}

	private TextBlock getTitle(String s, ISkinSimple spriteContainer) {
		if (s.length() <= 4) {
			return null;
		}
		s = StringUtils.trin(s.substring(2, s.length() - 2));
		return Display.getWithNewlines(s).create(titleConfig, HorizontalAlignment.LEFT, spriteContainer);
	}

	public void drawU(UGraphic ug) {
		getArea(ug.getStringBounder()).drawU(ug);
	}

}
