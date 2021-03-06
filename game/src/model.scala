package game

import scala.util.Random

import Action._, Special._, Country._

def newGame: Game =
  val deck = DeckBuilder()
  val p1Hand = deck.dealHand
  val p2Hand = deck.dealHand
  val commonCards = deck.dealCommon
  val resultingDeck = deck.resulting
  Game(
    deck = resultingDeck,
    commonCards = commonCards,
    discard = Nil,
    player1 = Player(p1Hand),
    player2 = Player(p2Hand),
  )

class DeckBuilder:
  private var deck: Deck =
    def loadOf[Card](c: Card): List[Card] =
      List.fill[Card](10)(c)
    val unshuffled =
      loadOf(Bus) ++
      loadOf(Train) ++
      loadOf(ArrogantBackpacker) ++
      loadOf(CzechRepublic)
    Random.shuffle(unshuffled)
  end deck

  def take(n: Int): List[Card] =
    val res = deck.take(n)
    deck = deck.drop(n)
    res

  def dealHand: Hand = take(8)
  def dealCommon: CommonCards = take(3)
  def resulting: Deck = deck
end DeckBuilder

opaque type Card = Action | Country | Special
opaque type Deck = List[Card]
opaque type Hand = List[Card]
opaque type Effect = Action
opaque type CommonCards = (Card, Card, Card)

case class Player(
  hand: Hand,
  countriesPlayed: List[Country] = Nil,
  capturedCountries: List[Country] = Nil,  // via Russian Gambit
  extraEffects: List[Effect] = Nil,
)

case class Game(
  deck: Deck,
  commonCards: CommonCards,
  discard: Deck,
  player1: Player,
  player2: Player,
)
