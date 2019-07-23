package com.br.rockjvm.scala.oop.filesystem

import java.util.Scanner

import com.br.rockjvm.scala.oop.commands.Command
import com.br.rockjvm.scala.oop.files.Directory

object Filesystem extends App{

  val root = Directory.ROOT
  var state = State(root, root)
  val scanner = new Scanner(System.in)

  while (true){
    state.show
    val input = scanner.nextLine()
    state = Command.from(input).apply(state)
  }
}
