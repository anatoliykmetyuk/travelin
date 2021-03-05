package game

// Shuffle => Deck
opaque type Card = Action | Country | Special
opaque type Deck = List[Card]
opaque type Hand = List[Card]
opaque type Effect = Action

case class Player(
  name: String,
  hand: Hand,
  countriesPlayed: List[Country],
  capturedCountries: List[Country],  // via Russian Gambit
  extraEffects: List[Effect],
)

case class Game(
  deck: Deck,
  commonCards: (Card, Card, Card),
  discard: Deck,
)
