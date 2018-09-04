/*
 * Copyright 2018 Arsen Ibragimov (ars)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ars.common.interval

import ars.common.AbstractBaseTest

/** Tests for [[Finiteable]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.5
  */
class FiniteableTest extends AbstractBaseTest {

  "Finiteable" must "have correct signature" in {
    val f = new Finiteable {
      override def isFinite: Boolean = true
    }

    assert(f != null)
    assertResult(true)(f.isFinite)
    assertResult(false)(f.nonFinite)
  }

  it must "be serializable" in {
    pending
  }


  "isFinite" must "be !nonFinite" in {
    case class F(isFinite: Boolean)extends Finiteable

    val f1 = F(true)
    assert(f1 != null)
    assertResult(true)(f1.isFinite)
    assertResult(false)(f1.nonFinite)

    val f2 = F(false)
    assert(f2 != null)
    assertResult(false)(f2.isFinite)
    assertResult(true)(f2.nonFinite)
  }
}
