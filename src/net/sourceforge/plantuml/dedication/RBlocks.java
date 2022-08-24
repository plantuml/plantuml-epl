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
package net.sourceforge.plantuml.dedication;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RBlocks {

	private final List<RBlock> all = new ArrayList<>();

	private RBlocks() {

	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(all.size());
		for (RBlock block : all) {
			sb.append(" - ");
			sb.append(block.toString());
		}
		return sb.toString();
	}

	public static RBlocks readFrom(byte[] fileContent, int size) {
		final RBlocks result = new RBlocks();
		int start = 0;
		while (start < fileContent.length) {
			final RBlock block = new RBlock(fileContent, start, size);
			start += size;
			result.all.add(block);
		}
		return result;
	}

	public RBlocks change(BigInteger E, BigInteger N) {
		final RBlocks result = new RBlocks();
		for (RBlock rsa : all) {
			result.all.add(rsa.change(E, N));
		}
		return result;
	}

	public void writeTo(Path out, int size) throws IOException {
		writeTo(new FileOutputStream(out.toFile()), size);
	}

	public byte[] toByteArray(int size) throws IOException {
		final byte[] result = new byte[size * all.size()];
		for (int i = 0; i < all.size(); i++) {
			final byte[] tmp = all.get(i).getData(size);
			System.arraycopy(tmp, 0, result, i * size, tmp.length);
		}
		return result;
	}

	public void writeTo(OutputStream os, int size) throws IOException {
		for (RBlock rsa : all) {
			final byte[] tmp = rsa.getData(size);
			os.write(tmp);
		}
		os.close();
	}

}
