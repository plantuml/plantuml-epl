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
package net.sourceforge.plantuml.code;

import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Deprecated
public class CompressionZlibAttic implements Compression {

	private static boolean USE_ZOPFLI = false;
	private static final int COMPRESSION_LEVEL = 9;

	public byte[] compress(byte[] in) {
		if (USE_ZOPFLI) {
			return new CompressionZopfliZlib().compress(in);
		}
		if (in.length == 0) {
			return null;
		}
		int len = in.length * 2;
		if (len < 1000) {
			len = 1000;
		}
		byte[] result = null;
		result = tryCompress(in, len);
		return result;
	}

	private byte[] tryCompress(byte[] in, final int len) {
		// Compress the bytes
		final Deflater compresser = new Deflater(COMPRESSION_LEVEL, true);
		compresser.setInput(in);
		compresser.finish();

		final byte[] output = new byte[len];
		final int compressedDataLength = compresser.deflate(output);
		if (compresser.finished() == false) {
			return null;
		}
		return copyArray(output, compressedDataLength);
	}

	public ByteArray decompress(byte[] in) throws NoPlantumlCompressionException {
		try {
			final byte in2[] = new byte[in.length + 256];
			System.arraycopy(in, 0, in2, 0, in.length);

			int len = 100_000;
			byte[] result = null;
			result = tryDecompress(in2, len);
			if (result == null) {
				throw new NoPlantumlCompressionException("Too big?");

			}

			return ByteArray.from(result);
		} catch (IOException e) {
			// Logme.error(e);
			throw new NoPlantumlCompressionException(e);
		}

	}

	private byte[] tryDecompress(byte[] in, final int len) throws IOException {
		if (len > 200_000) {
			throw new IOException("OutOfMemory");
		}
		// Decompress the bytes
		final byte[] tmp = new byte[len];
		final Inflater decompresser = new Inflater(true);
		decompresser.setInput(in);
		try {
			final int resultLength = decompresser.inflate(tmp);
			if (decompresser.finished() == false) {
				return null;
			}
			decompresser.end();

			final byte[] result = copyArray(tmp, resultLength);
			return result;
		} catch (DataFormatException e) {
			// Logme.error(e);
			throw new IOException(e.toString());
		}
	}

	private byte[] copyArray(final byte[] data, final int len) {
		final byte[] result = new byte[len];
		System.arraycopy(data, 0, result, 0, len);
		return result;
	}

}
