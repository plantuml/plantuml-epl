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
package net.sourceforge.plantuml.cucadiagram.dot;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.plantuml.StringUtils;

public class GraphvizVersionFinder {

	final private File dotExe;
	final public static GraphvizVersion DEFAULT = new GraphvizVersion() {
		public boolean useShield() {
			return true;
		}

		public boolean useProtectionWhenThereALinkFromOrToGroup() {
			return true;
		}

		public boolean useXLabelInsteadOfLabel() {
			return false;
		}

		public boolean isVizjs() {
			return false;
		}

		public boolean ignoreHorizontalLinks() {
			return false;
		}
	};

	public GraphvizVersionFinder(File dotExe) {
		this.dotExe = dotExe;
	}

	public GraphvizVersion getVersion() {
		final String dotVersion = dotVersion();
		final Pattern p = Pattern.compile("([23456])\\.(\\d\\d?)");
		final Matcher m = p.matcher(dotVersion);
		final boolean find = m.find();
		if (find == false) {
			return DEFAULT;
		}
		final int major = Integer.parseInt(m.group(1));
		final int minor = Integer.parseInt(m.group(2));
		final int v = 100 * major + minor;
		return new GraphvizVersion() {
			public boolean useShield() {
				return v <= 228;
			}

			public boolean useProtectionWhenThereALinkFromOrToGroup() {
				if (v == 239 || v == 240) {
					return false;
				}
				// return v < 238;
				return true;
			}

			public boolean useXLabelInsteadOfLabel() {
				return false;
			}

			public boolean isVizjs() {
				return false;
			}

			public boolean ignoreHorizontalLinks() {
				if (v == 230) {
					return true;
				}
				return false;
			}

		};
	}

	public String dotVersion() {
		final String cmd[] = getCommandLine();

		final ProcessRunner p = new ProcessRunner(cmd);
		final ProcessState state = p.run(null, null);
		if (state.differs(ProcessState.TERMINATED_OK())) {
			return "?";
		}
		final StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotEmpty(p.getOut())) {
			sb.append(p.getOut());
		}
		if (StringUtils.isNotEmpty(p.getError())) {
			if (sb.length() > 0) {
				sb.append(' ');
			}
			sb.append(p.getError());
		}
		return StringUtils.trin(sb.toString().replace('\n', ' '));
	}

	private String[] getCommandLine() {
		return new String[] { dotExe.getAbsolutePath(), "-V" };
	}

}
