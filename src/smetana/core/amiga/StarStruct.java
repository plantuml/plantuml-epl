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

package smetana.core.amiga;

import smetana.core.AllH;
import smetana.core.__ptr__;
import smetana.core.__struct__;

public interface StarStruct extends Area, AllH, InternalData {

	public boolean isSameThan(StarStruct other);

	public Class getRealClass();

	public __struct__ getStruct();

	public String getUID36();

	public String getDebug(String fieldName);

	public void setInt(String fieldName, int data);

	public void setDouble(String fieldName, double data);

	public __ptr__ plus(int pointerMove);

	public void setStruct(String fieldName, __struct__ newData);

	public __ptr__ setPtr(String fieldName, __ptr__ newData);

	public void memcopyFrom(Area source);

	public void copyDataFrom(__struct__ other);

	public void setStruct(__struct__ value);

	public void copyDataFrom(__ptr__ arg);

	public __ptr__ castTo(Class dest);

	public Object addVirtualBytes(int virtualBytes);

}
