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
package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.sourceforge.plantuml.activitydiagram3.ftile.vertical.FtileDecorate;
import net.sourceforge.plantuml.ugraphic.UGraphic;

class FtileWithConnection extends FtileDecorate {

	private final List<Connection> connections = new ArrayList<Connection>();

	FtileWithConnection(Ftile ftile, Collection<Connection> connections) {
		super(ftile);
		if (connections == null || connections.size() == 0) {
			throw new IllegalArgumentException();
		}
		this.connections.addAll(connections);
	}

	@Override
	public String toString() {
		return super.toString() + " " + connections;
	}

	public FtileWithConnection(Ftile ftile, Connection connection) {
		this(ftile, Arrays.asList(connection));
		if (connection == null) {
			throw new IllegalArgumentException();
		}
	}

	public void drawU(UGraphic ug) {
		getFtileDelegated().drawU(ug);
		for (Connection c : connections) {
			ug.draw(c);
		}
	}

	public Collection<Connection> getInnerConnections() {
		final List<Connection> result = new ArrayList<Connection>(super.getInnerConnections());
		result.addAll(connections);
		return Collections.unmodifiableList(connections);
	}

}
