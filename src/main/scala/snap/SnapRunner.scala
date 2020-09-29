package snap
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object SnapRunner {

  def main(args: Array[String]): Unit = {

    val players = args.head.toInt
    val game = new Snap(players)

    val cards = game.deck.shuffle()

    val playerHands: List[(Int, mutable.Buffer[Card])] = game.dealer.dealHands(players, cards)

    while (game.checkWinner(playerHands, 52) == 0) {

      playSnap(game, playerHands)

    }

    if (game.checkWinner(playerHands, cards.length) != 0)
      println(s"Player ${game.checkWinner(playerHands, cards.length)} won!")

  }

  private[snap] def playSnap(game: Snap, playerHands: List[(Int, mutable.Buffer[Card])]): Unit = {
    playerHands.foreach { playerHand: (Int, mutable.Buffer[Card]) =>
      pickUpPileIfHandIsEmpty(game, game.pile, playerHand)
      game.playCard(playerHand)
      if (game.matchCard(game.pile)) {
        game.pickUpPile(game.pile, playerHand)
      }
    }
  }

  private[snap] def pickUpPileIfHandIsEmpty(game: Snap, pile: ListBuffer[Card], playerHand:(Int, mutable.Buffer[Card])): Unit = {
      if (playerHand._2.isEmpty )
        game.pickUpPile(pile, playerHand)
  }

}
