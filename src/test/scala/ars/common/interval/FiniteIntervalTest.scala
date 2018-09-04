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

/** Tests for [[FiniteInterval]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
class FiniteIntervalTest extends AbstractBaseTest {
  "FiniteInterval" must "validate args" in {
    FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Incl(3), Incl(100))
    FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Incl(3), Incl(3))
    FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Excl(3), Excl(3))
    FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Incl(3), Excl(3))
    FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Excl(3), Incl(3))
    FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Incl(-3), Excl(3))
    FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Excl(-3), Incl(3))
    FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Incl(-30), Excl(-3))
    FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Excl(-30), Incl(-3))
    FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Incl(-30), Excl(0))
    FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Excl(-30), Incl(0))
    FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Incl(0), Excl(3))
    FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Excl(0), Incl(3))


    intercept[IllegalArgumentException] {
      FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](null, Incl(100))
    }

    intercept[IllegalArgumentException] {
      FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Incl(3), null)
    }

    intercept[IllegalArgumentException] {
      FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Incl(100), Incl(99))
    }

    intercept[IllegalArgumentException] {
      FiniteInterval[Double, FiniteBound[Double], FiniteBound[Double]](Incl(20.01), Incl(20.0))
    }
  }

//  "isEmpty()" must "return `true` for empty interval" in {
//    assertResult(true) {
//      FiniteInterval(Excl(5), Excl(5)).isEmpty
//    }
//
//    assertResult(true) {
//      FiniteInterval(Incl(5), Excl(5)).isEmpty
//    }
//
//    assertResult(true) {
//      FiniteInterval(Excl(5), Incl(5)).isEmpty
//    }
//  }
//
//  it must "return `false` for non-empty intervals" in {
//    assertResult(false) {
//      FiniteInterval(Incl(5), Incl(5)).isEmpty
//    }
//
//    assertResult(false) {
//      FiniteInterval(Excl(5), Excl(6)).isEmpty
//    }
//  }

  "isOpen()" must "return `true` for open interval" in {
    assertResult(true) {
      FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Excl(3), Excl(5)).isOpen
    }
  }

  it must "return `false` for non-open intervals" in {
    assertResult(false) {
      FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Incl(3), Excl(5)).isOpen
    }

    assertResult(false) {
      FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Excl(3), Incl(5)).isOpen
    }

    assertResult(false) {
      FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Incl(3), Incl(5)).isOpen
    }
  }

  "isClosed()" must "return `true` for closed interval" in {
    assertResult(true) {
      FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Incl(3), Incl(5)).isClosed
    }
  }

  it must "return `false` for non-open intervals" in {
    assertResult(false) {
      FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Incl(3), Excl(5)).isClosed
    }

    assertResult(false) {
      FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Excl(3), Incl(5)).isClosed
    }

    assertResult(false) {
      FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Excl(3), Excl(5)).isClosed
    }
  }

  "isLeftOpen()" must "return `true` for left-open interval" in {
    assertResult(true) {
      FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Excl(3), Incl(5)).isLeftOpen
    }
  }

  it must "return `false` for non-left-open intervals" in {
    assertResult(false) {
      FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Incl(3), Excl(5)).isLeftOpen
    }

    assertResult(false) {
      FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Incl(3), Incl(5)).isLeftOpen
    }

    assertResult(false) {
      FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Excl(3), Excl(5)).isLeftOpen
    }
  }

  "isRightOpen()" must "return `true` for right-open interval" in {
    assertResult(true) {
      FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Incl(3), Excl(5)).isRightOpen
    }
  }

  it must "return `false` for non-right-open intervals" in {
    assertResult(false) {
      FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Excl(3), Incl(5)).isRightOpen
    }

    assertResult(false) {
      FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Incl(3), Incl(5)).isRightOpen
    }

    assertResult(false) {
      FiniteInterval[Int, FiniteBound[Int], FiniteBound[Int]](Excl(3), Excl(5)).isRightOpen
    }
  }
}
