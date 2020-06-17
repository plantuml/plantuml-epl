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
package net.sourceforge.plantuml;

import java.util.Map;

import net.sourceforge.plantuml.creole.Parser;
import net.sourceforge.plantuml.sprite.Sprite;
import net.sourceforge.plantuml.sprite.SpriteImage;
import net.sourceforge.plantuml.ugraphic.color.ColorMapper;
import net.sourceforge.plantuml.ugraphic.color.ColorMapperIdentity;
import net.sourceforge.plantuml.ugraphic.color.HColorSet;

public class SpriteContainerEmpty implements SpriteContainer, ISkinSimple {

	public Sprite getSprite(String name) {
		return SpriteImage.fromInternal(name);
	}

	public String getValue(String key) {
		return null;
	}

	public double getPadding() {
		return 0;
	}

	public Guillemet guillemet() {
		return Guillemet.DOUBLE_COMPARATOR;
	}

	public String getMonospacedFamily() {
		return Parser.MONOSPACED;
	}

	public int getTabSize() {
		return 8;
	}

	public HColorSet getIHtmlColorSet() {
		return HColorSet.instance();
	}

	public int getDpi() {
		return 96;
	}

	public LineBreakStrategy wrapWidth() {
		return LineBreakStrategy.NONE;
	}

	public ColorMapper getColorMapper() {
		return new ColorMapperIdentity();
	}

	public void copyAllFrom(ISkinSimple other) {
		throw new UnsupportedOperationException();
	}

	public Map<String, String> values() {
		throw new UnsupportedOperationException();
	}

}
