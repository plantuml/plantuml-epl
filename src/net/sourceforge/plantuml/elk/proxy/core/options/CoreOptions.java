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
package net.sourceforge.plantuml.elk.proxy.core.options;

import net.sourceforge.plantuml.elk.proxy.Reflect;

public class CoreOptions {
	
	public static final Object DIRECTION = Reflect.field("org.eclipse.elk.core.options.CoreOptions", "DIRECTION");
	public static final Object EDGE_LABELS_INLINE = Reflect.field("org.eclipse.elk.core.options.CoreOptions", "EDGE_LABELS_INLINE");
	public static final Object NODE_SIZE_CONSTRAINTS = Reflect.field("org.eclipse.elk.core.options.CoreOptions", "NODE_SIZE_CONSTRAINTS");
	public static final Object HIERARCHY_HANDLING = Reflect.field("org.eclipse.elk.core.options.CoreOptions", "HIERARCHY_HANDLING");
	public static final Object EDGE_LABELS_PLACEMENT = Reflect.field("org.eclipse.elk.core.options.CoreOptions", "EDGE_LABELS_PLACEMENT");
	public static final Object EDGE_TYPE = Reflect.field("org.eclipse.elk.core.options.CoreOptions", "EDGE_TYPE");
	public static final Object NODE_LABELS_PLACEMENT = Reflect.field("org.eclipse.elk.core.options.CoreOptions", "NODE_LABELS_PLACEMENT");
	public static final Object NODE_SIZE_OPTIONS = Reflect.field("org.eclipse.elk.core.options.CoreOptions", "NODE_SIZE_OPTIONS");
	public static final Object PADDING = Reflect.field("org.eclipse.elk.core.options.CoreOptions", "PADDING");

}
