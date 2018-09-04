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

import ars.common.interval.implicits._

/** Represents interval of values.
  *
  * @tparam V the values type
  * @tparam L the lower bound type
  * @tparam U the upper bound type
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
abstract class Interval[+V, +L <: Bound[V], +U <: Bound[V]] extends Finiteable {

  /**
    * @return the lower bound (non-null)
    */
  def lower: L

  /**
    * @return the upper bound (non-null)
    */
  def upper: U

//  /**
//    * Test if interval is empty. TODO
//    *
//    * @return `true` if interval is empty, `false` otherwise
//    */
//  def isEmpty: Boolean

//  /**
//    * Test if interval is not empty. TODO
//    *
//    * @return `true` if interval is not empty, `false` otherwise
//    */
//  def nonEmpty: Boolean = !isEmpty


  override def isFinite: Boolean = lower.isFinite && upper.isFinite
}

object Interval {

  /**
    * Creates a finite interval.
    *
    * @param lower the lower bound (must be non-null)
    * @param upper the upper bound (must be non-null)
    * @tparam V the value type
    * @tparam L the lower bound type
    * @tparam U the upper bound type
    * @return the new finite interval (non-null)
    */
  def of[V, L <: FiniteBound[V], U <: FiniteBound[V]](lower: L, upper: U): FiniteInterval[V, L, U] = {
    FiniteInterval[V, L, U](lower, upper)
  }

  //  /** TODO
  //    * Creates a finite interval with specific comparator.
  //    *
  //    * @param lower the lower bound (must be non-null)
  //    * @param upper the upper bound (must be non-null)
  //    * @param comparator the comparator (must be non-null)
  //    *
  //    * @tparam T the value type
  //    *
  //    * @return the new finite interval (non-null)
  //    */
  //  def of[T](lower: FiniteBound[T], upper: FiniteBound[T], comparator: Comparator[T]): ComparableFiniteInterval[T] = {
  //    ComparableFiniteInterval(lower, upper, comparator)
  //  }

  /**
    * Creates a finite open interval (both boundaries are excluded).
    *
    * @param lower the lower bound (must be non-null)
    * @param upper the upper bound (must be non-null)
    * @tparam V the value type
    * @return the new finite open interval (non-null)
    */
  def open[V](lower: V, upper: V): FiniteInterval[V, Excl[V], Excl[V]] = {
    FiniteInterval[V, Excl[V], Excl[V]](lower.excl, upper.excl)
  }

  //  /**
  //    * Creates a finite open interval with specific comparator (both boundaries are excluded).
  //    *
  //    * @param from the lower bound (must be non-null)
  //    * @param until the upper bound (must be non-null)
  //    * @param comparator the comparator (must be non-null)
  //    *
  //    * @tparam T the value type
  //    *
  //    * @return the new finite open interval (non-null)
  //    */
  //  def open[T](from: T, until: T, comparator: Comparator[T]): ComparableFiniteInterval[T] = {
  //    ComparableFiniteInterval(Exclusive(from), Exclusive(until), comparator)
  //  }

  /**
    * Creates a finite closed interval (both boundaries are included).
    *
    * @param lower the lower bound (must be non-null)
    * @param upper the upper bound (must be non-null)
    * @tparam V the value type
    * @return the new finite closed interval (non-null)
    */
  def closed[V](lower: V, upper: V): FiniteInterval[V, Incl[V], Incl[V]] = {
    FiniteInterval[V, Incl[V], Incl[V]](lower.incl, upper.incl)
  }

  //  /**
  //    * Creates a finite closed interval with specific comparator (both boundaries are included).
  //    *
  //    * @param from the lower bound (must be non-null)
  //    * @param to the upper bound (must be non-null)
  //    * @param comparator the comparator (must be non-null)
  //    *
  //    * @tparam T the value type
  //    *
  //    * @return the new finite closed interval (non-null)
  //    */
  //  def closed[T](from: T, to: T, comparator: Comparator[T]): ComparableFiniteInterval[T] = {
  //    ComparableFiniteInterval(Inclusive(from), Inclusive(to), comparator)
  //  }

  /**
    * Creates a finite left-open interval with specific comparator (left bound is excluded, right is included).
    *
    * @param lower the lower bound (must be non-null)
    * @param upper the upper bound (must be non-null)
    * @tparam V the value type
    * @return the new finite left-open interval (non-null)
    */
  def leftOpen[V](lower: V, upper: V): FiniteInterval[V, Excl[V], Incl[V]] = {
    FiniteInterval[V, Excl[V], Incl[V]](lower.excl, upper.incl)
  }

  //  /**
  //    * Creates a finite left-open interval with specific comparator (left bound is excluded, right is included).
  //    *
  //    * @param from the lower bound (must be non-null)
  //    * @param until the upper bound (must be non-null)
  //    * @param comparator the comparator (must be non-null)
  //    *
  //    * @tparam T the value type
  //    *
  //    * @return the new finite open interval (non-null)
  //    */
  //  def leftOpen[T](from: T, to: T, comparator: Comparator[T]): ComparableFiniteInterval[T] = {
  //    ComparableFiniteInterval(Exclusive(from), Inclusive(to), comparator)
  //  }

  /**
    * Creates a finite right-open interval with specific comparator (left bound is included, right is excluded).
    *
    * @param lower the lower bound (must be non-null)
    * @param upper the upper bound (must be non-null)
    * @tparam V the value type
    * @return the new finite right-open interval (non-null)
    */
  def rightOpen[V](lower: V, upper: V): FiniteInterval[V, Incl[V], Excl[V]] = {
    FiniteInterval[V, Incl[V], Excl[V]](lower.incl, upper.excl)
  }

  //  /**
  //    * Creates a finite right-open interval with specific comparator (left bound is included, right is excluded).
  //    *
  //    * @param from the lower bound (must be non-null)
  //    * @param until the upper bound (must be non-null)
  //    * @param comparator the comparator (must be non-null)
  //    *
  //    * @tparam T the value type
  //    *
  //    * @return the new finite open interval (non-null)
  //    */
  //  def rightOpen[T](from: T, to: T, comparator: Comparator[T]): ComparableFiniteInterval[T] = {
  //    ComparableFiniteInterval(Inclusive(from), Exclusive(to), comparator)
  //  }
}