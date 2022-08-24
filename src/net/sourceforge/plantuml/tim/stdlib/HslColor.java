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
package net.sourceforge.plantuml.tim.stdlib;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sourceforge.plantuml.LineLocation;
import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;
import net.sourceforge.plantuml.tim.TContext;
import net.sourceforge.plantuml.tim.TFunctionSignature;
import net.sourceforge.plantuml.tim.TMemory;
import net.sourceforge.plantuml.tim.expression.TValue;
import net.sourceforge.plantuml.ugraphic.color.HColors;
import net.sourceforge.plantuml.ugraphic.color.HSLColor;

public class HslColor extends SimpleReturnFunction {

	public TFunctionSignature getSignature() {
		return new TFunctionSignature("%hsl_color", 3);
	}

	public boolean canCover(int nbArg, Set<String> namedArgument) {
		return nbArg == 3 || nbArg == 4;
	}

	public TValue executeReturnFunction(TContext context, TMemory memory, LineLocation location, List<TValue> values,
			Map<String, TValue> named) throws EaterException, EaterExceptionLocated {
		final int h = values.get(0).toInt();
		final int s = values.get(1).toInt();
		final int l = values.get(2).toInt();
		if (values.size() == 3) {
			final HSLColor color = new HSLColor(h, s, l);
			final Color rgb = color.getRGB();
			return TValue.fromString(HColors.simple(rgb).asString());
		}
		final int a = values.get(3).toInt();
		final HSLColor color = new HSLColor(h, s, l, (float) (a / 100.0));
		final Color rgb = color.getRGB();
		return TValue.fromString(HColors.simple(rgb).asString());

	}
}
