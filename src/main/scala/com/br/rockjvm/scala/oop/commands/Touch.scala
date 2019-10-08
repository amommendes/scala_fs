package com.br.rockjvm.scala.oop.commands

import com.br.rockjvm.scala.oop.files.{DirEntry, File}
import com.br.rockjvm.scala.oop.filesystem.State

class Touch(name: String) extends CreateEntry (name) {
  override def CreateSpecificEntry(state: State): DirEntry =
    File.empty(state.wd.path, name)


}
