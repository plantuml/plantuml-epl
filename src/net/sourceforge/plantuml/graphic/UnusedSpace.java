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
package net.sourceforge.plantuml.graphic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.ugraphic.UFont;

public class UnusedSpace {

	static class Point {
		final private double x;
		final private double y;

		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public double getDistSq(Point other) {
			final double dx = this.x - other.x;
			final double dy = this.y - other.y;
			return dx * dx + dy * dy;
		}
	}

	private static final int HALF_SIZE = 20;

	private double meanX2;
	private double meanY2;

	private final List<Point> points = new ArrayList<>();

	final private static Map<Object, UnusedSpace> cache = new HashMap<Object, UnusedSpace>();

	public static UnusedSpace getUnusedSpace(UFont font, char c) {
		final Object key = Arrays.asList(font, c);
		UnusedSpace result = cache.get(key);
		if (result == null) {
			result = new UnusedSpace(font, c);
			cache.put(key, result);
		}
		return result;
	}

	private UnusedSpace(UFont font, char c) {
		final BufferedImage im = new BufferedImage(2 * HALF_SIZE, 2 * HALF_SIZE, BufferedImage.TYPE_INT_RGB);
		final Graphics2D g2d = im.createGraphics();
		g2d.setFont(font.getUnderlayingFont());
		g2d.drawString("" + c, HALF_SIZE, HALF_SIZE);

		int minI = Integer.MAX_VALUE;
		int minJ = Integer.MAX_VALUE;
		int maxI = Integer.MIN_VALUE;
		int maxJ = Integer.MIN_VALUE;

		for (int i = 0; i < im.getWidth(); i++) {
			for (int j = 0; j < im.getHeight(); j++) {
				if (isPoint(im, i, j)) {
					if (i < minI) {
						minI = i;
					}
					if (j < minJ) {
						minJ = j;
					}
					if (i > maxI) {
						maxI = i;
					}
					if (j > maxJ) {
						maxJ = j;
					}
					points.add(new Point(i, j));
				}
			}
		}

		double min = Double.MAX_VALUE;
		for (int i = minI * 4; i <= maxI * 4; i++) {
			for (int j = minJ * 4; j < maxJ * 4; j++) {
				final Point p = new Point(i / 4.0, j / 4.0);
				final double d = biggestDistSqFromPoint(p);
				if (d < min) {
					min = d;
					this.meanX2 = i / 4.0 - HALF_SIZE;
					this.meanY2 = j / 4.0 - HALF_SIZE;
				}
			}
		}

		// g2d.setColor(Color.RED);
		// g2d.draw(new Line2D.Double(meanX2 + HALF_SIZE - 1, meanY2 + HALF_SIZE
		// - 1, meanX2 + HALF_SIZE + 1, meanY2
		// + HALF_SIZE + 1));
		// g2d.draw(new Line2D.Double(meanX2 + HALF_SIZE + 1, meanY2 + HALF_SIZE
		// - 1, meanX2 + HALF_SIZE - 1, meanY2
		// + HALF_SIZE + 1));

		// int cpt = 1;
		// try {
		// ImageIO.write(im, "png", SecurityUtils.File("c:/img" + cpt + ".png"));
		// cpt++;
		// } catch (IOException e) {
		// Logme.error(e);
		// }

	}

	private double biggestDistSqFromPoint(Point p) {
		double result = 0;
		for (Point other : points) {
			final double d = p.getDistSq(other);
			if (d > result) {
				result = d;
			}
		}
		return result;
	}

	private static boolean isPoint(BufferedImage im, int x, int y) {
		final int color = im.getRGB(x, y) & 0x00FFFFFF;
		if (color == 0) {
			return false;
		}
		return true;
	}

	public double getCenterX() {
		return meanX2;
	}

	public double getCenterY() {
		return meanY2;
	}

}
