# scala-common

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/ru.ars-co/scala-common_2.11/badge.svg)](https://maven-badges.herokuapp.com/maven-central/ru.ars-co/scala-common_2.11) 
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/ru.ars-co/scala-common_2.12/badge.svg)](https://maven-badges.herokuapp.com/maven-central/ru.ars-co/scala-common_2.12)

[![Coverage Status](https://coveralls.io/repos/github/ArsCo/scala-common/badge.svg?branch=master)](https://coveralls.io/github/ArsCo/scala-common?branch=master)
[![Licence](https://img.shields.io/badge/license-Apache_2.0-blue.svg)](https://tldrlegal.com/license/apache-license-2.0-(apache-2.0))
[![Latest release](https://img.shields.io/github/release/ArsCo/scala-common/all.svg)](https://github.com/ArsCo/scala-common/releases/latest)
[![Code quality](https://img.shields.io/codacy/2422de7dc02a43e19cb0baf6f155cc4b.svg)](https://www.codacy.com/app/ArsCo/scala-common)
[![Gitterchat](https://img.shields.io/gitter/room/nwjs/nw.js.svg)](https://gitter.im/ars-co/scala-common)


Scala library that provides common code part or templates

## Features:
- enumeration pattern
    - enumeration value trait `EnumValue`
        - enumeration value `code` 
        - enumeration value `name`
        - common `toString` method implementation
    - enumeration object trait `EnumObject`
        - common `values` method
        - common `valueOf` method
- serializable enumeration pattern
    - all features of enumeration pattern
    - standard Java serialization/deserialization 
- Scala 2.11 and 2.12

# Usage

Add dependency to `build.sbt`:
```scala
libraryDependencies +=  "ru.ars-co" %% "scala-common" % currentVersion
```

Implement enumeration:
```scala
sealed abstract class MyIntEnumValue(override val code: Int) extends EnumValue[Int]

object MyIntEnumValues extends EnumObject[MyIntEnumValue, Int] {
  final case object FirstValue  extends MyIntEnumValue(1)
  final case object SecondValue extends MyIntEnumValue(2)
  final case object ThirdValue  extends MyIntEnumValue(3)

  override def values: Seq[MyIntEnumValue] = Seq(FirstValue, SecondValue, ThirdValue)
}
```

Use enumeration:
```scala
MyIntEnumValues.ThirdValue
MyIntEnumValues.FirstValue.code
```


# Basic enum implementation
To implement basic enum:
1. create enum value class by extending `EnumValue` trait
    1. override `code` method
1. create enum values objects by extending  `EnumObject` trait
    1. create `final case object` for each enum value
    1. override `values` method with `Seq` of all enum value objects
    
__Examples:__
```scala
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
```


# Java serializable `Int`- and `String`-based enum implementation

There are 2 ways to implement enumeration: trait-based and abstract class-based. 
The first is better when you want to subclass enum class from another class but need more code to write.
The last one provides more concise code but you need to implement `objectTypeTag()` method manually.

## Trait-based realization

To implement Java serializable enum:
1. create enum value class by extending `SerializableIntEnumValue` or 
`SerializableStringEnumValue` abstract class.
    1. override `code` method
    1. override `objectTypeTag()` method
1. create enum values objects by extending  `EnumObject` trait
    1. create `final case object` for each enum value
    1. override `values` method with `Seq` of all enum value objects


__Examples:__
```scala
sealed abstract class MyIntEnumValue(override val code: Int) 
  extends SerializableIntEnumValue[MyEnumValue, MyEnumValues.type] {
  
  override protected[this] val objectTypeTag: TypeTag[MyIntEnumValues.type] = typeTag[MyIntEnumValues.type]
}

object MyIntEnumValues extends EnumObject[MyIntEnumValue, Int] {
  final case object FirstValue extends MyIntEnumValue(1)
  final case object SecondValue extends MyIntEnumValue(2)
  final case object ThirdValue extends MyIntEnumValue(3)

  override def values: Seq[MyIntEnumValue] = Seq(FirstValue, SecondValue, ThirdValue)
}

sealed abstract class MyStringEnumValue(override val code: String) 
  extends SerializableStringEnumValue[MyEnumValue, MyEnumValues.type] {
  
    override protected[this] val objectTypeTag: TypeTag[MyStringEnumValues.type] = typeTag[MyStringEnumValues.type]
}

object MyStringEnumValues extends EnumObject[MyStringEnumValue, String] {
  final case object FirstValue  extends MyStringEnumValue("first")
  final case object SecondValue extends MyStringEnumValue("second")
  final case object ThirdValue  extends MyStringEnumValue("third")

  override def values: Seq[MyStringEnumValue] = Seq(FirstValue, SecondValue, ThirdValue)
}
```


## Abstract-based realization

To implement Java serializable enum:
1. create enum value class by extending `AbstractSerializableIntEnumValue` or 
`AbstractSerializableStringEnumValue` abstract class.
    1. override `code` method
1. create enum values objects by extending  `EnumObject` trait
    1. create `final case object` for each enum value
    1. override `values` method with `Seq` of all enum value objects


__Examples:__
```scala
sealed abstract class MyIntEnumValue(override val code: Int) 
  extends AbstractSerializableIntEnumValue[MyEnumValue, MyEnumValues.type]

object MyIntEnumValues extends EnumObject[MyIntEnumValue, Int] {
  final case object FirstValue extends MyIntEnumValue(1)
  final case object SecondValue extends MyIntEnumValue(2)
  final case object ThirdValue extends MyIntEnumValue(3)

  override def values: Seq[MyIntEnumValue] = Seq(FirstValue, SecondValue, ThirdValue)
}

sealed abstract class MyStringEnumValue(override val code: String) 
  extends AbstractSerializableStringEnumValue[MyEnumValue, MyEnumValues.type]

object MyStringEnumValues extends EnumObject[MyStringEnumValue, String] {
  final case object FirstValue  extends MyStringEnumValue("first")
  final case object SecondValue extends MyStringEnumValue("second")
  final case object ThirdValue  extends MyStringEnumValue("third")

  override def values: Seq[MyStringEnumValue] = Seq(FirstValue, SecondValue, ThirdValue)
}
```

# Copyright

Copyright 2018 Arsen Ibragimov (ars)