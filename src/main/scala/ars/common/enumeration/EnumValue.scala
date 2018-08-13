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

/** Enumeration value base trait.
  *
  * ''Example:''
  * {{{
  *   sealed abstract class MyEnumValue(override val code: Int) extends EnumValue[Int]
  *
  *   object MyEnumValues extends EnumObject[MyEnumValue, Int] {
  *     final case object FirstValue  extends MyEnumValue(1)
  *     final case object SecondValue extends MyEnumValue(2)
  *     final case object ThirdValue  extends MyEnumValue(3)
  *
  *     override def values: Seq[MyEnumValue] = Seq(FirstValue, SecondValue, ThirdValue)
  *   }
  * }}}
  *
  * @tparam CodeType the enumeration code type
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
trait EnumValue[CodeType] {

  /** The enum value code. */
  def code: CodeType

  /**
    * Gets enum name.
    *
    * @return enumeration value name (non-null).
    */
  def name: String = {
    nameByClassName(getClass.getName)
  }

  private[enumeration] final val UnknownName = "<Unknown name>"

  private[enumeration] def nameByClassName(className: String): String = {
    val length = className.length

    if (length > 1) {
      val startIndex = className.lastIndexOf("$", length - 2)
      if (startIndex != -1) className.substring(startIndex + 1, length - 1)
      else UnknownName
    } else UnknownName
  }

  /**
    * Default enumeration implementation of `toString`.
    *
    * It returns string containing enum name following by enum code in round brackets.
    * For example: `FirstValue(1)`
    */
  override def toString: String = s"$name($code)"
}
