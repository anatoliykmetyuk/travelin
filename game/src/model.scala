package game

import scala.util.Random

opaque type Foo = List[Int]
class DeckBuilder:
  def deck: Foo =
    val unshuffled =
      List.fill[Int](10)(10)
    Random.shuffle(unshuffled)  // CRASH
end DeckBuilder
