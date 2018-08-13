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

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, ObjectInputStream, ObjectOutputStream}

import ars.common.AbstractBaseTest

import scala.reflect.runtime.universe._

/** Tests for [[SerializableEnumValue]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
class SerializableEnumValueTest extends AbstractBaseTest {

  "SerializableEnumValue" must "be serializable" in {
    val oos = new ObjectOutputStream(new ByteArrayOutputStream())
    oos.writeObject(MyEnumValues.FirstValue)
  }

  it must "be deserializable" in {
    val os = new ByteArrayOutputStream()
    val oos = new ObjectOutputStream(os)
    oos.writeObject(MyEnumValues.FirstValue)
    oos.flush()

    val serializedEnum = os.toByteArray

    val ois = new ObjectInputStream(new ByteArrayInputStream(serializedEnum))
    val obj = ois.readObject()
    assert(obj.isInstanceOf[MyEnumValues.FirstValue.type], obj.getClass.getCanonicalName)
  }
}

sealed abstract class MyEnumValue(override val code: Int) extends SerializableEnumValue[MyEnumValue, MyEnumValues.type, Int] {
  override protected[this] val objectTypeTag: TypeTag[MyEnumValues.type] = typeTag[MyEnumValues.type]

  override def serialize(out: ObjectOutputStream): Unit = {}
  override def deserialize(in: ObjectInputStream): Int = 1
}

object MyEnumValues extends EnumObject[MyEnumValue, Int] {
  final case object FirstValue extends MyEnumValue(1)
  final case object SecondValue extends MyEnumValue(2)
  final case object ThirdValue extends MyEnumValue(3)

  override def values: Seq[MyEnumValue] = Seq(FirstValue, SecondValue, ThirdValue)
}
