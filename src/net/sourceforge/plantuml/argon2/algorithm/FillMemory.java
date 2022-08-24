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
/* 	This file is taken from
	https://github.com/andreas1327250/argon2-java

	Original Author: Andreas Gadermaier <up.gadermaier@gmail.com>
 */
package net.sourceforge.plantuml.argon2.algorithm;

import static net.sourceforge.plantuml.argon2.Constants.ARGON2_SYNC_POINTS;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import net.sourceforge.plantuml.argon2.model.Instance;
import net.sourceforge.plantuml.argon2.model.Position;

public class FillMemory {

    public static void fillMemoryBlocks(Instance instance) {
        if (instance.getLanes() == 1) {
            fillMemoryBlockSingleThreaded(instance);
        } else {
            fillMemoryBlockMultiThreaded(instance);
        }
    }

    private static void fillMemoryBlockSingleThreaded(Instance instance) {
        for (int i = 0; i < instance.getIterations(); i++) {
            for (int j = 0; j < ARGON2_SYNC_POINTS; j++) {
                Position position = new Position(i, 0, j, 0);
                FillSegment.fillSegment(instance, position);
            }
        }
    }

    private static void fillMemoryBlockMultiThreaded(final Instance instance) {

        ExecutorService service = Executors.newFixedThreadPool(instance.getLanes());
        List<Future<?>> futures = new ArrayList<Future<?>>();

        for (int i = 0; i < instance.getIterations(); i++) {
            for (int j = 0; j < ARGON2_SYNC_POINTS; j++) {
                for (int k = 0; k < instance.getLanes(); k++) {

                    final Position position = new Position(i, k, j, 0);

                    Future future = service.submit(new Runnable() {
                        @Override
                        public void run() {
                            FillSegment.fillSegment(instance, position);
                        }
                    });

                    futures.add(future);
                }

                joinThreads(instance, futures);
            }
        }

        service.shutdownNow();
    }

    private static void joinThreads(Instance instance, List<Future<?>> futures) {
        try {
            for (Future<?> f : futures) {
                f.get();
            }
        } catch (InterruptedException e) {
            instance.clear();
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            instance.clear();
            throw new RuntimeException(e);
        }
    }
}
