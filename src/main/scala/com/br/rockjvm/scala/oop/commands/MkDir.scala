package com.br.rockjvm.scala.oop.commands

import com.br.rockjvm.scala.oop.files.{DirEntry, Directory}
import com.br.rockjvm.scala.oop.filesystem.State

class MkDir(name:String) extends CreateEntry(name) {
    override def CreateSpecificEntry(state: State): DirEntry =
        Directory.empty(state.wd.path, name)
}
