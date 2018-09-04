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

/** Tests for [[Interval]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.5
  */
class IntervalTest extends AbstractBaseTest {

  "Interval" must "have correct interface" in {
    new Interval[String, Bound[String], Bound[String]] {
      override def lower: Bound[String] = ???
      override def upper: Bound[String] = ???
//      override def isEmpty: Boolean = ???
    }
  }

//  "nonEmpty()" must "be !isEmpty()" in {
//    val i = new Interval[String, Bound[String], Bound[String]] {
//      override def lower: Bound[String] = ???
//      override def upper: Bound[String] = ???
//      override def isEmpty: Boolean = true
//    }
//
//    assertResult(true)(i.isEmpty)
//    assertResult(false)(i.nonEmpty)
//  }

  "of()" must "create correct new instance of FiniteInterval" in {
    val l = Incl(5)
    val u = Excl(10)
    val i = Interval.of[Int, l.type, u.type](l, u)
    assert(i.isInstanceOf[FiniteInterval[_,_,_]])
    assertResult(l) { i.lower }
    assertResult(u) { i.upper }
  }

  "open()" must "create correct new instance of open interval" in {
    val i = Interval.open(3, 10)
    assertResult(Excl(3)) { i.lower }
    assertResult(Excl(10)) { i.upper }
  }

  "closed()" must "create correct new instance of closed interval" in {
    val i = Interval.closed(3, 10)
    assertResult(Incl(3)) { i.lower }
    assertResult(Incl(10)) { i.upper }
  }

  "leftOpen()" must "create correct new instance of left-open interval" in {
    val i = Interval.leftOpen(3, 10)
    assertResult(Excl(3)) { i.lower }
    assertResult(Incl(10)) { i.upper }
  }

  "rightOpen()" must "create correct new instance of right-open interval" in {
    val i = Interval.rightOpen(3, 10)
    assertResult(Incl(3)) { i.lower }
    assertResult(Excl(10)) { i.upper }
  }

  "isFinite()" must "return `true` for all finite interval types" in {

    assertResult(true) {
      val l = Incl(5)
      val u = Excl(10)
      Interval.of[Int, l.type, u.type](l, u).isFinite
    }

    assertResult(true) {
      Interval.open(5, 13).isFinite
    }

    assertResult(true) {
      Interval.closed(5, 13).isFinite
    }

    assertResult(true) {
      Interval.leftOpen(5, 13).isFinite
    }

    assertResult(true) {
      Interval.rightOpen(5, 13).isFinite
    }
  }

}
