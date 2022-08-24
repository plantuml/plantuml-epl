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
package net.sourceforge.plantuml.style;

public enum SName {
	activity, //
	activityBar, //
	activityDiagram, //
	actor, //
	agent, //
	analog, //
	archimate, //
	arrow, //
	artifact, //
	binary, //
	boundary, //
	box, //
	boxless, //
	caption, //
	card, //
	circle, //
	classDiagram, //
	class_, //
	clickable, //
	cloud, //
	closed, //
	collection, //
	collections, //
	component, //
	robust, //
	concise, //
	clock, //
	componentDiagram, //
	constraintArrow, //
	control, //
	database, //
	delay, //
	destroy, //
	diamond, //
	document, //
	element, //
	entity, //
	end, //
	start, //
	stop, //
	file, //
	folder, //
	footer, //
	frame, //
	ganttDiagram, //
	group, //
	groupHeader, //
	header, //
	hexagon, //
	highlight, //
	interface_, //
	jsonDiagram, //
	gitDiagram, //
	label, //
	leafNode, //
	legend, //
	lifeLine, //
	map, //
	milestone, //
	mindmapDiagram, //
	network, //
	node, //
	note, //
	nwdiagDiagram, //
	objectDiagram, //
	object, //
	package_, //
	participant, //
	partition, //
	person, //
	port, //
	queue, //
	rectangle, //
	reference, //
	referenceHeader, //
	requirement, //
	root, //
	rootNode, //
	saltDiagram, //
	separator, //
	sequenceDiagram, //
	server, //
	stack, //
	stateDiagram, //
	state, //
	stateBody, //
	stereotype, //
	storage, //
	swimlane, //
	task, //
	timeline, //
	timingDiagram, //
	title, //
	undone, //
	unstarted, //
	usecase, //

	visibilityIcon, //
	private_, //
	protected_, //
	public_, //
	IEMandatory, //
	spot, //
	spotAnnotation, //
	spotInterface, //
	spotEnum, //
	spotProtocol, //
	spotStruct, //
	spotEntity, //
	spotException, //
	spotClass, //
	spotAbstractClass, //

	wbsDiagram, //
	yamlDiagram; //

	public static String depth(int level) {
		return "depth(" + level + ")";
	}
}
