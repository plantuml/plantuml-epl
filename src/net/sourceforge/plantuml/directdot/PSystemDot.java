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
package net.sourceforge.plantuml.directdot;

import static net.sourceforge.plantuml.ugraphic.ImageBuilder.plainImageBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import net.sourceforge.plantuml.AbstractPSystem;
import net.sourceforge.plantuml.CounterOutputStream;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.FileImageData;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.api.ImageDataSimple;
import net.sourceforge.plantuml.core.DiagramDescription;
import net.sourceforge.plantuml.core.ImageData;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.cucadiagram.dot.ExeState;
import net.sourceforge.plantuml.cucadiagram.dot.Graphviz;
import net.sourceforge.plantuml.cucadiagram.dot.GraphvizUtils;
import net.sourceforge.plantuml.cucadiagram.dot.ProcessState;
import net.sourceforge.plantuml.graphic.GraphicStrings;
import net.sourceforge.plantuml.graphic.TextBlock;

public class PSystemDot extends AbstractPSystem {

	private final String data;

	public PSystemDot(UmlSource source, String data) {
		super(source);
		this.data = data;
	}

	public DiagramDescription getDescription() {
		return new DiagramDescription("(Dot)");
	}

	@Override
	final protected ImageData exportDiagramNow(OutputStream os, int num, FileFormatOption fileFormat)
			throws IOException {
		final Graphviz graphviz = GraphvizUtils.createForSystemDot(null, data,
				StringUtils.goLowerCase(fileFormat.getFileFormat().name()));
		if (graphviz.getExeState() != ExeState.OK) {
			final TextBlock result = GraphicStrings
					.createForError(Arrays.asList("There is an issue with your Dot/Graphviz installation"), false);
			return plainImageBuilder(result, fileFormat)
					.seed(seed())
					.status(FileImageData.CRASH)
					.write(os);
		}
		final CounterOutputStream counter = new CounterOutputStream(os);
		final ProcessState state = graphviz.createFile3(counter);
		// if (state.differs(ProcessState.TERMINATED_OK())) {
		// throw new IllegalStateException("Timeout1 " + state);
		// }
		if (counter.getLength() == 0 || state.differs(ProcessState.TERMINATED_OK())) {
			final TextBlock result = GraphicStrings.createForError(Arrays.asList("GraphViz has crashed"), false);
			return plainImageBuilder(result, fileFormat)
					.seed(seed())
					.status(FileImageData.CRASH)
					.write(os);
		}

		return ImageDataSimple.ok();
	}
}
