package routes.exceptions

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server._
import Directives._

object Exceptions {
  implicit def myExceptionHandler: ExceptionHandler =
    ExceptionHandler {
      case _: ArithmeticException =>
        extractUri { uri =>
          println(s"Request to $uri could not be handled normally")
          complete(HttpResponse(InternalServerError, entity = "Bad numbers, bad result!!!"))
        }
      case _: IllegalArgumentException =>
        extractUri { uri =>
          println(s"Request to $uri could not be handled normally")
          complete(HttpResponse(BadGateway, entity = "Bad numbers, bad result!!!"))
        }
      case _: IllegalAccessError =>
        extractUri { uri =>
          println(s"Request to $uri could not be handled normally")
          complete(HttpResponse(BadGateway, entity = "Bad numbers, bad result!!!"))
        }
      case _: Exception =>
        extractUri { uri =>
          println(s"Request to $uri could not be handled normally")
          complete(HttpResponse(BadRequest, entity = "Bad numbers, bad result!!!"))
        }
    }
}
