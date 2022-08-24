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
package net.sourceforge.plantuml.security.authentication.basicauth;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.code.Base64Coder;
import net.sourceforge.plantuml.security.authentication.SecurityAccessInterceptor;
import net.sourceforge.plantuml.security.authentication.SecurityAuthentication;

import java.net.URLConnection;

/**
 * Applies from {@link SecurityAuthentication} data a BasicAuth authentication access header.
 *
 * @author Aljoscha Rittner
 */
public class BasicAuthAccessInterceptor implements SecurityAccessInterceptor {

	/**
	 * Applies from {@link SecurityAuthentication} data a BasicAuth authentication access header.
	 * <p>
	 * Expects "identifier" and "secret" to build a Authorization header.
	 *
	 * @param authentication the determined authentication data to authorize for the endpoint access
	 * @param connection     the connection to the endpoint
	 */
	@Override
	public void apply(SecurityAuthentication authentication, URLConnection connection) {
		String auth = getAuth(authentication);
		String authorization = Base64Coder.encodeString(auth);
		String authHeaderValue = "Basic " + authorization;
		connection.setRequestProperty("Authorization", authHeaderValue);
	}

	private String getAuth(SecurityAuthentication authentication) {
		String id = (String) authentication.getTokens().get("identifier");
		char[] secret = (char[]) authentication.getTokens().get("secret");
		StringBuilder auth = new StringBuilder();
		if (StringUtils.isNotEmpty(id)) {
			auth.append(id);
			if (secret != null && secret.length > 0) {
				auth.append(':').append(secret);
			}
		}
		return auth.toString();
	}
}
