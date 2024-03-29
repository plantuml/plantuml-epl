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
package net.sourceforge.plantuml.vizjs;

import java.io.File;
import java.io.OutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

import net.sourceforge.plantuml.cucadiagram.dot.ExeState;
import net.sourceforge.plantuml.cucadiagram.dot.Graphviz;
import net.sourceforge.plantuml.cucadiagram.dot.GraphvizVersion;
import net.sourceforge.plantuml.cucadiagram.dot.ProcessState;
import net.sourceforge.plantuml.log.Logme;

public class GraphvizJs implements Graphviz {

	private final static ExecutorService executorService = Executors
			.newSingleThreadScheduledExecutor(new ThreadFactory() {
				public Thread newThread(Runnable runnable) {
					return new JsThread(runnable);
				}
			});

	static class JsThread extends Thread {

		private final Runnable runnable;
		private VizJsEngine engine;

		public JsThread(Runnable runnable) {
			this.runnable = runnable;
		}

		@Override
		public void run() {
			if (engine == null) {
				try {
					this.engine = new VizJsEngine();
				} catch (Exception e) {
					Logme.error(e);
				}
			}
			runnable.run();
		}

	}

	private final String dotString;

	public GraphvizJs(String dotString) {
		this.dotString = dotString;
	}

	public ProcessState createFile3(OutputStream os) {
		try {
			final String svg = submitJob().get();
			os.write(svg.getBytes());
			return ProcessState.TERMINATED_OK();
		} catch (Exception e) {
			Logme.error(e);
			throw new GraphvizJsRuntimeException(e);
		}
	}

	private Future<String> submitJob() {
		return executorService.submit(new Callable<String>() {
			public String call() throws Exception {
				final JsThread th = (JsThread) Thread.currentThread();
				final VizJsEngine engine = th.engine;
				return engine.execute(dotString);
			}
		});
	}

	public File getDotExe() {
		return null;
	}

	public String dotVersion() {
		return "VizJs";
	}

	public ExeState getExeState() {
		return ExeState.OK;
	}

	public static GraphvizVersion getGraphvizVersion(final boolean modeSafe) {
		return new GraphvizVersion() {
			public boolean useShield() {
				return true;
			}

			public boolean useProtectionWhenThereALinkFromOrToGroup() {
				return true;
			}

			public boolean useXLabelInsteadOfLabel() {
				return modeSafe;
			}

			public boolean isVizjs() {
				return true;
			}

			public boolean ignoreHorizontalLinks() {
				return false;
			}
		};
	}

	public boolean graphviz244onWindows() {
		return false;
	}

}
