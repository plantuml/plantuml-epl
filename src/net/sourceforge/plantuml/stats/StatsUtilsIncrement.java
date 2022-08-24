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
package net.sourceforge.plantuml.stats;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.prefs.Preferences;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.activitydiagram3.ActivityDiagram3;
import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.directdot.PSystemDot;
import net.sourceforge.plantuml.eggs.PSystemWelcome;
import net.sourceforge.plantuml.error.PSystemErrorUtils;
import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.math.PSystemMath;
import net.sourceforge.plantuml.salt.PSystemSalt;
import net.sourceforge.plantuml.stats.api.Stats;

public class StatsUtilsIncrement {

	final private static Preferences prefs = StatsUtils.prefs;

	final private static ConcurrentMap<String, ParsedGenerated> byTypeEver = StatsUtils.byTypeEver;
	final private static ConcurrentMap<String, ParsedGenerated> byTypeCurrent = StatsUtils.byTypeCurrent;

	final private static FormatCounter formatCounterCurrent = StatsUtils.formatCounterCurrent;
	final private static FormatCounter formatCounterEver = StatsUtils.formatCounterEver;

	public static void onceMoreParse(long duration, Class<? extends Diagram> type) {
		if (StatsUtils.fullEver == null || StatsUtils.historicalData == null) {
			return;
		}
		getByTypeCurrent(type).parsed().addValue(duration);
		final ParsedGenerated byTypeEver = getByTypeEver(type);
		byTypeEver.parsed().addValue(duration);
		StatsUtils.fullEver.parsed().addValue(duration);
		StatsUtils.historicalData.current().parsed().addValue(duration);

		StatsUtils.historicalData.current().parsed().save(prefs);
		StatsUtils.fullEver.parsed().save(prefs);
		byTypeEver.parsed().save(prefs);
		realTimeExport();
	}

	public static void onceMoreGenerate(long duration, Class<? extends Diagram> type, FileFormat fileFormat) {
		if (StatsUtils.fullEver == null || StatsUtils.historicalData == null) {
			return;
		}
		if (formatCounterCurrent == null || formatCounterEver == null) {
			return;
		}
		getByTypeCurrent(type).generated().addValue(duration);
		final ParsedGenerated byTypeEver = getByTypeEver(type);
		byTypeEver.generated().addValue(duration);
		StatsUtils.fullEver.generated().addValue(duration);
		StatsUtils.historicalData.current().generated().addValue(duration);
		formatCounterCurrent.plusOne(fileFormat, duration);
		formatCounterEver.plusOne(fileFormat, duration);

		formatCounterEver.save(prefs, fileFormat);
		StatsUtils.historicalData.current().generated().save(prefs);
		StatsUtils.fullEver.generated().save(prefs);
		byTypeEver.generated().save(prefs);
		realTimeExport();
	}

	private static ParsedGenerated getByTypeCurrent(Class<? extends Diagram> type) {
		final String name = name(type);
		ParsedGenerated n = byTypeCurrent.get(name);
		if (n == null) {
			byTypeCurrent.putIfAbsent(name, ParsedGenerated.createVolatileDated());
			n = byTypeCurrent.get(name);
		}
		return n;
	}

	private static ParsedGenerated getByTypeEver(Class<? extends Diagram> type) {
		final String name = name(type);
		ParsedGenerated n = byTypeEver.get(name);
		if (n == null) {
			byTypeEver.putIfAbsent(name, ParsedGenerated.loadDated(prefs, "type." + name));
			n = byTypeEver.get(name);
		}
		return n;
	}

	private static String name(Class<? extends Diagram> type) {
		if (PSystemErrorUtils.isDiagramError(type)) {
			return "Error";
		}
		if (type == ActivityDiagram3.class) {
			return "ActivityDiagramBeta";
		}
		if (type == PSystemSalt.class) {
			return "Salt";
		}
		if (type.getSimpleName().equals("PSystemSudoku")) {
			return "Sudoku";
		}
		if (type == PSystemDot.class) {
			return "Dot";
		}
		if (type == PSystemWelcome.class) {
			return "Welcome";
		}
		if (type.getSimpleName().equals("PSystemDitaa")) {
			return "Ditaa";
		}
		if (type.getSimpleName().equals("PSystemJcckit")) {
			return "Jcckit";
		}
		if (type == PSystemMath.class) {
			return "Math";
		}
		final String name = type.getSimpleName();
		if (name.endsWith("Diagram")) {
			return name;
		}
		// return "Other " + name;
		return "Other";
	}

	private static final Lock lockXml = new ReentrantLock();
	private static final Lock lockHtml = new ReentrantLock();

	private static void realTimeExport() {
		if (StatsUtils.realTimeStats) {
			final Stats stats = StatsUtils.getStatsLazzy();
			try {
				if (StatsUtils.xmlStats && lockXml.tryLock())
					try {
						StatsUtils.xmlOutput(stats);
					} finally {
						lockXml.unlock();
					}
				if (StatsUtils.htmlStats && lockHtml.tryLock())
					try {
						StatsUtils.htmlOutput(stats);
					} finally {
						lockHtml.unlock();
					}
			} catch (Exception e) {
				Logme.error(e);
			}

		}
	}

}
