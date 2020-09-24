package snap

import scala.collection.mutable

object Dealer {

  def dealHands(deckOfCards: mutable.Buffer[Card], numberOfPlayers: Int): List[(Int, mutable.Buffer[Card])] = {
    (0 until numberOfPlayers)
      .map {
        e => (e + 1,
          (e until deckOfCards.length by numberOfPlayers)
            .map {
              i => deckOfCards(i)
            }.toBuffer)
      }
  }.toList

}
