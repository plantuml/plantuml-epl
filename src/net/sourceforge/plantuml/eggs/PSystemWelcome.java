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
package net.sourceforge.plantuml.eggs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.PlainDiagram;
import net.sourceforge.plantuml.core.DiagramDescription;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.graphic.GraphicPosition;
import net.sourceforge.plantuml.graphic.GraphicStrings;
import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.svek.TextBlockBackcolored;
import net.sourceforge.plantuml.version.PSystemVersion;

public class PSystemWelcome extends PlainDiagram {

	private final List<String> strings = new ArrayList<>();
	private final GraphicPosition position;

	public PSystemWelcome(UmlSource source, GraphicPosition position) {
		super(source);
		this.position = position;
		strings.add("<b>Welcome to PlantUML!");
		strings.add(" ");
		strings.add("You can start with a simple UML Diagram like:");
		strings.add(" ");
		strings.add("\"\"Bob->Alice: Hello\"\"");
		strings.add(" ");
		strings.add("Or");
		strings.add(" ");
		strings.add("\"\"class Example\"\"");
		strings.add(" ");
		strings.add("You will find more information about PlantUML syntax on <u>https://plantuml.com</u>");
		strings.add(" ");
		strings.add("(If you use this software, you accept its license)");
		strings.add("(Details by typing \"\"license\"\" keyword)");
		strings.add(" ");
		if (position == GraphicPosition.BACKGROUND_CORNER_BOTTOM_RIGHT) {
			strings.add(" ");
			strings.add(" ");
			strings.add(" ");
			strings.add(" ");
		}
	}

	@Override
	protected UDrawable getRootDrawable(FileFormatOption fileFormatOption) throws IOException {
		return getGraphicStrings();
	}

	public TextBlockBackcolored getGraphicStrings() {
		if (position != null) {
			return GraphicStrings.createBlackOnWhite(strings, PSystemVersion.getPlantumlImage(), position);
		}
		return GraphicStrings.createBlackOnWhite(strings);
	}

	public DiagramDescription getDescription() {
		return new DiagramDescription("(Empty)");
	}

}
