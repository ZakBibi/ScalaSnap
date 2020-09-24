package snap

import org.scalatest.flatspec.AnyFlatSpec

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class DealerSpec extends AnyFlatSpec {

  val deck: mutable.Buffer[Card] = mutable.Buffer(
    Card("Ace", "Hearts"),
    Card("2", "Hearts"),
    Card("3", "Hearts"),
    Card("4", "Hearts"),
    Card("5", "Hearts"),
    Card("6", "Hearts")
  )

  it should "deal 2 hands" in {
    val dealt_hands = Dealer.dealHands(deck, 2)
    assert(
      dealt_hands.head == (1, ArrayBuffer(Card("Ace", "Hearts"), Card("3", "Hearts"), Card("5", "Hearts"))) &&
      dealt_hands(1) == (2, ArrayBuffer(Card("2", "Hearts"), Card("4", "Hearts"), Card("6", "Hearts")))
    )
  }

  it should "deal 3 hands" in {
    val dealt_hands = Dealer.dealHands(deck, 3)
    assert(
        dealt_hands.head == (1, ArrayBuffer(Card("Ace", "Hearts"), Card("4", "Hearts"))) &&
        dealt_hands(1) == (2, ArrayBuffer(Card("2", "Hearts"), Card("5", "Hearts"))) &&
        dealt_hands(2) == (3, ArrayBuffer(Card("3", "Hearts"), Card("6", "Hearts")))
    )
  }

  it should "deal 3 hands when hand is uneven" in {
    val unevenDeck = mutable.Buffer(
      Card("Ace", "Hearts"),
      Card("2", "Hearts"),
      Card("3", "Hearts"),
      Card("4", "Hearts"),
      Card("5", "Hearts")
    )

    val dealt_hands = Dealer.dealHands(unevenDeck, 3)
    assert(
      dealt_hands.head == (1, ArrayBuffer(Card("Ace", "Hearts"), Card("4", "Hearts"))) &&
        dealt_hands(1) == (2, ArrayBuffer(Card("2", "Hearts"), Card("5", "Hearts"))) &&
        dealt_hands(2) == (3, ArrayBuffer(Card("3", "Hearts")))
    )
  }
}