package snap

case class Card(rank: String, suit: String) {

  override def toString: String = {
    rank + " " + "of" + " " + suit
  }

}
