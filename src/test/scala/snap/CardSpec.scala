package snap

import org.scalatest.flatspec.AnyFlatSpec

class CardSpec extends AnyFlatSpec {

  val aceOfHearts: Card = Card("Ace", "Hearts")
  val aceOfSpades: Card = Card("Ace", "Spades")

  it should "be equal" in {
    assert(Card("Ace", "Hearts") == aceOfHearts)
  }

  it should "not be equal" in {
    assert(aceOfHearts != aceOfSpades)
  }

  it should "make card human readable" in {
    val card = Card("Ace", "Hearts")
    assert(card.toString == "Ace of Hearts")

  }

}

