package com.knoldus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

/* This is Driver class to check the program is running or not */
object Driver extends App {
  private val averageCalculator = new AverageCalculator
  private val filePath = "/home/knoldus/Desktop/KUP/Asignment/SCALA SESSION/session-5/assignment-1/src/main/scala/com/knoldus/StudentsDetails.csv"
  val result = Try(averageCalculator.calculateGrades(filePath))
  result match {
    case Success(average) => average.onComplete {
      case Success(value) => println(s"Class average: $value")
      case Failure(exception) => println(exception)
    }
    case Failure(ex) => println(s"Failed to calculate grades: ${ex.getMessage}")
  }
}
