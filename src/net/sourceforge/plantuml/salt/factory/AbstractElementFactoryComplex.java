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
package net.sourceforge.plantuml.salt.factory;

import java.util.ArrayList;
import java.util.Collection;

import net.sourceforge.plantuml.salt.DataSource;
import net.sourceforge.plantuml.salt.Dictionary;
import net.sourceforge.plantuml.salt.Terminated;
import net.sourceforge.plantuml.salt.element.Element;

public abstract class AbstractElementFactoryComplex implements ElementFactory {

	final private DataSource dataSource;
	final private Collection<ElementFactory> factories = new ArrayList<ElementFactory>();
	final private Dictionary dictionary;
	

	public AbstractElementFactoryComplex(DataSource dataSource, Dictionary dictionary) {
		this.dataSource = dataSource;
		this.dictionary = dictionary;
	}

	final public void addFactory(ElementFactory factory) {
		factories.add(factory);
	}

	protected Terminated<Element> getNextElement() {
		for (ElementFactory factory : factories) {
			if (factory.ready()) {
				return factory.create();
			}
		}
		throw new IllegalStateException(dataSource.peek(0).getElement());
	}

	protected final DataSource getDataSource() {
		return dataSource;
	}

	protected final Dictionary getDictionary() {
		return dictionary;
	}

}
