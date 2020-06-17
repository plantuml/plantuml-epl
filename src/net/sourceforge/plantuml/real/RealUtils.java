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
package net.sourceforge.plantuml.real;

import java.util.Arrays;
import java.util.Collection;

public class RealUtils {

	public static RealOrigin createOrigin() {
		final RealLine line = new RealLine();
		final RealImpl result = new RealImpl("O", line, 0);
		return result;
	}

	public static Real middle(Real r1, Real r2) {
		return new RealMiddle2((RealMoveable) r1, (RealMoveable) r2);
	}

	public static Real max(Real... reals) {
		return new RealMax(Arrays.asList(reals));
	}

	public static Real max(Collection<Real> reals) {
		return new RealMax(reals);
	}

	public static Real min(Real... reals) {
		return new RealMin(Arrays.asList(reals));
	}

	public static Real min(Collection<Real> reals) {
		return new RealMin(reals);
	}
}
