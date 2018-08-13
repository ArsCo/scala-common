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

package ars.common.enumeration

import ars.common.AbstractBaseTest

import scala.util.Failure

/** Tests for [[EnumObject]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
class EnumObjectTest extends AbstractBaseTest {

  sealed abstract class MyEnumValue(override val code: String) extends EnumValue[String]

  object MyEnumValues extends EnumObject[MyEnumValue, String] {
    final case object FirstValue  extends MyEnumValue("first")
    final case object SecondValue extends MyEnumValue("second")
    final case object ThirdValue  extends MyEnumValue("third")

    override def values: Seq[MyEnumValue] = Seq(FirstValue, SecondValue, ThirdValue)
  }

  "valueOf()" must "validate args" in {
    intercept[IllegalArgumentException] {
      MyEnumValues.valueOf(null)
    }
  }

  it must "get correct enum instance by code" in {
    import MyEnumValues._

    assertResult(FirstValue){ valueOf("first").get }
    assertResult(SecondValue){ valueOf("second").get }
    assertResult(ThirdValue){ valueOf("third").get }

    valueOf("forth") match {
      case Failure(_) =>
      case _ => fail()
    }
  }

  it must "get correct enum instance by name" in {
    import MyEnumValues._

    assertResult(FirstValue){ valueByName("FirstValue").get }
    assertResult(SecondValue){ valueByName("SecondValue").get }
    assertResult(ThirdValue){ valueByName("ThirdValue").get }

    valueOf("ForthValue") match {
      case Failure(_) =>
      case _ => fail()
    }
  }
}
