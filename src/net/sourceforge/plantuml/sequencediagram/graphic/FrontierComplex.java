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
package net.sourceforge.plantuml.sequencediagram.graphic;

import java.util.Arrays;
import java.util.Objects;

class FrontierComplex implements Frontier {

	private final double freeY[];

	public static FrontierComplex create(double freeY, int rangeEnd) {
		final FrontierComplex result = new FrontierComplex(new double[rangeEnd + 1]);
		for (int i = 0; i <= rangeEnd; i++)
			result.freeY[i] = freeY;

		return result;
	}

	private FrontierComplex(double freeY[]) {
		this.freeY = freeY;
	}

	public double getFreeY(ParticipantRange range) {
		Objects.requireNonNull(range);
		double result = freeY[range.start()];
		for (int i = range.start(); i <= range.end(); i++)
			if (freeY[i] > result)
				result = freeY[i];

		return result;
	}

	@Override
	public String toString() {
		return Arrays.toString(freeY);
	}

	public FrontierComplex add(double delta, ParticipantRange range) {
		Objects.requireNonNull(range);
		final FrontierComplex result = new FrontierComplex(freeY.clone());
		final double newV = result.getFreeY(range) + delta;
		for (int i = range.start(); i <= range.end(); i++)
			result.freeY[i] = newV;
		return result;
	}

	FrontierComplex copy() {
		return new FrontierComplex(freeY.clone());
	}

	FrontierComplex mergeMax(FrontierComplex other) {
		if (this.freeY.length != other.freeY.length)
			throw new IllegalArgumentException();

		final FrontierComplex result = new FrontierComplex(new double[freeY.length]);
		for (int i = 0; i < freeY.length; i++)
			result.freeY[i] = Math.max(this.freeY[i], other.freeY[i]);

		return result;
	}

	// public double diff(Frontier other) {
	// return freeY - other.freeY;
	// }

}
