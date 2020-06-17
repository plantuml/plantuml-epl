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
package net.sourceforge.plantuml.sequencediagram.puma;

public class FixedLink {

	final private SegmentPosition segmentPosition1;
	final private SegmentPosition segmentPosition2;

	public FixedLink(SegmentPosition segmentPosition1, SegmentPosition segmentPosition2) {
		this.segmentPosition1 = segmentPosition1;
		this.segmentPosition2 = segmentPosition2;
	}

	public boolean pushIfNeed() {
		final double p1 = segmentPosition1.getPosition();
		final double p2 = segmentPosition2.getPosition();
		if (p1 == p2) {
			return false;
		}
		final double diff = p1 - p2;
		segmentPosition2.getSegment().push(diff);
		assert segmentPosition1.getPosition() == segmentPosition2.getPosition();
		return true;
	}

}
