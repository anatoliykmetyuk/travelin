package game
package logic

import scala.util.Random
import scala.collection.mutable.{ Stack, ListBuffer }
import Country._, Special._, Action._

class Game extends GameSetup with GameLifecycle:
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
