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
package net.sourceforge.plantuml.core;

import net.sourceforge.plantuml.utils.StartUtils;

public enum DiagramType {
	UML, BPM, DITAA, DOT, PROJECT, JCCKIT, SALT, FLOW, CREOLE, JUNGLE, CUTE, MATH, LATEX, DEFINITION, GANTT, NW, MINDMAP, WBS, WIRE, UNKNOWN;

	static public DiagramType getTypeFromArobaseStart(String s) {
		s = s.toLowerCase();
		// if (s.startsWith("@startuml2")) {
		// return UML2;
		// }
		if (StartUtils.startsWithSymbolAnd("startwire", s)) {
			return WIRE;
		}
		if (StartUtils.startsWithSymbolAnd("startbpm", s)) {
			return BPM;
		}
		if (StartUtils.startsWithSymbolAnd("startuml", s)) {
			return UML;
		}
		if (StartUtils.startsWithSymbolAnd("startdot", s)) {
			return DOT;
		}
		if (StartUtils.startsWithSymbolAnd("startjcckit", s)) {
			return JCCKIT;
		}
		if (StartUtils.startsWithSymbolAnd("startditaa", s)) {
			return DITAA;
		}
		if (StartUtils.startsWithSymbolAnd("startproject", s)) {
			return PROJECT;
		}
		if (StartUtils.startsWithSymbolAnd("startsalt", s)) {
			return SALT;
		}
		if (StartUtils.startsWithSymbolAnd("startflow", s)) {
			return FLOW;
		}
		if (StartUtils.startsWithSymbolAnd("startcreole", s)) {
			return CREOLE;
		}
		if (StartUtils.startsWithSymbolAnd("starttree", s)) {
			return JUNGLE;
		}
		if (StartUtils.startsWithSymbolAnd("startcute", s)) {
			return CUTE;
		}
		if (StartUtils.startsWithSymbolAnd("startmath", s)) {
			return MATH;
		}
		if (StartUtils.startsWithSymbolAnd("startlatex", s)) {
			return LATEX;
		}
		if (StartUtils.startsWithSymbolAnd("startdef", s)) {
			return DEFINITION;
		}
		if (StartUtils.startsWithSymbolAnd("startgantt", s)) {
			return GANTT;
		}
		if (StartUtils.startsWithSymbolAnd("startnwdiag", s)) {
			return NW;
		}
		if (StartUtils.startsWithSymbolAnd("startmindmap", s)) {
			return MINDMAP;
		}
		if (StartUtils.startsWithSymbolAnd("startwbs", s)) {
			return WBS;
		}
		return UNKNOWN;
	}
}
