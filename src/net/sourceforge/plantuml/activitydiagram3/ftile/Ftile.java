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

import java.util.Collection;
import java.util.List;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.activitydiagram3.LinkRendering;
import net.sourceforge.plantuml.graphic.HorizontalAlignment;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.ugraphic.UStroke;
import net.sourceforge.plantuml.ugraphic.UTranslate;

public interface Ftile extends Swimable, TextBlock {

	public UStroke getThickness();

	public ISkinParam skinParam();

	public LinkRendering getInLinkRendering();

	public LinkRendering getOutLinkRendering();

	public FtileGeometry calculateDimension(StringBounder stringBounder);

	public UTranslate getTranslateFor(Ftile child, StringBounder stringBounder);

	public Collection<Ftile> getMyChildren();

	public Collection<Connection> getInnerConnections();

	public List<WeldingPoint> getWeldingPoints();
	
	public HorizontalAlignment arrowHorizontalAlignment();

}
