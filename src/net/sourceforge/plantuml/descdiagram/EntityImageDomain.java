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
package net.sourceforge.plantuml.descdiagram;

import net.sourceforge.plantuml.ColorParam;
import net.sourceforge.plantuml.Dimension2DDouble;
import net.sourceforge.plantuml.FontParam;
import net.sourceforge.plantuml.Guillemet;
import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.LineParam;
import net.sourceforge.plantuml.SkinParamUtils;
import net.sourceforge.plantuml.Url;
import net.sourceforge.plantuml.awt.geom.Dimension2D;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.cucadiagram.ILeaf;
import net.sourceforge.plantuml.cucadiagram.Stereotype;
import net.sourceforge.plantuml.graphic.FontConfiguration;
import net.sourceforge.plantuml.graphic.HorizontalAlignment;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.graphic.TextBlockUtils;
import net.sourceforge.plantuml.graphic.color.ColorType;
import net.sourceforge.plantuml.svek.AbstractEntityImage;
import net.sourceforge.plantuml.svek.ShapeType;
import net.sourceforge.plantuml.ugraphic.PlacementStrategyY1Y2;
import net.sourceforge.plantuml.ugraphic.UFont;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.ULayoutGroup;
import net.sourceforge.plantuml.ugraphic.URectangle;
import net.sourceforge.plantuml.ugraphic.UStroke;
import net.sourceforge.plantuml.ugraphic.UTranslate;
import net.sourceforge.plantuml.ugraphic.color.HColor;

public class EntityImageDomain extends AbstractEntityImage {
	final private TextBlock name;
	final private TextBlock tag;
	final private TextBlock stereo;
	final private Url url;

	public EntityImageDomain(ILeaf entity, ISkinParam skinParam, char typeLetter) {
		super(entity, skinParam);
		final Stereotype stereotype = entity.getStereotype();
		FontConfiguration fc = FontConfiguration.create(getSkinParam(), FontParam.DESIGNED_DOMAIN, stereotype);
		this.name = TextBlockUtils.withMargin(entity.getDisplay().create(fc, HorizontalAlignment.CENTER, skinParam), 2,
				2);
		if (stereotype == null || stereotype.getLabel(Guillemet.DOUBLE_COMPARATOR) == null) {
			this.stereo = null;
		} else {
			this.stereo = Display.create(stereotype.getLabels(skinParam.guillemet())).create(
					FontConfiguration.create(getSkinParam(), FontParam.DESIGNED_DOMAIN_STEREOTYPE, stereotype),
					HorizontalAlignment.CENTER, skinParam);
		}
		this.tag = new BoxedCharacter(typeLetter, 8, UFont.byDefault(8), stereotype.getHtmlColor(), null, fc.getColor());

		this.url = entity.getUrl99();
	}

	private UStroke getStroke() {
		UStroke stroke = getSkinParam().getThickness(LineParam.domainBorder, getStereo());

		if (stroke == null) {
			stroke = new UStroke(1.5);
		}
		return stroke;
	}

	private Dimension2D getTitleDimension(StringBounder stringBounder) {
		return getNameAndSteretypeDimension(stringBounder);
	}

	private Dimension2D getNameAndSteretypeDimension(StringBounder stringBounder) {
		final Dimension2D nameDim = name.calculateDimension(stringBounder);
		final Dimension2D stereoDim = stereo == null ? new Dimension2DDouble(0, 0) : stereo
				.calculateDimension(stringBounder);
		final Dimension2D nameAndStereo = new Dimension2DDouble(Math.max(nameDim.getWidth(), stereoDim.getWidth()),
				nameDim.getHeight() + stereoDim.getHeight());
		return nameAndStereo;
	}

	public double getStartingX(StringBounder stringBounder, double y) {
		return 0;
	}

	public double getEndingX(StringBounder stringBounder, double y) {
		return calculateDimension(stringBounder).getWidth();
	}

	final public void drawU(UGraphic ug) {
		final StringBounder stringBounder = ug.getStringBounder();
		final Dimension2D dimTotal = calculateDimension(stringBounder);
		final Dimension2D dimTitle = getTitleDimension(stringBounder);
		final Dimension2D dimTag = getTagDimension(stringBounder);
		final double widthTotal = dimTotal.getWidth();
		final double heightTotal = dimTotal.getHeight();
		final URectangle rect = new URectangle(widthTotal, heightTotal);

		ug = ug.apply(SkinParamUtils.getColor(getSkinParam(), getStereo(), ColorParam.domainBorder));
		HColor backcolor = getEntity().getColors().getColor(ColorType.BACK);
		if (backcolor == null) {
			backcolor = SkinParamUtils.getColor(getSkinParam(), getStereo(), ColorParam.domainBackground);
		}
		ug = ug.apply(backcolor.bg());
		if (url != null) {
			ug.startUrl(url);
		}

		final UStroke stroke = getStroke();
		ug.apply(stroke).draw(rect);

		final ULayoutGroup header = new ULayoutGroup(new PlacementStrategyY1Y2(ug.getStringBounder()));
		header.add(name);
		header.drawU(ug, dimTotal.getWidth(), dimTitle.getHeight());
		final ULayoutGroup footer = new ULayoutGroup(new PlacementStrategyY1Y2(ug.getStringBounder()));
		footer.add(tag);
		footer.drawU(ug.apply(new UTranslate(dimTotal.getWidth() - dimTag.getWidth(), dimTitle.getHeight())),
				dimTag.getWidth(), dimTag.getHeight());
		if (url != null) {
			ug.closeUrl();
		}
	}

	private Dimension2D getTagDimension(StringBounder stringBounder) {
		final Dimension2D tagDim = tag == null ? new Dimension2DDouble(0, 0) : tag.calculateDimension(stringBounder);
		return tagDim;
	}

	public ShapeType getShapeType() {
		return ShapeType.RECTANGLE;
	}

	public Dimension2D calculateDimension(StringBounder stringBounder) {
		final Dimension2D dimTitle = getTitleDimension(stringBounder);
		final double width = dimTitle.getWidth();
		final double height = dimTitle.getHeight();
		final Dimension2D dimTag = getTagDimension(stringBounder);
		return new Dimension2DDouble(width, height + dimTag.getHeight());
	}

}
