package com.br.rockjvm.scala.oop.commands
import com.br.rockjvm.scala.oop.filesystem.State

class UnknownCommand extends Command {

  override def apply(state: State): State = state.setMessage("Command not found!")

}
