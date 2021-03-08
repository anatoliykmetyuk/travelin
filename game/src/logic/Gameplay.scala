package game
package logic

trait Gameplay:
  this: Game =>

  var turnPhase = null

  // === Draw Phase ===
  def drawFromDeck() = turnAction(Draw) {
    dealFromDeck(currentPlayer, 2)
    Action
  }

  def drawCommon(id: Int) = turnAction(Draw) {
    dealFromCommonCards(currentPlayer, id)
    populateCommonCards()
    Action
  }

  def drawDiscard() = turnAction(Draw) {
    dealFromDiscard(currentPlayer, 1)
  }

  // === Action Phase ===
  def travel(country: Int, actions: List[Int], countryActions: List[Int]): Unit = turnAction(TurnAction) {
    val cCard = currentPlayer.getCountry(country)
    val aCards = actions.map(currentPlayer.getAction)
    if possibleToTravel(country.card, actions.cards, countryActions.cards)
    then  // TODO check available action count
      placeCountryCard(country.card)
      discard(action.cards)
      useCountryAction(countryActions.cards)
  }
