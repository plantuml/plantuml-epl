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

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.sequencediagram.InGroupable;
import net.sourceforge.plantuml.skin.Context2D;
import net.sourceforge.plantuml.ugraphic.UGraphic;

final class NotesBoxes extends GraphicalElement implements InGroupable {

	private final List<NoteBox> notes = new ArrayList<>();
	private final List<ParticipantBox> participants1 = new ArrayList<>();
	private final List<ParticipantBox> participants2 = new ArrayList<>();

	NotesBoxes(double startingY) {
		super(startingY);
	}

	public void add(NoteBox noteBox, ParticipantBox participantBox1, ParticipantBox participantBox2) {
		notes.add(noteBox);
		participants1.add(participantBox1);
		if (participantBox2 == null)
			participants2.add(participantBox1);
		else
			participants2.add(participantBox2);

	}

	public void ensureConstraints(StringBounder stringBounder, ConstraintSet constraintSet) {
		for (int i = 0; i < notes.size(); i++) {
			final NoteBox noteBox = notes.get(i);
			final ParticipantBox participantBox1 = participants1.get(i);
			final ParticipantBox participantBox2 = participants2.get(i);
			final double width = noteBox.getPreferredWidth(stringBounder);
			// System.err.println("i=" + i);
			// System.err.println("width=" + width);
			// System.err.println("participantBox1=" + participantBox1);
			// System.err.println("participantBox2=" + participantBox2);
			constraintSet.getConstraintBefore(participantBox1).ensureValue(width / 2);
			constraintSet.getConstraintAfter(participantBox2).ensureValue(width / 2);
			for (int j = i + 1; j < notes.size(); j++) {
				final NoteBox noteBox2 = notes.get(j);
				final ParticipantBox otherParticipantBox1 = participants1.get(j);
				final double width2 = noteBox2.getPreferredWidth(stringBounder);
				if (participantBox2 != otherParticipantBox1)
					constraintSet.getConstraint(participantBox2, otherParticipantBox1)
							.ensureValue((width + width2) / 2);
			}
		}
	}

	public double getMinX(StringBounder stringBounder) {
		double result = Double.MAX_VALUE;
		for (NoteBox n : notes) {
			final double m = n.getMinX(stringBounder);
			if (m < result)
				result = m;

		}
		return result;
	}

	public double getMaxX(StringBounder stringBounder) {
		double result = -Double.MAX_VALUE;
		for (NoteBox n : notes) {
			final double m = n.getMaxX(stringBounder);
			if (m > result)
				result = m;

		}
		return result;
	}

	public String toString(StringBounder stringBounder) {
		return toString();
	}

	@Override
	protected void drawInternalU(UGraphic ug, double maxX, Context2D context) {
		for (NoteBox n : notes)
			n.drawInternalU(ug, maxX, context);

	}

	@Override
	public double getStartingX(StringBounder stringBounder) {
		double result = Double.MAX_VALUE;
		for (NoteBox n : notes) {
			final double m = n.getStartingX(stringBounder);
			if (m < result)
				result = m;

		}
		return result;
	}

	@Override
	public double getPreferredWidth(StringBounder stringBounder) {
		final double result = getMaxX(stringBounder) - getMinX(stringBounder);
		return result;
	}

	@Override
	public double getPreferredHeight(StringBounder stringBounder) {
		double result = 0;
		for (NoteBox n : notes) {
			final double m = n.getPreferredHeight(stringBounder);
			if (m > result)
				result = m;

		}
		return result;
	}

}
