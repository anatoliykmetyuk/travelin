package game

import utest._

object GameTests extends TestSuite:
  val tests = Tests {
    test("Setup") {
      test("Setup deals 8 cards to each player") {
        val game = new Game
        assert(game.player1.hand.isEmpty)
        assert(game.player2.hand.isEmpty)
        assert(game.commonCards == null)
        assert(game.discard.isEmpty)

        game.setup()
        assert(game.player1.hand.size == 8)
        assert(game.player2.hand.size == 8)
        assertMatch(game.commonCards) { case (a, b, c) =>}

        assert(game.player1.countriesPlayed.isEmpty)
        assert(game.player1.extraEffects.isEmpty)
      }
    }
  }
