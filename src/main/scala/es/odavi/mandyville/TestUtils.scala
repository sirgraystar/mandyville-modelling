package es.odavi.mandyville

import es.odavi.mandyville.common.entity.{
  FPLPlayerGameweek,
  Fixture,
  Player,
  PlayerFixture,
  Prediction
}

import java.time.{LocalDate, LocalDateTime, ZoneOffset}
import scala.util.Random

/** Provides utility methods for testing, mostly for generating test
  * data in the form of entity case classes
  */
object TestUtils {

  /** Generates a dummy PlayerFixture and Fixture pair
    *
    * If a seasons and date are not provided, nonsensical combinations
    * may be produced.
    *
    * @param playerId - the ID of the player
    * @param teamId - the ID of the team the player played for
    * @param date - the date of the fixture - defaults to 1st Jan 2020
    * @param season the season - defaults to 2020
    * @param yellow force a yellow card - defaults to false
    * @param red force a red card - defaults to false
    */
  def getDummyFixtureInfo(
    playerId: Int,
    teamId: Int,
    otherTeamId: Int = randomInt,
    competitionId: Int = randomInt,
    date: Option[LocalDate] = maybeDate,
    season: Short = 2020,
    yellow: Boolean = false,
    red: Boolean = false
  ): (PlayerFixture, Fixture) = {
    val playerFixtureId = randomId
    val fixtureId = randomId
    val minutes = Random.between(0, 90).toShort
    (
      PlayerFixture(
        playerFixtureId,
        playerId,
        fixtureId,
        teamId,
        minutes,
        yellowCard = yellow,
        redCard = red,
        maybeShort,
        maybeShort,
        maybeShort,
        maybeBigDecimal,
        maybeBigDecimal,
        maybeBigDecimal,
        maybeBigDecimal,
        None,
        maybeShort,
        maybeBigDecimal
      ),
      Fixture(
        fixtureId,
        competitionId,
        teamId,
        otherTeamId,
        season,
        Option(teamId),
        maybeShort,
        maybeShort,
        date
      )
    )
  }

  /** Generates a dummy FPLPlayerGameweek
    *
    * @param playerId the ID for the player
    * @param gameweekId the ID of the FPLGameweek
    */
  def getDummyPerformance(playerId: Int, gameweekId: Int): FPLPlayerGameweek =
    FPLPlayerGameweek(
      randomId,
      playerId,
      gameweekId,
      randomShort,
      randomShort,
      randomShort,
      randomBigDecimal,
      randomInt,
      randomInt,
      randomInt
    )

  /** Generates a dummy Player
    *
    * @param playerId the ID to use for the player
    */
  def getDummyPlayer(playerId: Int, countryId: Int = randomInt): Player =
    Player(
      playerId,
      randomString,
      randomString,
      countryId,
      maybeInt,
      maybeInt,
      maybeInt
    )

  /** Generates a dummy FPLPlayerGameweek object
    *
    * @param playerId the ID to use for the player
    * @param fplGameweekId the ID to use for the gameweek
    */
  def getDummyPlayerGameweek(
    playerId: Int,
    fplGameweekId: Int
  ): FPLPlayerGameweek =
    FPLPlayerGameweek(
      randomId,
      playerId,
      fplGameweekId,
      randomShort,
      randomShort,
      randomShort,
      randomBigDecimal,
      randomInt,
      randomInt,
      randomInt
    )

  /** Generates a dummy Prediction object
    *
    * @param playerId the ID to use for the player
    * @param fplGameweekId the ID to use for the gameweek
    * @param predictorId the ID of the predictor - optional
    */
  def getDummyPrediction(
    playerId: Int,
    fplGameweekId: Int,
    predictorId: Int = randomId
  ): Prediction =
    Prediction(
      randomId,
      playerId,
      fplGameweekId,
      predictorId,
      randomBigDecimal
    )

  private def maybe[T](thing: T): Option[T] =
    if (Random.nextFloat() < 0.1) None else Option(thing)

  private def maybeBigDecimal: Option[BigDecimal] = maybe(randomBigDecimal)

  private def maybeDate: Option[LocalDate] = maybe(randomDate)

  private def maybeInt: Option[Int] = maybe(randomInt)

  private def maybeShort: Option[Short] = maybe(randomShort)

  private def randomBigDecimal: BigDecimal =
    BigDecimal(Random.between(0.0, 5.0))

  private def randomInt = randomId

  private def randomId: Int = Random.between(0, 10000)

  // Fix some start and end periods for plausible fixture dates and
  // pick a random date between that period
  private def randomDate: LocalDate = {
    val start = LocalDate.of(2016, 8, 1)
    val end = LocalDate.of(2021, 7, 1)

    val random = Random.between(
      start.atStartOfDay().toEpochSecond(ZoneOffset.UTC),
      end.atStartOfDay().toEpochSecond(ZoneOffset.UTC)
    )
    LocalDateTime.ofEpochSecond(random, 0, ZoneOffset.UTC).toLocalDate
  }

  private def randomShort: Short = Random.between(0, 10).toShort

  private def randomString: String = Random.nextString(10)
}
