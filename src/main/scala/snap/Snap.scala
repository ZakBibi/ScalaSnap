package snap
import scala.collection.mutable.ListBuffer

class Snap(private val numberOfPlayers: Int) {

  val deck = new Deck
  var pile: ListBuffer[Card] = ListBuffer[Card]()

  def playCard(playerHand: ListBuffer[Card]): Unit = {
    pile += playerHand.head
    playerHand -= playerHand.head
  }

  def matchCard(pile: ListBuffer[Card]): Boolean = {
    if (pile.isEmpty) false
    else if (pile.length == 1) false
    else
      pile.last.rank == pile.init.last.rank
  }

  def pickUpPile(pile: ListBuffer[Card], playerHand: ListBuffer[Card]): Unit = {
    playerHand.addAll(pile)
    pile.clear()
  }

}
