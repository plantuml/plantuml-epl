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
package net.sourceforge.plantuml.project.lang;

import java.util.Arrays;
import java.util.Collection;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.regex.IRegex;
import net.sourceforge.plantuml.command.regex.RegexLeaf;
import net.sourceforge.plantuml.command.regex.RegexResult;
import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.core.TaskInstant;
import net.sourceforge.plantuml.project.time.Day;

public class SubjectSeparator implements Subject {

	public IRegex toRegex() {
		return new RegexLeaf("SUBJECT", "separator");
	}

	public Failable<GanttDiagram> getMe(GanttDiagram project, RegexResult arg) {
		return Failable.ok(project);
	}

	public Collection<? extends SentenceSimple> getSentences() {
		return Arrays.asList(new JustBefore(), new JustAfter(), new Just());
	}

	class JustBefore extends SentenceSimple {

		public JustBefore() {
			super(SubjectSeparator.this, Verbs.justBefore(), new ComplementDate());
		}

		@Override
		public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
			final Day day = (Day) complement;
			assert project == subject;
			project.addVerticalSeparatorBefore(day);
			return CommandExecutionResult.ok();
		}

	}

	class JustAfter extends SentenceSimple {

		public JustAfter() {
			super(SubjectSeparator.this, Verbs.justAfter(), new ComplementDate());
		}

		@Override
		public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
			final Day day = (Day) complement;
			assert project == subject;
			project.addVerticalSeparatorBefore(day.increment());
			return CommandExecutionResult.ok();
		}

	}

	class Just extends SentenceSimple {

		public Just() {
			super(SubjectSeparator.this, Verbs.just(), new ComplementBeforeOrAfterOrAtTaskStartOrEnd());
		}

		@Override
		public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
			final TaskInstant when = (TaskInstant) complement;

			assert project == subject;
			project.addVerticalSeparatorBefore(when.getInstantPrecise());
			return CommandExecutionResult.ok();
		}

	}

}
