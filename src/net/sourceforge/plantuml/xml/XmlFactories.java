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
package net.sourceforge.plantuml.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;

public class XmlFactories {

	private XmlFactories() {
	}

	// This class uses the "initialization-on-demand holder" idiom to provide thread-safe
	// lazy initialization of expensive factories.
	// (see https://stackoverflow.com/a/8297830/1848731)

	private static class DocumentBuilderFactoryHolder {
		static final DocumentBuilderFactory INSTANCE = DocumentBuilderFactory.newInstance();
	}

	private static class TransformerFactoryHolder {
		static final TransformerFactory INSTANCE = TransformerFactory.newInstance();
	}

	public static DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
		return DocumentBuilderFactoryHolder.INSTANCE.newDocumentBuilder();
	}

	public static Transformer newTransformer() throws TransformerConfigurationException {
		return TransformerFactoryHolder.INSTANCE.newTransformer();
	}
}
