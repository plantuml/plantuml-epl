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
package net.sourceforge.plantuml.code;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class CompressionHuffman implements Compression {

	public byte[] compress(byte[] in) {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final Deflater deflater = new Deflater(Deflater.HUFFMAN_ONLY);
		deflater.setLevel(9);
		final DeflaterOutputStream gz = new DeflaterOutputStream(baos, deflater);
		try {
			gz.write(in);
			gz.close();
			baos.close();
			return baos.toByteArray();
		} catch (IOException e) {
			throw new IllegalStateException(e.toString());
		}
	}

	public ByteArray decompress(byte[] in) throws NoPlantumlCompressionException {
		try {
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();

			final ByteArrayInputStream bais = new ByteArrayInputStream(in);
			final InflaterInputStream gz = new InflaterInputStream(bais);
			int read;
			while ((read = gz.read()) != -1) {
				baos.write(read);
			}
			gz.close();
			bais.close();
			baos.close();
			return ByteArray.from(baos.toByteArray());
		} catch (IOException e) {
			System.err.println("Not Huffman");
			throw new NoPlantumlCompressionException(e);
		}
	}

}
