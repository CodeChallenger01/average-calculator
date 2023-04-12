package com.knoldus

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.language.postfixOps
import scala.util.{Failure, Success, Try}

class AverageCalculatorTest extends AnyFlatSpec with Matchers {
  private val averageCalculator = new AverageCalculator

  "calculateGrades" should "returns error if empty path is pass as a parameter " in {
    val filePath = ""
    val result = Try(averageCalculator.calculateGrades(filePath))
    val actualOutput = result match {
      case Success(average) => s"Class average: $average"
      case Failure(ex) => s"Failed to calculate grades: ${ex.getMessage}"
    }
    val expectedOutput = "Failed to calculate grades: Failed to parse CSV file '':  (No such file or directory)"
    expectedOutput shouldBe actualOutput
  }

  "calculateGrades" should "returns error if path is not valid" in {
    val filePath = "/home/knoldus/"
    val result = Try(averageCalculator.calculateGrades(filePath))
    val actualOutput = result match {
      case Success(average) => s"Class average: $average"
      case Failure(ex) => s"Failed to calculate grades: ${ex.getMessage}"
    }
    val expectedOutput = "Failed to calculate grades: Failed to parse CSV file '/home/knoldus/': /home/knoldus (Is a directory)"
    expectedOutput shouldBe actualOutput
  }

  "calculateGrades" should "returns Future average " in {
    val filePath = "/home/knoldus/Desktop/KUP/Assignment/SCALA SESSION/session-5/assignment-1/src/main/scala/com/knoldus/StudentsDetails.csv"
    val result = Try(averageCalculator.calculateGrades(filePath))
    val actualOutput = result match {
      case Success(average) => s"Class average: $average"
      case Failure(ex) => s"Failed to calculate grades: ${ex.getMessage}"
    }
    val expectedOutput = "Class average: Future(Success(79.5))"
    expectedOutput shouldBe actualOutput
  }

  "calculateGrades" should "returns NaN if csv doesn't contain list of number " in {
    val filePath = "/home/knoldus/Desktop/KUP/Assignment/SCALA SESSION/session-5/assignment-1/src/main/scala/com/knoldus/FileToCheck.csv"
    val result = Try(averageCalculator.calculateGrades(filePath))
    val actualOutput = result match {
      case Success(average) => s"Class average: $average"
      case Failure(ex) => s"Failed to calculate grades: ${ex.getMessage}"
    }
    val expectedOutput = "Class average: Future(Success(NaN))"
    actualOutput shouldBe expectedOutput
  }

  "calculateGrades" should "returns not Nan if it contains student details" in {
    val filePath = "/home/knoldus/Desktop/KUP/Assignment/SCALA SESSION/session-5/assignment-1/src/main/scala/com/knoldus/StudentsDetails.csv"
    val result = Try(averageCalculator.calculateGrades(filePath))
    val actualOutput = result match {
      case Success(average) => s"Class average: $average"
      case Failure(ex) => s"Failed to calculate grades: ${ex.getMessage}"
    }
    val expectedOutput = "Class average: Future(Success(NaN))"
    assert(actualOutput != expectedOutput)
  }

  "calculateGrades" should "returns average not equal to other value " in {
    val filePath = "/home/knoldus/Desktop/KUP/Assignment/SCALA SESSION/session-5/assignment-1/src/main/scala/com/knoldus/StudentsDetails.csv"
    val result = Try(averageCalculator.calculateGrades(filePath))
    val actualOutput = result match {
      case Success(average) => s"${average}"
      case Failure(ex) => s"Failed to calculate grades: ${ex.getMessage}"
    }
    val expectedOutput = "Future(Success(99.5))"
    assert(expectedOutput != actualOutput)
  }
}
