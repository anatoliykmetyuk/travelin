package game

trait Gameplay:
  this: Game =>

  var turnPhase = null

  // === Draw Phase ===
  def drawFromDeck() = turnAction(Draw) {
    currentPlayer.hand ++= deck.pop(2)
    Action
  }

  def drawCommon(id: Int) = turnAction(Draw) {
    currentPlayer.hand :+= commonCards.toList(id)
    Action
  }

  def drawDiscard() = turnAction(Draw) {
    currentPlayer.hand :+= discard.pop(1)
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
