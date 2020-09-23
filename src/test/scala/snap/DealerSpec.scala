package snap

import org.scalatest.flatspec.AnyFlatSpec

class DealerSpec extends AnyFlatSpec {

  val deck = List(
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
      dealt_hands.head == List(Card("Ace", "Hearts"), Card("3", "Hearts"), Card("5", "Hearts")) &&
      dealt_hands(1) == List(Card("2", "Hearts"), Card("4", "Hearts"), Card("6", "Hearts"))
    )
  }

  it should "deal 3 hands" in {
    val dealt_hands = Dealer.dealHands(deck, 3)
    assert(
        dealt_hands.head == List(Card("Ace", "Hearts"), Card("4", "Hearts")) &&
        dealt_hands(1) == List(Card("2", "Hearts"), Card("5", "Hearts")) &&
        dealt_hands(2) == List(Card("3", "Hearts"), Card("6", "Hearts"))
    )
  }

  it should "deal 3 hands when hand is uneven" in {
    val unevenDeck = List(
      Card("Ace", "Hearts"),
      Card("2", "Hearts"),
      Card("3", "Hearts"),
      Card("4", "Hearts"),
      Card("5", "Hearts")
    )

    val dealt_hands = Dealer.dealHands(unevenDeck, 3)
    assert(
      dealt_hands.head == List(Card("Ace", "Hearts"), Card("4", "Hearts")) &&
        dealt_hands(1) == List(Card("2", "Hearts"), Card("5", "Hearts")) &&
        dealt_hands(2) == List(Card("3", "Hearts"))
    )
  }
}