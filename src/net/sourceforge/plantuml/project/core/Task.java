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
package net.sourceforge.plantuml.project.core;

import net.sourceforge.plantuml.Direction;
import net.sourceforge.plantuml.Url;
import net.sourceforge.plantuml.project.Load;
import net.sourceforge.plantuml.project.lang.ComplementColors;
import net.sourceforge.plantuml.project.lang.Subject;
import net.sourceforge.plantuml.project.time.Wink;

public interface Task extends Subject, Moment {

	public TaskCode getCode();

	public Wink getStart();

	public Wink getEnd();

	public Load getLoad();

	public void setLoad(Load load);

	public void setStart(Wink start);

	public void setEnd(Wink end);

	public void setColors(ComplementColors colors);

	public void addResource(Resource resource, int percentage);

	public void setDiamond(boolean diamond);

	public boolean isDiamond();

	public void setCompletion(int completion);

	public void setUrl(Url url);

	public double getHeight();

	public double getY();

	public void setY(double y);

	public double getY(Direction direction);

}
