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
package net.sourceforge.plantuml.xmlsc;

import java.io.IOException;
import java.io.OutputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import net.sourceforge.plantuml.Log;
import net.sourceforge.plantuml.statediagram.StateDiagram;

public final class StateDiagramScxmlMaker {

	private final StateDiagram diagram;

	public StateDiagramScxmlMaker(StateDiagram diagram) throws IOException {
		this.diagram = diagram;
	}

	public void createFiles(OutputStream fos) throws IOException {
		try {
			final ScxmlStateDiagramStandard xmi;
			xmi = new ScxmlStateDiagramStandard(diagram);
			xmi.transformerXml(fos);
		} catch (ParserConfigurationException e) {
			Log.error(e.toString());
			e.printStackTrace();
			throw new IOException(e.toString());
		} catch (TransformerException e) {
			Log.error(e.toString());
			e.printStackTrace();
			throw new IOException(e.toString());
		}
	}

}
