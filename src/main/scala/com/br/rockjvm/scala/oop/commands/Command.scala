package com.br.rockjvm.scala.oop.commands

import com.br.rockjvm.scala.oop.filesystem.State

trait Command {
  /**
    * Commands have the power to transform a state
    */
  def apply(state: State): State

}

object Command{
  def from (input: String):Command =
    new UnknownCommand
}
