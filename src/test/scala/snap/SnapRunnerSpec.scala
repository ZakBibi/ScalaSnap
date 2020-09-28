package snap

import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AnyFlatSpec

import scala.collection.mutable.ArrayBuffer

class SnapRunnerSpec extends AnyFlatSpec {

  private val game = new Snap(2)

  it should "pick up pile if cards match" in {
    val playerHands = List(
      (1, ArrayBuffer[Card](Card("Ace", "Clubs"), Card("10", "Diamonds"))),
      (2, ArrayBuffer[Card](Card("Ace", "Spades"), Card("2", "Hearts")))
    )
    SnapRunner.playSnap(game, playerHands)
    assert(playerHands == List(
      (1,ArrayBuffer(Card("10", "Diamonds"))),
      (2,ArrayBuffer(Card("2", "Hearts"), Card("Ace", "Clubs"), Card("Ace", "Spades")))
    ))
  }

  it should "play a round if cards do not match" in {
    val playerHands = List(
      (1, ArrayBuffer[Card](Card("3", "Clubs"), Card("10", "Diamonds"))),
      (2, ArrayBuffer[Card](Card("Ace", "Spades"), Card("2", "Hearts")))
    )
    SnapRunner.playSnap(game, playerHands)
    assert(playerHands == List(
      (1,ArrayBuffer(Card("10", "Diamonds"))),
      (2,ArrayBuffer(Card("2", "Hearts")))
    ))
  }

  it should "play snap correctly with 3 players" in {
    val threePlayerGame = new Snap(3)
    val playerHands = List(
      (1, ArrayBuffer[Card](Card("3", "Clubs"), Card("10", "Diamonds"))),
      (2, ArrayBuffer[Card](Card("Ace", "Spades"), Card("2", "Hearts"))),
      (3, ArrayBuffer[Card](Card("Ace", "Diamonds"), Card("King", "Clubs")))
    )
    SnapRunner.playSnap(threePlayerGame, playerHands)
    assert(playerHands == List(
      (1, ArrayBuffer(Card("10", "Diamonds"))),
      (2, ArrayBuffer(Card("2", "Hearts"))),
      (3, ArrayBuffer(Card("King", "Clubs"), Card("3", "Clubs"), Card("Ace", "Spades"), Card("Ace", "Diamonds")))
    ))
  }

  it should "pick up pile when a hand is empty" in {
    val playerHands = List(
      (1, ArrayBuffer[Card](Card("3", "Clubs"), Card("10", "Diamonds"))),
      (2, ArrayBuffer[Card](Card("Ace", "Spades")))
    )
    SnapRunner.playSnap(game, playerHands)
    SnapRunner.checkEmptyPlayerHands(game, playerHands)
    assert(playerHands == List(
      (1, ArrayBuffer(Card("10", "Diamonds"))),
      (2, ArrayBuffer(Card("3", "Clubs"), Card("Ace", "Spades")))
    ))
  }

  it should "pick up pile when a hand is empty in a 3 player game" in {
    val threePlayerGame = new Snap(3)
    val playerHands = List(
      (1, ArrayBuffer[Card](Card("3", "Clubs"), Card("10", "Diamonds"))),
      (2, ArrayBuffer[Card](Card("Ace", "Spades"))),
      (3, ArrayBuffer[Card](Card("Jack", "Hearts"), Card("7", "Diamonds")))
    )
    SnapRunner.playSnap(threePlayerGame, playerHands)
    SnapRunner.checkEmptyPlayerHands(threePlayerGame, playerHands)
    assert(playerHands == List(
      (1, ArrayBuffer(Card("10", "Diamonds"))),
      (2, ArrayBuffer(Card("3", "Clubs"), Card("Ace", "Spades"), Card("Jack", "Hearts"))),
      (3, ArrayBuffer(Card("7", "Diamonds")))
    ))
  }


}
