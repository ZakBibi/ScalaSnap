package snap

import scala.util.Random

class Deck {

  override def equals(that: Any): Boolean = {
    def canEqual(a: Any): Boolean = a.isInstanceOf[Deck]

    that match {
      case that: Deck =>
        canEqual(this) &&
          this.deck == that.deck
      case _ => false
    }
  }

  override def hashCode: Int = {
    val prime = 31
    var result = 1
    result = prime * result + deck.hashCode
    result
  }

  val deck: List[Card] = constructDeck()

  private def constructDeck(): List[Card] = {
    val suits = Set("Hearts", "Clubs", "Diamonds", "Spades")
    val ranks = List("Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King")
    for (r <- ranks; s <- suits) yield Card(r, s)
  }

  def shuffle(): List[Card] = Random.shuffle(deck)

}
