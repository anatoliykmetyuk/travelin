package game

import GamePhase._

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
    player.hand(id) match
      case card: Country =>
        player.countriesPlayed += PlayedCountryCard(card)
        player.hand.remove(id)
      case card => error(s"Initial card must be a country card! $card is not a country card.")

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

  def setTurn(phase: Player1Turn.type | Player2Turn.type): Unit = action(AmbiguousFirstTurn) {
    phase
  }
