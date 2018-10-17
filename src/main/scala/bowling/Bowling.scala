package bowling

object Bowling extends App{

  def mainLoop(): Unit = {

    val game =  Game.GameEmpty
    val frame = new Frame(Array(1,2))


    gameLoop(game, 10)




  }
  mainLoop()

  def gameLoop(game : Game, numRounds : Int): Game ={
    if (numRounds == 0){
      println("Your final score is " + game.scores.head + "or " + game.scores.last)
      return game
    }
    else {

      println("The scores are " + game.scores)
      println(game)
      println("Round number " + (game.round))

      gameLoop( Game.returnNewGameAfterRound(game, Game.getNewFrame(game.round)) , numRounds - 1 )

    }

  }

}
