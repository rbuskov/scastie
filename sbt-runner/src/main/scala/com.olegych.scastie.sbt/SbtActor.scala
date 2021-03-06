package com.olegych.scastie
package sbt

import api._

import akka.actor.{ActorSystem, Props, Actor}

import scala.concurrent.duration._

class SbtActor(system: ActorSystem,
               runTimeout: FiniteDuration, 
               production: Boolean) extends Actor {

  val formatActor = 
    system.actorOf(Props(new FormatActor()), name = "FormatActor")

  val sbtRunner =
    system.actorOf(
      Props(new SbtRunner(runTimeout,production)),
      name = "SbtRunner"
    )

  def receive = {
    case SbtPing => 
      sender ! SbtPong

    case completion: CompletionRequest =>
      sbtRunner.tell(completion, sender)

    case format: FormatRequest =>
      formatActor.tell(format, sender)

    case task: SbtTask =>
      sbtRunner.tell(task, sender)
  }
}