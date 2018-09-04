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

import java.io.{IOException, ObjectInputStream, ObjectOutputStream, ObjectStreamException}

import ars.common.exception.ExceptionUtils

import scala.reflect.runtime.universe._
import scala.util.{Failure, Success}


// TODO: Correct example!
/** Serializable Scala enumeration value. There're two subclasses [[SerializableIntEnumValue]] fro integer codes
  * and [[SerializableStringEnumValue]] for string codes.
  *
  * Example:
  * {{{
  *   sealed abstract class MyEnumValue(val code: Int) extends SerializableIntEnum[MyEnumValue] {
  *     def valueOf(code: Int): Try[MyEnumValue] = MyEnumValues.valueByCode(code)
  *   }
  *
  *   object MyEnumValues {
  *     final case object FirstValue extends MyEnumValue(1)
  *     final case object SecondValue extends MyEnumValue(2)
  *     final case object ThirdValue extends MyEnumValue(3)
  *
  *     def valueByCode(code: Int): Try[MyEnumValue] = {
  *       code match {
  *         case FirstValue.code  => FirstValue
  *         case SecondValue.code => SecondValue
  *         case ThirdValue.code  => ThirdValue
  *         case _ => Failure(new IllegalArgumentException("Unknown enumeration code"))
  *       }
  *     }
  *   }
  * }}}
  *
  * @tparam EnumValueType the enumeration value type
  * @tparam EnumObjectType the enumeration object type
  * @tparam CodeType the code type
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
trait SerializableEnumValue[
    EnumValueType <: EnumValue[CodeType],
    EnumObjectType <: EnumObject[EnumValueType, CodeType],
    CodeType
] extends EnumValue[CodeType] with Serializable {

  protected[this] var serializationCode: CodeType = _

  protected[this] def objectTypeTag(): TypeTag[EnumObjectType]



  @throws[IOException]
  def writeObject(out: ObjectOutputStream): Unit = serialize(out)

  @throws[IOException]
  @throws[ClassNotFoundException]
  def readObject(in: ObjectInputStream): Unit = deserialize(in)

  /**
    * Serializes code to stream.
    *
    * @param out the output stream (non-null)
    */
  def serialize(out: ObjectOutputStream): Unit

  /**
    * Deserializes code from stream.
    *
    * @param in the input stream (non-null)
    *
    * @return the code (non-null)
    */
  def deserialize(in: ObjectInputStream): CodeType

  @throws[ObjectStreamException]
  def readResolve(): Any = {
      enumModule.valueOf(code) match {
      case Success(v) => v
      case Failure(e) => throw ExceptionUtils.wrapInRuntimeException(e)
    }
  }

  private def enumModule = {
    val t = objectTypeTag()
    val module = t.tpe.termSymbol.asModule

    val enumModule = t.mirror.reflectModule(module)
    enumModule.instance.asInstanceOf[EnumObjectType]
  }
}

