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
package net.sourceforge.plantuml;

import java.io.IOException;
import java.io.InputStream;

import net.sourceforge.plantuml.security.SFile;

public class AFileRegular implements AFile {

	private final SFile file;

	@Override
	public String toString() {
		return "AFileRegular::" + file.getAbsolutePath();
	}

	public AFileRegular(SFile file) {
		this.file = file;
	}

	public InputStream openFile() {
		return file.openFile();
	}

	public boolean isOk() {
		return file.exists() && file.isDirectory() == false;
	}

	@Override
	public int hashCode() {
		return file.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AFileRegular == false) {
			return false;
		}
		return this.file.equals(((AFileRegular) obj).file);
	}

	public AParentFolder getParentFile() {
		return new AParentFolderRegular(file.getParentFile());
	}

	public SFile getUnderlyingFile() {
		return file;
	}

	public SFile getSystemFolder() throws IOException {
		return file.getParentFile().getCanonicalFile();
	}

}
