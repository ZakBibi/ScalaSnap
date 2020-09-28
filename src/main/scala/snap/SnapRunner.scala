package snap
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object SnapRunner {

  def main(args: Array[String]): Unit = {

    val players = args.head.toInt
    val game = new Snap(players)

    val cards = game.deck.shuffle()

    val playerHands: List[(Int, mutable.Buffer[Card])] = game.dealer.dealHands(players, cards)

    while (game.checkWinner(playerHands, 52) == 0) {

      playSnap(game, playerHands)

      checkEmptyPlayerHands(game, playerHands)

    }

    if (game.checkWinner(playerHands, cards.length) != 0)
      println(s"Player ${game.checkWinner(playerHands, cards.length)} won!")

  }

  private[snap] def playSnap(game: Snap, playerHands: List[(Int, mutable.Buffer[Card])]): Unit = {
    playerHands.indices.foreach { h =>
      checkEmptyPlayerHands(game, playerHands)
      game.playCard(playerHands(h))
      if (game.matchCard(game.pile)) {
        game.pickUpPile(game.pile, playerHands(h))
      }
    }
  }

  private[snap] def checkEmptyPlayerHands(game: Snap, playerHands: List[(Int, mutable.Buffer[Card])]): Unit = {
    playerHands.foreach { e =>
      if (e._2 == ArrayBuffer[Card]())
        game.pickUpPile(game.pile, e)
    }
  }

}
