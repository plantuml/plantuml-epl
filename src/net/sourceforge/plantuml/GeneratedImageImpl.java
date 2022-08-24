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
package net.sourceforge.plantuml;

import java.io.File;
import java.io.IOException;

import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.error.PSystemError;
import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.security.SFile;

public class GeneratedImageImpl implements GeneratedImage {

	private final SFile pngFile;
	private final String description;
	private final BlockUml blockUml;
	private final int status;

	public final int getStatus() {
		return status;
	}

	public GeneratedImageImpl(SFile pngFile, String description, BlockUml blockUml, int status) {
		this.blockUml = blockUml;
		this.pngFile = pngFile;
		this.description = description;
		this.status = status;
	}

	public File getPngFile() {
		return pngFile.conv();
	}

	public String getDescription() {
		return description;
	}

	public int lineErrorRaw() {
		final Diagram system = blockUml.getDiagram();
		if (system instanceof PSystemError) {
			return ((PSystemError) system).getLineLocation().getPosition();
		}
		return -1;
	}

	@Override
	public String toString() {
		return pngFile.getPrintablePath() + " " + description;
	}

	public int compareTo(GeneratedImage this2) {
		try {
			final int cmp = this.getPngFile().getCanonicalPath().compareTo(this2.getPngFile().getCanonicalPath());
			if (cmp != 0) {
				return cmp;
			}
		} catch (IOException e) {
			Logme.error(e);
		}
		return this.description.compareTo(this2.getDescription());
	}

	@Override
	public int hashCode() {
		return pngFile.hashCode() + description.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		final GeneratedImageImpl this2 = (GeneratedImageImpl) obj;
		return this2.pngFile.equals(this.pngFile) && this2.description.equals(this.description);
	}

	public BlockUml getBlockUml() {
		return blockUml;
	}
}
