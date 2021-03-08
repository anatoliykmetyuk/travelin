package game
package logic

import GamePhase._
import TurnPhase._

trait GameSetup:
  this: Game =>

  def setup(): Unit = action(Setup) {
    dealFromDeck(player1, 8)
    dealFromDeck(player2, 8)
    populateCommonCards()
    FirstCardSelection
  }

  def placeInitialCard(player: Player, id: Int): Unit = action(FirstCardSelection) {
    player.hand(id) match
      case card: Country => playCountryCard(player, card)
      case card => error(s"Initial card must be a country card! $card is not a country card.")

    if   player1.countriesPlayed.nonEmpty
      && player2.countriesPlayed.nonEmpty
    then
      setTurnPhase(Draw)
      val p1Value = player1.countriesPlayed.head.playedCard.value
      val p2Value = player2.countriesPlayed.head.playedCard.value
           if p1Value < p2Value then Player1Turn
      else if p1Value > p2Value then Player2Turn
      else AmbiguousFirstTurn
    else FirstCardSelection
  }

  def setTurn(phase: Player1Turn.type | Player2Turn.type): Unit = action(AmbiguousFirstTurn) {
    phase
  }
