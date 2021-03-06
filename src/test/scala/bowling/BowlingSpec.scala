package bowling

import org.scalatest.{FunSpec, Matchers}

class BowlingSpec extends FunSpec with Matchers {


  describe("This round ") {
    val game = Game.GameEmpty
    val frameScoreEq3 = new Frame(Array(1,2))
    val frameScoreEq10 = new Frame(Array(5,5))

    val game1 = Game.returnNewGameAfterRound(game, frameScoreEq3)
    val game2 = Game.returnNewGameAfterRound(game, frameScoreEq10)
    val game3 = Game.returnNewGameAfterRound(game1 ,frameScoreEq3)

    // Bonuses
    it ("should not have a bonus "){
      (game1.bonus) should be (false)
    }

    it ("should have a bonus "){
      (game2.bonus) should be (true)
    }

    // rounds
    it ("should have a number of rounds equal to 2 "){
      (game1.round == 2) should be (true)
    }

    it ("should not have a number of rounds equal to 2 "){
      (game3.round == 2) should be (false)
    }
    it ("should have a number of rounds equal to 1"){
      (game1.round == 2) should be (true)
    }

    // score

    it("should  give a score of 0"){
      (game2.scores.head.get) should be(0 )
    }

    it("should not give a score of 3"){
      (game2.scores.head == 3) should be(false )
    }

  }

  describe("This Bonus  ") {
    val frame1 = new Frame(Array(10))
    val frame3 = new Frame(Array(10))

    val frame2 = new Frame(Array(1,2))
    val emptyScores = List(Some(0))

    val game =  new Game(emptyScores ,   1, false, List(frame1,frame3 ,  frame2) )
    val game2 =  new Game(emptyScores ,   1, false, List(frame1) )


    it ("shouldn't be spread on 3 rounds  ") {
      (Game.returnNumberBonus(game.frames, 0) == 3 ) should be(false)
    }
    it ("should be spread on 2 rounds  ") {
      (Game.returnNumberBonus(game.frames, 0) == 2 ) should be(true)
    }
    it(" should be spread on one round"){
      (Game.returnNumberBonus(game2.frames, 0)  ) should be(1)
    }
  }


  describe("the last score should be of "){
    val frame1 = new Frame(Array(10))
    val frame3 = new Frame(Array(3,6))

    val frame2 = new Frame(Array(1,1))
    val emptyScores = List(Some(0))

    val game =  new Game(emptyScores ,   1, false, List(frame1,frame3 ,  frame2) )
    val gameInput10 = Game.returnNewGameAfterRound(game, frame1)

    // Ten strikes in a row


    it ("should be 3  ") {
      (Game.returnNewGameAfterRound(game, frame2).scores.head.get ) should be(2)
    }



    it ("should be of zero after one strike ") {
      (gameInput10.scores.head.get ) should be(0)
    }
/*
    it ("should be of 300 after onlt strikes") {
      () should be(0)
    }
*/

  }

  describe("the frame individual score should be  "){
    val frame1 = new Frame(Array(10))
    val frame3 = new Frame(Array(3,6))

    it ("equal to 10  ") {
      (Frame.getScore(frame1)) should be(10)
    }

    it ("equal to 9") {
      (Frame.getScore(frame3)) should be(9)
    }

  }




}
