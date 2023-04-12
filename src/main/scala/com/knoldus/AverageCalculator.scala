package com.knoldus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}
import scala.io.Source
import scala.util.{Failure, Success, Try}

class AverageCalculator {
  /* This method is used to fetch the data from csv file and split it in form of Future[List[Map[String, String]]] */
  private def parseCsv(pathOfFile: String): Future[List[Map[String, String]]] = {
    Future {
      Try {
        val file = Source.fromFile(pathOfFile).getLines().toList
        val studentKeys = file.head.split(",").toList
        val studentDetail = file.tail.map { line =>
          val listOfMarks = line.split(",").toList
          studentKeys.zip(listOfMarks).toMap
        }
        studentDetail
      }
      match {
        case Success(result) => result
        case Failure(ex) => throw new Exception(s"Failed to parse CSV file '$pathOfFile': ${ex.getMessage}")
      }
    }
  }

  /* It takes the input as return type of previous method (parseCsv)
       It is used to calculate the average of every student */
  private def calculateStudentAverage(studentDetails: Future[List[Map[String, String]]]): Future[List[(String, Double)]] = {
    Future {
      Try {
        val studentAverage = studentDetails.map { studentDetail =>
          studentDetail.map { mapValue =>
            val id = mapValue("StudentID")
            val grades = List(mapValue("English"), mapValue("Chemistry"), mapValue("Physics"), mapValue("Maths"))
            val gradeInDouble = grades.map { grade =>
              grade.toDouble
            }
            val average = gradeInDouble.sum / gradeInDouble.size
            (id, average)
          }
        }
        Await.result(studentAverage, 5.seconds)
      }
      match {
        case Success(result) => result
        case Failure(ex) => throw new Exception(s"${ex.getMessage}")
      }
    }
  }

  /* It takes input type of calculateStudentAverage return type
       Inside this method it is used to find average of class */
  private def calculateClassAverage(studentsAverage: Future[List[(String, Double)]]): Future[Double] = {
    Try {
      val classAverage = studentsAverage.map { studentAverage =>
        val fetchingStudentAverage = studentAverage.map { average =>
          average._2
        }
        val result = fetchingStudentAverage.sum / fetchingStudentAverage.size
        result
      }
      classAverage
    } match {
      case Success(result) => result
      case Failure(ex) => throw new Exception(s"${ex.getMessage}")
    }
  }

  /* This method takes input of path file and it call all the above method to calculate the grade */
  def calculateGrades(path: String): Future[Double] = {
    val gradeCalculator = for {
      data <- parseCsv(path)
      studentAverage <- calculateStudentAverage(Future(data))
      classAverage <- calculateClassAverage(Future(studentAverage))
    } yield classAverage
    Await.result(gradeCalculator, 5.seconds)
    gradeCalculator.recoverWith { case exception =>
      Future.failed(exception)
  }
  }
}
