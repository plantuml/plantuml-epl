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
package net.sourceforge.plantuml.activitydiagram;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.ISkinSimple;
import net.sourceforge.plantuml.activitydiagram.command.CommandElse;
import net.sourceforge.plantuml.activitydiagram.command.CommandEndPartition;
import net.sourceforge.plantuml.activitydiagram.command.CommandEndif;
import net.sourceforge.plantuml.activitydiagram.command.CommandIf;
import net.sourceforge.plantuml.activitydiagram.command.CommandLinkActivity;
import net.sourceforge.plantuml.activitydiagram.command.CommandLinkLongActivity;
import net.sourceforge.plantuml.activitydiagram.command.CommandPartition;
import net.sourceforge.plantuml.api.ThemeStyle;
import net.sourceforge.plantuml.classdiagram.command.CommandHideShow2;
import net.sourceforge.plantuml.command.Command;
import net.sourceforge.plantuml.command.CommandFootboxIgnored;
import net.sourceforge.plantuml.command.CommandRankDir;
import net.sourceforge.plantuml.command.PSystemCommandFactory;
import net.sourceforge.plantuml.command.CommonCommands;
import net.sourceforge.plantuml.command.note.CommandFactoryNoteActivity;
import net.sourceforge.plantuml.command.note.CommandFactoryNoteOnLink;
import net.sourceforge.plantuml.core.UmlSource;

public class ActivityDiagramFactory extends PSystemCommandFactory {

	@Override
	public ActivityDiagram createEmptyDiagram(ThemeStyle style, UmlSource source, ISkinSimple skinParam) {
		return new ActivityDiagram(style, source, skinParam);
	}

	@Override
	protected List<Command> createCommands() {
		final List<Command> cmds = new ArrayList<>();
		cmds.add(new CommandFootboxIgnored());
		CommonCommands.addCommonCommands1(cmds);
		cmds.add(new CommandRankDir());

		cmds.add(new CommandPartition());
		cmds.add(new CommandEndPartition());
		cmds.add(new CommandLinkLongActivity());

		final CommandFactoryNoteActivity factoryNoteActivityCommand = new CommandFactoryNoteActivity();
		cmds.add(factoryNoteActivityCommand.createSingleLine());
		cmds.add(factoryNoteActivityCommand.createMultiLine(false));

		final CommandFactoryNoteOnLink factoryNoteOnLinkCommand = new CommandFactoryNoteOnLink();
		cmds.add(factoryNoteOnLinkCommand.createSingleLine());
		cmds.add(factoryNoteOnLinkCommand.createMultiLine(false));

		cmds.add(new CommandIf());
		cmds.add(new CommandElse());
		cmds.add(new CommandEndif());

		cmds.add(new CommandLinkActivity());
		cmds.add(new CommandHideShow2());
		// addCommand(new CommandInnerConcurrent(system));

		return cmds;

	}

}
