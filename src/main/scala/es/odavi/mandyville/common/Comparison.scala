package es.odavi.mandyville.common

import es.odavi.mandyville.{Context, Predictor}
import es.odavi.mandyville.common.entity.Player

/** A comparison result between two values
  *
  * @param expected the value we expected to get, generally the result
  *                 of a prediction
  * @param actual the value that occurred in real life
  */
case class Comparison(
  player: Player,
  context: Context,
  predictor: Predictor,
  expected: BigDecimal,
  actual: BigDecimal
) {

  /** The absolute difference between the expected and actual value
    */
  def difference: BigDecimal = expected.abs - actual.abs

  override def toString: String = s"$player: $expected, $actual"
}
