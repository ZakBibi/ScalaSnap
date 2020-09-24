package snap

import org.scalatest.flatspec.AnyFlatSpec

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

class SnapSpec extends AnyFlatSpec {

  private val snap = new Snap(2)

  it can "play a card" in {
    val playerHand = List((1, ArrayBuffer(
      Card("Ace", "Hearts"),
      Card("2", "Spades"),
      Card("3", "Diamonds"),
      Card("4", "Clubs")
    )))

    snap.playCard(playerHand)
    assert(
      snap.pile == ListBuffer(Card("Ace", "Hearts")) &&
      playerHand.head == (1, ArrayBuffer(Card("2", "Spades"), Card("3", "Diamonds"), Card("4", "Clubs")))
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
    val playerHand = List((1, ArrayBuffer(Card("2", "Hearts"))))
    snap.pickUpPile(pile, playerHand)
    assert(playerHand == List((1, ArrayBuffer(Card("2", "Hearts"), Card("Ace", "Spades")))) &&
    pile.isEmpty)
  }

  it should "check winner" in {
    val deck = ListBuffer(Card("Ace", "Hearts"), Card("2", "Spades"), Card("3", "Diamonds"), Card("4", "Clubs")
    )
    val playerHands = List(
      (1, ArrayBuffer[Card]()),
      (2, ArrayBuffer[Card]()),
      (3, ArrayBuffer[Card](Card("2", "Hearts"), Card("Ace", "Spades"), Card("3", "Diamonds"), Card("4", "Clubs")))
    )
    assert(snap.checkWinner(playerHands, deck.length) == 3)
  }

  it should "return null if there is no winner" in {
    val deck = ListBuffer(Card("Ace", "Hearts"), Card("2", "Spades"), Card("3", "Diamonds"), Card("4", "Clubs")
    )
    val playerHands = List(
      (1, ArrayBuffer[Card]()),
      (2, ArrayBuffer[Card](Card("2", "Hearts"), Card("Ace", "Spades"))),
      (3, ArrayBuffer[Card](Card("3", "Diamonds"), Card("4", "Clubs")))
    )
    assert(snap.checkWinner(playerHands, deck.length) == 0)
  }

}
