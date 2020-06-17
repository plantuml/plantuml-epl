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
package net.sourceforge.plantuml.creole;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.plantuml.ISkinSimple;
import net.sourceforge.plantuml.creole.legacy.CreoleParser;
import net.sourceforge.plantuml.graphic.FontConfiguration;
import net.sourceforge.plantuml.graphic.HorizontalAlignment;

public class Parser {

	public static final String MONOSPACED = "monospaced";

	public static SheetBuilder build(FontConfiguration fontConfiguration, HorizontalAlignment horizontalAlignment,
			ISkinSimple skinParam, CreoleMode creoleMode) {
		final FontConfiguration stereotype = fontConfiguration.forceFont(null, null);
		return new CreoleParser(fontConfiguration, horizontalAlignment, skinParam, creoleMode, stereotype);
	}

	public static SheetBuilder build(FontConfiguration fontConfiguration, HorizontalAlignment horizontalAlignment,
			ISkinSimple skinParam, CreoleMode creoleMode, FontConfiguration stereotype) {
		return new CreoleParser(fontConfiguration, horizontalAlignment, skinParam, creoleMode, stereotype);
	}

	public static boolean isTreeStart(String line) {
		// return false;
		return line.startsWith("|_");
	}

	public static double getScale(String s, double def) {
		if (s == null) {
			return def;
		}
		final Pattern p = Pattern.compile("(?:scale=|\\*)([0-9.]+)");
		final Matcher m = p.matcher(s);
		if (m.find()) {
			return Double.parseDouble(m.group(1));
		}
		return def;
	}

	public static String getColor(String s) {
		if (s == null) {
			return null;
		}
		final Pattern p = Pattern.compile("color[= :](#[0-9a-fA-F]{6}|\\w+)");
		final Matcher m = p.matcher(s);
		if (m.find()) {
			return m.group(1);
		}
		return null;
	}

}
