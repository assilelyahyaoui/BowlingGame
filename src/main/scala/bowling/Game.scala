package bowling

case class Game(private val _scores : List[Option[Int]], private val _round : Int, private val _bonus : Boolean, private val _frames : List[Frame]) {
// frames are the 2 (or 3 for 10th) scores
// score represent the addition of frame scores + eventual bonuses
  def scores : List[Option[Int]] = this._scores
  def round : Int = this._round
  def bonus : Boolean = this._bonus
  def frames : List[Frame] = this._frames

  }



object Game {

  def GameEmpty : Game = {
    new Game(List[Option[Int]](Option(0 )), 1, false, List[Frame]())
  }
  def getNewScore(game: Game): Option[Int] = {
    // gives the score of the game
    val lastFrame = game.frames.head
    val lastFrameScore = lastFrame.chanceScore(0) + lastFrame.chanceScore(1)

    if (lastFrameScore == 10) {
      None
    } else {
      Some(lastFrameScore)
    }
  }

  def getNewFrame(roundNumber : Int): Frame = {
    // returns a new Frame
    Frame.roll(roundNumber)

  }

  def returnNewGameAfterRound(game : Game,  getNewFrame: => Frame ): Game = {
      // returns a new game with a new rolled frame

    val gameWithNewFrame = game.copy(_frames = getNewFrame :: game.frames  )
    //val newScore = getNewScore(gameWithNewFrame)
    val newScore = Frame.getOptionalScore(gameWithNewFrame.frames.head)
   // val isEmpty = newScore.getOrElse()

    if (newScore nonEmpty){
      // no strike nor spare
      if (! game.bonus){
        //this frame does not count as bonus for previous frame
        val ns = (newScore.get + gameWithNewFrame.scores.head.get)
        val newListScore = (Option(ns) :: gameWithNewFrame.scores)
        //gameWithNewFrame.copy( _scores =  newListScore, _round = gameWithNewFrame.round+1 )
        val lzl = new Game(newListScore,gameWithNewFrame.round+1 , false, gameWithNewFrame._frames )
        lzl
      }
      else {
        val numBonus = returnNumberBonus(gameWithNewFrame.frames.tail , 0)
        val scoreRound0 = gameWithNewFrame.scores.head.get + 10*numBonus + newScore.get
        val ns = (scoreRound0 + newScore.get)
        gameWithNewFrame.copy( _scores = List(Option(ns), Option(scoreRound0)) :::  gameWithNewFrame.scores, _bonus = false , _round = gameWithNewFrame.round+1 )
      }
    }
    else{
      //strike or spare
      if (! game.bonus){
        //this frame does not count as bonus for previous frame
        gameWithNewFrame.copy( _bonus = true ,_round = gameWithNewFrame.round+1 )

      }
      else{
        // counts as bonus
        val scoreRound0 = gameWithNewFrame.scores.head.get + 30
        gameWithNewFrame.copy( _scores= Option(scoreRound0) :: gameWithNewFrame.scores , _bonus = true ,_round = gameWithNewFrame.round+1 )

      }

    }

  }


  def returnNumberBonus(frames : List[Frame], numBonus : Int): Int ={

    if (frames.isEmpty || Frame.getScore(frames.head) !=  10   ){
      numBonus
    }
    else {
      returnNumberBonus(frames.tail , numBonus+1)
    }
  }
}


