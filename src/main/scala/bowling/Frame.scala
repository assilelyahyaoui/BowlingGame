package bowling

import scala.Some
import scala.io.StdIn.readLine
import scala.util.Random

case class Frame(val chanceScore : Array[Int]) {


  def this() {
    this(Array[Int]())
  }
}
  object Frame {

  def roll( roundNumber : Int): Frame ={

    //val randInt = new Random()
    println("enter score of first roll")
    val score1 =  readLine.toInt


    println("score1 " + score1)

    roundNumber match {
      case 10 => {
        println("enter score of second roll")
        val score2 = readLine.toInt

        if (score1 + score2 >= 10){
          println("enter score of third roll")
          val score3 = readLine.toInt
          new Frame(Array(score1, score2, score3))
        }
        else{
          new Frame(Array(score1, score2))
        }
      }

      case _ =>{


        score1 match {
          case 10 => {
            new Frame(Array(10, 0))
          }
          case _ => {
            println("enter score of second roll")
            val score2 = readLine.toInt
            //0+randInt.nextInt(10 - score1)
            println("score2 " + score2)
            new Frame(Array(score1, score2))
          }

        }
      }
    }
  }

    def getOptionalScore(frame: Frame): Option[Int] = {
      // gives the score of the game

      val FrameScore = frame.chanceScore.reduce((a1, a2) => a1 +a2 )

      if (FrameScore == 10) {
        None
      } else {
        Some(FrameScore)
      }
    }

    def getScore(frame : Frame) : Int = {
      frame.chanceScore.reduce((a1, a2) => a1 +a2 )
    }


  }



