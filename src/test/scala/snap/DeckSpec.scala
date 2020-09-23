package snap

import org.scalatest.flatspec.AnyFlatSpec

class DeckSpec extends AnyFlatSpec {

  val cardDeck = new Deck

  it should "be equal" in {
    assert(cardDeck == new Deck)
  }

  it should "construct deck" in {
    assert(cardDeck.deck.length == 52)
  }

  it should "shuffle deck" in {
    val shuffled_deck = cardDeck.shuffle()
    assert(cardDeck.deck != shuffled_deck)
  }

}
