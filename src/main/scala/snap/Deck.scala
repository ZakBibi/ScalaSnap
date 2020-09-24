package snap

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
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

  val deck: mutable.Buffer[Card] = constructDeck()

  private def constructDeck(): mutable.Buffer[Card] = {
    val suits = Set("Hearts", "Clubs", "Diamonds", "Spades")
    val ranks = List("Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King")
    val cards = for (r <- ranks; s <- suits) yield Card(r, s)
    cards.toBuffer
  }

  def shuffle(): mutable.Buffer[Card] = Random.shuffle(deck)

}
