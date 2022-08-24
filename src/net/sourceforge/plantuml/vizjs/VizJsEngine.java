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
package net.sourceforge.plantuml.vizjs;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class VizJsEngine {

	public static boolean isOk() {
		try {
			final Class classVizJS = Class.forName("ch.braincell.viz.VizJS");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private final Object viz;
	private final Method mExecute;

	public VizJsEngine() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		final Class classVizJS = Class.forName("ch.braincell.viz.VizJS");
		final Method mCreate = classVizJS.getMethod("create");
		mExecute = classVizJS.getMethod("execute", String.class);
		this.viz = mCreate.invoke(null);
	}

	public String execute(String dot) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		return (String) mExecute.invoke(viz, dot);
	}

}
