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
package net.sourceforge.plantuml.api;

import net.sourceforge.plantuml.CMapData;
import net.sourceforge.plantuml.annotation.HaxeIgnored;
import net.sourceforge.plantuml.awt.geom.Dimension2D;

public class ImageDataComplex extends ImageDataAbstract {

	private final CMapData cmap;
	private final String warningOrError;

	@SuppressWarnings("unused")  // available publicly so retained for backwards compatibility
	@HaxeIgnored
	public ImageDataComplex(Dimension2D info, CMapData cmap, String warningOrError) {
		super(info);
		this.cmap = cmap;
		this.warningOrError = warningOrError;
	}

	public ImageDataComplex(Dimension2D info, CMapData cmap, String warningOrError, int status) {
		super(info);
		this.cmap = cmap;
		this.warningOrError = warningOrError;
		setStatus(status);
	}

	public boolean containsCMapData() {
		return cmap != null && cmap.containsData();
	}

	public String getCMapData(String nameId) {
		return cmap.asString(nameId);
	}

	public String getWarningOrError() {
		return warningOrError;
	}

}
