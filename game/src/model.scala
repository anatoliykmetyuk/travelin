package game

import scala.collection.mutable.ListBuffer

import Action._, Country._, Special._

type Card = Action | Country | Special
type Effect = Action

class PlayedCountryCard(val playedCard: Country):
  val capturedCountries = ListBuffer.empty[Card]
  override def toString =
    val capturedSuffix =
      if capturedCountries.isEmpty then ""
      else s", captured: ${capturedCountries.mkString(", ")}"
    s"${playedCard}${capturedSuffix}"
end PlayedCountryCard

class Player:
  val hand = ListBuffer.empty[Card]
  val countriesPlayed = ListBuffer.empty[PlayedCountryCard]
  val extraEffects = ListBuffer.empty[Effect]

  override def toString =
    s"""Hand: ${hand.mkString(", ")}
      |Played cards:
      |${countriesPlayed.mkString("\n")}
      |Extra Effects: ${extraEffects.mkString(", ")}""".stripMargin
end Player
