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
package net.sourceforge.plantuml.nwdiag.core;

import net.sourceforge.plantuml.nwdiag.next.NStage;
import net.sourceforge.plantuml.ugraphic.color.HColor;

public class Network implements NStackable {

	private final String name;
	private String description;
	private HColor color;
	private boolean visible = true;
	private String ownAdress;
	private double y;
	private boolean fullWidth;
	private final NStage up;

	private final NStage nstage;

	@Override
	public String toString() {
		return name;
	}

	private boolean isEven() {
		return nstage.getNumber() % 2 == 0;
	}

	public double magicDelta() {
		if (isVisible() == false)
			return 0;
		if (isEven())
			return 2;
		else
			return -2;
	}

	public NStage getUp() {
		return up;
	}

	public Network(NStage up, NStage nstage, String name) {
		this.up = up;
		this.name = name;
		this.nstage = nstage;
	}

	public final String getOwnAdress() {
		return ownAdress;
	}

	public final void setOwnAdress(String ownAdress) {
		this.ownAdress = ownAdress;
	}

	public final String getDisplayName() {
		if (this.description == null) 
			return name;
		
		return this.description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	public final HColor getColor() {
		return color;
	}

	@Override
	public final void setColor(HColor color) {
		this.color = color;
	}

	public final void goInvisible() {
		this.visible = false;
	}

	public final boolean isVisible() {
		return visible;
	}

	public final double getY() {
		return y;
	}

	public final void setY(double y) {
		this.y = y;
	}

	public void setFullWidth(boolean fullWidth) {
		this.fullWidth = fullWidth;
	}

	public final boolean isFullWidth() {
		return fullWidth;
	}

	public final NStage getNstage() {
		return nstage;
	}

	private double xmin;
	private double xmax;

	public void setMinMax(double xmin, double xmax) {
		this.xmin = xmin;
		this.xmax = xmax;
	}

	public final double getXmin() {
		return xmin;
	}

	public final double getXmax() {
		return xmax;
	}

}
