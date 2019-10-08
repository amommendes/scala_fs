package com.br.rockjvm.scala.oop.commands
import com.br.rockjvm.scala.oop.filesystem.State

class Pwd  extends Command {
  override def apply(state: State): State =
    state.setMessage(state.wd.path)

}
