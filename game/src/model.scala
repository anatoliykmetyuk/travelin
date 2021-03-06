package game

import scala.util.Random
import scala.collection.mutable.{ Stack, ListBuffer }

import Action._, Country._, Special._

type Card = Action | Country | Special
type Effect = Action

class PlayedCountryCard(val playedCard: Card):
  val capturedCountries = ListBuffer.empty[Card]
  override def toString =
    val capturedSuffix =
      if capturedCountries.isEmpty then ""
      else s", captured: ${capturedCountries.mkString(", ")}"
    s"${countryCard}${capturedSuffix}"
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

class Game:
  var deck: Stack[Card] =
    def loadOf[Card](c: Card): Stack[Card] =
      Stack.fill[Card](10)(c)
    val unshuffled =
      loadOf(Bus) ++
      loadOf(Train) ++
      loadOf(ArrogantBackpacker) ++
      loadOf(CzechRepublic)
    Random.shuffle(unshuffled).asInstanceOf[Stack[Card]]
  end deck
  var commonCards: (Card, Card, Card) = null
  val player1: Player = new Player
  val player2: Player = new Player
  var discard = Stack.empty[Card]

  override def toString =
    s"""=== Game ===
      |Deck (first 10):
      |${deck.take(10).mkString("\n")}
      |
      |Common cards = $commonCards
      |Discard (first 10):
      |${discard.take(10).mkString("\n")}
      |
      |=== Players ===
      |Player1:
      |$player1
      |
      |Player2:
      |$player2""".stripMargin
end Game

enum GamePhase {
  case
    Setup,
    FirstCardSelection,
    AmbiguousFirstTurn,
    Player1Turn, Player2Turn,
}

trait GameSetup:
  this: Game =>

  def setup(): Unit = action(Setup) {
    player1.hand ++= deck.pop(8)
    player2.hand ++= deck.pop(8)
    (deck.pop(3): @unchecked) match
      case one :: two :: three :: Nil =>
        commonCards = (one, two, three)
    FirstCardSelection
  }

  def placeInitialCard(player: Player, id: Int): Unit = action(FirstCardSelection) {
    val card = player.hand.remove(id)
    player.countriesPlayed += PlayedCountryCard(card)

    if   player1.countriesPlayed.nonEmpty
      && player2.countriesPlayed.nonEmpty
    then
      val p1Value = player1.countriesPlayed.head.playedCard.value
      val p2Value = player2.countriesPlayed.head.playedCard.value
           if p1Value < p2Value then Player1Turn
      else if p1Value > p2Value then Player2Turn
      else AmbiguousFirstTurn
    else FirstCardSelection
  }

  def setTurn(phase: Player1Turn | Player2Turn): Unit = action(AmbiguousFirstTurn) {
    phase
  }
end GameSetup