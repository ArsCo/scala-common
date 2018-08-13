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

/** Tests for [[EnumValue]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
class EnumValueTest extends AbstractBaseTest {
  "EnumValue" must "have correct signature" in {
    new EnumValue[Int] {
      override def code: Int = 10
    }
  }

  "Test example" must "be correct" in {
    sealed abstract class MyIntEnumValue(override val code: Int) extends EnumValue[Int]

    object MyIntEnumValues extends EnumObject[MyIntEnumValue, Int] {
      final case object FirstValue  extends MyIntEnumValue(1)
      final case object SecondValue extends MyIntEnumValue(2)
      final case object ThirdValue  extends MyIntEnumValue(3)

      override def values: Seq[MyIntEnumValue] = Seq(FirstValue, SecondValue, ThirdValue)
    }

    sealed abstract class MyStringEnumValue(override val code: String) extends EnumValue[String]

    object MyStringEnumValues extends EnumObject[MyStringEnumValue, String] {
      final case object FirstValue  extends MyStringEnumValue("first")
      final case object SecondValue extends MyStringEnumValue("second")
      final case object ThirdValue  extends MyStringEnumValue("third")

      override def values: Seq[MyStringEnumValue] = Seq(FirstValue, SecondValue, ThirdValue)
    }

    // Usage
    MyIntEnumValues.ThirdValue
    MyIntEnumValues.FirstValue.code

    MyStringEnumValues.ThirdValue
    MyStringEnumValues.FirstValue.code
  }

  "name" must "return enumeration value name" in {
    sealed abstract class MyEnumValue(override val code: Int) extends EnumValue[Int]

    object MyEnumValues extends EnumObject[MyEnumValue, Int] {
      final case object FirstValue  extends MyEnumValue(1)
      final case object SecondValue extends MyEnumValue(2)
      final case object ThirdValue  extends MyEnumValue(3)

      override def values: Seq[MyEnumValue] = Seq(FirstValue, SecondValue, ThirdValue)
    }

    import MyEnumValues._

    assertResult("FirstValue") { FirstValue.name }
    assertResult("SecondValue") { SecondValue.name }
    assertResult("ThirdValue") { ThirdValue.name }
  }

  "toString()" must "be correct" in {
    sealed abstract class MyEnumValue(override val code: Int) extends EnumValue[Int]

    object MyEnumValues extends EnumObject[MyEnumValue, Int] {
      final case object FirstValue  extends MyEnumValue(1)
      final case object SecondValue extends MyEnumValue(2)
      final case object ThirdValue  extends MyEnumValue(3)

      override def values: Seq[MyEnumValue] = Seq(FirstValue, SecondValue, ThirdValue)
    }

    import MyEnumValues._

    assertResult("FirstValue(1)") { FirstValue.toString }
    assertResult("SecondValue(2)") { SecondValue.toString }
    assertResult("ThirdValue(3)") { ThirdValue.toString }
  }

  "nameByClassName" must "get correct enum class name" in {
    sealed abstract class MyEnumValue(override val code: Int) extends EnumValue[Int]

    object MyEnumValues extends EnumObject[MyEnumValue, Int] {
      final case object FirstValue  extends MyEnumValue(1)
      final case object SecondValue extends MyEnumValue(2)
      final case object ThirdValue  extends MyEnumValue(3)

      override def values: Seq[MyEnumValue] = Seq(FirstValue, SecondValue, ThirdValue)
    }


    assertResult("FirstValue") {
      val f = MyEnumValues.FirstValue
      f.nameByClassName(f.getClass.getName)
    }
  }

  it must "get <Unknown name> if class name don't match pattern" in {
    val e = new EnumValue[Int] { override def code: Int = 10 }

    assertResult(e.UnknownName) {
      e.nameByClassName("")
    }
    assertResult(e.UnknownName) {
      e.nameByClassName("ForName")
    }
  }
}
