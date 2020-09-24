package snap
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class Snap(private val numberOfPlayers: Int) {

  val deck = new Deck
  val dealer: Dealer.type = Dealer
  var pile: ListBuffer[Card] = ListBuffer[Card]()

  def playCard(playerHand: List[(Int, mutable.Buffer[Card])]): Unit = {
    pile += playerHand.head._2.head
    playerHand.head._2 -= playerHand.head._2.head
  }

  def matchCard(pile: ListBuffer[Card]): Boolean = {
    if (pile.isEmpty) false
    else if (pile.length == 1) false
    else
      pile.last.rank == pile.init.last.rank
  }

  def pickUpPile(pile: ListBuffer[Card], playerHand: List[(Int, mutable.Buffer[Card])]): Unit = {
    playerHand.head._2.addAll(pile)
    pile.clear()
  }

  def checkWinner(playerHands: List[(Int, mutable.Buffer[Card])], deckSize: Int): Int = {
    val winner = playerHands.filter {
      case (_, h) => h.length == deckSize
    }
    if (winner.length == 1) winner.head._1
    else
      0
  }
}
