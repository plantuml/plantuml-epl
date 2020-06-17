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
package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.StringLocated;
import net.sourceforge.plantuml.preproc.EvalBoolean;
import net.sourceforge.plantuml.preproc.Truth;
import net.sourceforge.plantuml.tim.expression.TValue;

public class EaterIfdef extends Eater {

	private String expression;

	public EaterIfdef(StringLocated s) {
		super(s);
	}

	@Override
	public void analyze(TContext context, TMemory memory) throws EaterException {
		skipSpaces();
		checkAndEatChar("!ifdef");
		skipSpaces();
		expression = eatAllToEnd();
	}

	public boolean isTrue(final TContext context, final TMemory memory) {
		final EvalBoolean eval = new EvalBoolean(expression, new Truth() {

			public boolean isTrue(String varname) {
				final TValue currentValue = memory.getVariable(varname);
				return currentValue != null || context.doesFunctionExist(varname);
			}
		});

		return eval.eval();
	}

}
