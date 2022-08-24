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
package net.sourceforge.plantuml.swing;

import java.io.File;
import java.util.List;
import java.util.concurrent.Future;

import net.sourceforge.plantuml.GeneratedImage;

class SimpleLine implements Comparable<SimpleLine> {

	private final File file;
	private final GeneratedImage generatedImage;
	private final Future<List<GeneratedImage>> future;

	public static SimpleLine fromFuture(File file, Future<List<GeneratedImage>> future) {
		return new SimpleLine(file, null, future);
	}

	public static SimpleLine fromGeneratedImage(File file, GeneratedImage generatedImage) {
		return new SimpleLine(file, generatedImage, null);
	}

	private SimpleLine(File file, GeneratedImage generatedImage, Future<List<GeneratedImage>> future) {
		this.generatedImage = generatedImage;
		this.file = file;
		this.future = future;
	}

	public File getFile() {
		return file;
	}

	public boolean pendingAndFinished() {
		return generatedImage == null && future.isDone();
	}

	@Override
	public String toString() {
		if (generatedImage == null) {
			return file.getName() + " (...pending...)";
		}
		final StringBuilder sb = new StringBuilder(generatedImage.getPngFile().getName());
		sb.append(" ");
		sb.append(generatedImage.getDescription());
		return sb.toString();
	}

	public Future<List<GeneratedImage>> getFuture() {
		return future;
	}

	public int compareTo(SimpleLine other) {
		return toString().compareTo(other.toString());
	}

	public GeneratedImage getGeneratedImage() {
		return generatedImage;
	}

}
