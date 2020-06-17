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
package net.sourceforge.plantuml.activitydiagram3;

import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.color.Colors;
import net.sourceforge.plantuml.sequencediagram.NotePosition;
import net.sourceforge.plantuml.sequencediagram.NoteType;

public class PositionedNote {

	private final Display display;
	private final NotePosition notePosition;
	private final NoteType type;
	private final Colors colors;
	private final Swimlane swimlaneNote;

	public PositionedNote(Display display, NotePosition position, NoteType type, Colors colors, Swimlane swimlaneNote) {
		this.display = display;
		this.notePosition = position;
		this.type = type;
		this.colors = colors;
		this.swimlaneNote = swimlaneNote;
	}

	@Override
	public String toString() {
		return "type=" + type + " notePosition=" + notePosition + " " + display;
	}

	public PositionedNote(Display note, NotePosition position, NoteType type, Swimlane swimlaneNote) {
		this(note, position, type, null, swimlaneNote);
	}

	public Display getDisplay() {
		return display;
	}

	public NotePosition getNotePosition() {
		return notePosition;
	}

	public NoteType getType() {
		return type;
	}

	public Colors getColors() {
		return colors;
	}

	public final Swimlane getSwimlaneNote() {
		return swimlaneNote;
	}

}
