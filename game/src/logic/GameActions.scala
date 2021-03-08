package game
package logic

trait GameActions:
  this: Game =>

  def dealFromDeck(player: Player, amount: Int): Unit
  def populateCommonCards(): Unit
  def playCountryCard(player: Player, card: Int): Unit
  def setTurnPhase(phase: TurnPhase): Unit