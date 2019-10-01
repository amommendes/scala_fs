package com.br.rockjvm.scala.oop.commands

import com.br.rockjvm.scala.oop.filesystem.State

trait Command {
  /**
    * Commands have the power to transform a state
    */
  def apply(state: State): State

}

<<<<<<< Updated upstream
object Command{
  def from (input: String):Command =
    new UnknownCommand
=======
object  Command {
  val MKDIR = "mkdir"
  val LS = "ls"
  val PWD = "pwd"
  def emptyCommand:Command = (state: State) => state

  def incompleteCommand(name:String) : Command = new Command {
    override def apply(state:State): State =
      state.setMessage("The command "+ name + " is incomplete dud!")

  }

  def from(input: String): Command = {
    val tokens: Array[String] = input.split("\\s");
    if (input.isEmpty || tokens.isEmpty) {
      emptyCommand
    } else if (MKDIR.equals(tokens(0))) {
        if (tokens.length < 2) incompleteCommand(MKDIR)
        else new MkDir (tokens(1))

    } else if(LS.equals(tokens(0))) {
        new Ls

    } else if(PWD.equals(tokens(0))) {
      new Pwd
    }else {
        new UnknownCommand
    }
  }
>>>>>>> Stashed changes
}
