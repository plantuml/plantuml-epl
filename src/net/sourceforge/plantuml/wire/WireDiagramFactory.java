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
package net.sourceforge.plantuml.wire;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.command.Command;
import net.sourceforge.plantuml.command.UmlDiagramFactory;
import net.sourceforge.plantuml.core.DiagramType;

public class WireDiagramFactory extends UmlDiagramFactory {

	public WireDiagramFactory() {
		super(DiagramType.WIRE);
	}

	@Override
	protected List<Command> createCommands() {

		final List<Command> cmds = new ArrayList<Command>();
		addCommonCommands1(cmds);
		cmds.add(new CommandComponent());
		cmds.add(new CommandContainer());
		cmds.add(new CommandContainerEnd());
		cmds.add(new CommandPin());
		cmds.add(new CommandPinSpace());
		cmds.add(new CommandVspace());
		cmds.add(new CommandNewColumn());

		return cmds;
	}

	@Override
	public WireDiagram createEmptyDiagram() {
		return new WireDiagram();
	}

}
