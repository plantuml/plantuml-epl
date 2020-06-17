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
import java.io.IOException;
import java.io.InputStream;

public class Upf9Decoder {

	private Upf9Decoder() {
	}

	static int decodeChar(InputStream is) throws IOException {
		final int read0 = is.read();
		if (read0 == -1) {
			return -1;
		}
		if (read0 == 0x0B) {
			final int read1 = is.read();
			if (read1 >= 0x80)
				return (char) read1;
			return (char) ((0xE0 << 8) + read1);
		}
		if (read0 == 0x0C) {
			final int read1 = is.read();
			final int read2 = is.read();
			return (char) ((read1 << 8) + read2);
		}
		if (read0 >= 0x01 && read0 <= 0x08) {
			final int read1 = is.read();
			return (char) ((read0 << 8) + read1);
		}
		if (read0 >= 0x80 && read0 <= 0xFF) {
			final int read1 = is.read();
			return (char) (((read0 - 0x60) << 8) + read1);
		}
		return (char) read0;
	}

	public static String decodeString(byte[] data, int length) throws IOException {
		final ByteArrayInputStream bais = new ByteArrayInputStream(data, 0, length);
		final StringBuilder result = new StringBuilder();
		int read;
		while ((read = decodeChar(bais)) != -1) {
			result.append((char) read);
		}
		bais.close();
		return result.toString();
	}

}
