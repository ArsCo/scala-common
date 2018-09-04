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

/** Tests for [[Bound]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.5
  */
class BoundTest extends AbstractBaseTest {
  "Bound" must "have finite and infinite subclasses" in {
    assert(classOf[FiniteBound[_]].getSuperclass == classOf[Bound[_]])
    assert(classOf[Incl[_]].getSuperclass == classOf[FiniteBound[_]])
    assert(classOf[Excl[_]].getSuperclass == classOf[FiniteBound[_]])

//    assert(classOf[InfiniteBound].getSuperclass == classOf[Bound])
//    assert(classOf[PositiveInfinityBound.type].getSuperclass == classOf[InfiniteBound])
//    assert(classOf[NegativeInfinityBound.type].getSuperclass == classOf[InfiniteBound])
  }

  "Incl" must "validate args" in {
    Incl(5)
    Incl("string")

    intercept[IllegalArgumentException] {
      Incl(null)
    }
  }

  it must
  "be serializable" in {
    pending
  }


  "Excl" must "validate args" in {
    Excl(5)
    Excl("string")

    intercept[IllegalArgumentException] {
      Excl(null)
    }
  }

  it must "be serializable" in {
    pending
  }


  "isIncluded()" must "return `true` for Incl" in {
    assertResult(true)(Incl(5).isIncluded)
  }

  it must "return `false` for Excl" in {
    assertResult(false)(Excl(5).isIncluded)
  }

  "isExcluded()" must "return `false` for Incl" in {
    assertResult(false)(Incl(5).isExcluded)
  }

  it must "return `true` for Excl" in {
    assertResult(true)(Excl(5).isExcluded)
  }

  "value()" must "return value for Incl & Excl" in {
    assertResult(12)(Excl(12).value)
    assertResult("asdfg")(Excl("asdfg").value)
    assertResult(12)(Incl(12).value)
    assertResult("asdfg")(Incl("asdfg").value)
  }

  "isFinite()" must "return `true` for Incl & Excl" in {
    assertResult(true)(Incl(5).isFinite)
    assertResult(true)(Excl(4).isFinite)
  }
}
