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
package net.sourceforge.plantuml.cucadiagram.dot;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GraphvizVersions {

	private final static GraphvizVersions singleton = new GraphvizVersions();

	private final Map<File, GraphvizVersion> map = new ConcurrentHashMap<File, GraphvizVersion>();

	private GraphvizVersions() {
	}

	public static GraphvizVersions getInstance() {
		return singleton;
	}

	public GraphvizVersion getVersion(File f) {
		if (f == null) {
			return null;
		}
		GraphvizVersion result = map.get(f);
		if (result != null) {
			return result;
		}
		result = checkVersionSlow(f.getAbsolutePath());
		map.put(f, result);
		return result;
	}

	static GraphvizVersion checkVersionSlow(String pathExecutable) {
		final GraphvizVersionFinder finder = new GraphvizVersionFinder(new File(pathExecutable));
		return finder.getVersion();
	}

}
