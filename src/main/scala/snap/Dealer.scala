package snap

object Dealer {

  def dealHands(hand: List[Card], numberOfPlayers: Int): List[List[Card]] = {
    (0 until numberOfPlayers)
      .map {
        e =>
          (e until hand.length by numberOfPlayers)
            .map {
              i => hand(i)
            }.toList
      }.toList
  }

}
