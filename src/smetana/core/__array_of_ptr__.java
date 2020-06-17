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

package smetana.core;

import smetana.core.amiga.Area;

public interface __array_of_ptr__ extends Area {

	public String getUID36();

	public void memcopyFrom(Area source);

	public void swap(int i, int j);

	public void realloc(int nb);

	public void realloc(size_t nb);

	public __ptr__ asPtr();

	public int comparePointerInternal(__array_of_ptr__ other);

	public __array_of_ptr__ move(int delta);

	public __array_of_ptr__ plus(int delta);

	public Area getInternal(int idx);

	public void setInternalByIndex(int idx, Area value);

	public int getInt();

	public __ptr__ getPtr();

	public __struct__ getStruct();

	public void setInt(int value);

	public void setPtr(__ptr__ value);

	public void setStruct(__struct__ value);

	public void setDouble(String fieldName, double value);

}
