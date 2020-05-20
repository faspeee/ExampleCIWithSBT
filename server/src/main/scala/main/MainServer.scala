package main
import akka.actor.typed.ActorSystem
import main.Server.StartServer

object MainServer extends App{
  ActorSystem[StartServer](Server(), "AkkaHttpServer")
}
