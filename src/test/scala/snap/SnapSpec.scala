package snap

import org.scalatest.flatspec.AnyFlatSpec

import scala.collection.mutable.ListBuffer

class SnapSpec extends AnyFlatSpec {

  private val snap = new Snap(2)

  it can "play a card" in {
    val playerHand = ListBuffer(
      Card("Ace", "Hearts"),
      Card("2", "Spades"),
      Card("3", "Diamonds"),
      Card("4", "Clubs")
    )

    snap.playCard(playerHand)
    assert(
      snap.pile == ListBuffer(Card("Ace", "Hearts")) &&
      playerHand.head == Card("2", "Spades")
    )
  }

  it should "match cards" in {
    val pile = ListBuffer(Card("King", "Diamonds"), Card("King", "Clubs"))
    assert(snap.matchCard(pile))
  }

  it should "return false if pile is empty" in {
    val pile = ListBuffer[Card]()
    assert(!snap.matchCard(pile))
  }

  it should "return false if pile has one card" in {
    val pile = ListBuffer(Card("Ace", "Hearts"))
    assert(!snap.matchCard(pile))
  }

  it should "pick up pile" in {
    val pile = ListBuffer(Card("Ace", "Spades"))
    val playerHand = ListBuffer(Card("2", "Hearts"))
    snap.pickUpPile(pile, playerHand)
    assert(playerHand == ListBuffer(Card("2", "Hearts"), Card("Ace", "Spades")) &&
    pile.isEmpty)
  }


}
