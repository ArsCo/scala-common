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

/** Tests for [[implicits]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.5
  */
class implicitsTest extends AbstractBaseTest {

  "incl" must "be implicit method of any type" in {
    import implicits._

    "String".incl
    5.incl
    5.6666.incl
    new Object().incl
  }

  it must "creates new instance of Incl" in {
    import implicits._

    assertResult(Incl(5)) { 5.incl }
    assertResult(Incl("asdfasf")) { "asdfasf".incl }
  }

  it must "validate args" in {
    import implicits._

    intercept[IllegalArgumentException] {
      val s: String = null
      s.incl
    }
  }

  "excl" must "be implicit method of any type" in {
    import implicits._

    "String".excl
    5.excl
    5.6666.excl
    new Object().excl
  }

  it must "creates new instance of Excl" in {
    import implicits._

    assertResult(Excl(5)) { 5.excl }
    assertResult(Excl("asdfasf")) { "asdfasf".excl }
  }

  it must "validate args" in {
    import implicits._

    intercept[IllegalArgumentException] {
      val s: String = null
      s.excl
    }
  }
}
