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
package net.sourceforge.plantuml.security.authentication.oauth;

import java.net.URLConnection;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.security.authentication.SecurityAccessInterceptor;
import net.sourceforge.plantuml.security.authentication.SecurityAuthentication;

/**
 * Applies from {@link SecurityAuthentication} data an OAuth2 Authorization access header.
 *
 * @author Aljoscha Rittner
 */
public class OAuth2AccessInterceptor implements SecurityAccessInterceptor {
	@Override
	public void apply(SecurityAuthentication authentication, URLConnection connection) {
		connection.setRequestProperty("Authorization", getAuth(authentication));
	}

	private String getAuth(SecurityAuthentication authentication) {
		String accessToken = (String) authentication.getTokens().get(OAuth2Tokens.ACCESS_TOKEN.key());
		String type = (String) authentication.getTokens().get(OAuth2Tokens.TOKEN_TYPE.key());
		return StringUtils.capitalize(type) + ' ' + accessToken;
	}

}
