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

import ars.precondition.require.Require.Default._

/**
  *
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
@SerialVersionUID(1L)
final case class FiniteInterval[+V, +L <: FiniteBound[V], U <: FiniteBound[V]](
    override val lower: L,
    override val upper: U
) extends Interval[V, L, U] with Serializable { // TODO comparable

  requireArgs()

//  override def isEmpty: Boolean = {
//    lower.value == upper.value && (lower.isExcluded || upper.isExcluded)
//  }

  /**
    * Tests is interval open (both bounds are excluded).
    *
    * @return `true` is interval is open, otherwise `false`
    */
  def isOpen: Boolean = lower.isExcluded && upper.isExcluded

  /**
    * Tests is interval closed (both bounds are included).
    *
    * @return `true` is interval is closed, otherwise `false`
    */
  def isClosed: Boolean = lower.isIncluded && upper.isIncluded

  /**
    * Tests is interval left open (left bound is excluded, right bound is included).
    *
    * @return `true` is interval is left open, otherwise `false`
    */
  def isLeftOpen: Boolean = lower.isExcluded && upper.isIncluded

  /**
    * Tests is interval left open (left bound is included, right bound is excluded).
    *
    * @return `true` is interval is right open, otherwise `false`
    */
  def isRightOpen: Boolean = lower.isIncluded && upper.isExcluded

  private def requireArgs(): Unit ={
    requireNotNull(lower,"lower")
    requireNotNull(upper, "upper")
    require(isCompareableArgs, "Both bounds must be instance of Comparable[T] or comparator must be given.")
    require(isRightOrdering, s"Must be `lower <= upper`, but: lower=${lower.value}, upper=${upper.value}.")
  }

  private def isCompareableArgs: Boolean = { // TODO requireInstanceOf in ars.preconditions
    lower.value.isInstanceOf[Comparable[V]] && upper.value.isInstanceOf[Comparable[V]]
  }

  private def isRightOrdering: Boolean = {
    val l = lower.value.asInstanceOf[Comparable[V]]
    l.compareTo(upper.value) <= 0
  }

}
