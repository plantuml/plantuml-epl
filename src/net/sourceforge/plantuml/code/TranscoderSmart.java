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

import java.io.IOException;

public class TranscoderSmart implements Transcoder {

	// Legacy encoder
	private final Transcoder oldOne = TranscoderImpl.utf8(new AsciiEncoder(), new ArobaseStringCompressor(),
			new CompressionHuffman());
	private final Transcoder zlib = TranscoderImpl.utf8(new AsciiEncoder(), new ArobaseStringCompressor(),
			new CompressionZlib());

	public String decode(String code) throws NoPlantumlCompressionException {
		// Work in progress
		// See https://github.com/plantuml/plantuml/issues/117

		if (code.startsWith("~0")) {
			return zlib.decode(code.substring(2));
		}
		if (code.startsWith("~1")) {
			return oldOne.decode(code.substring(2));
		}

		try {
			return zlib.decode(code);
		} catch (Exception ex) {
			return oldOne.decode(code);
		}
		// return zlib.decode(code);
	}

	public String encode(String text) throws IOException {
		// Right now, we still use the legacy encoding.
		// This will be changed in the incoming months
		return zlib.encode(text);
	}
}
