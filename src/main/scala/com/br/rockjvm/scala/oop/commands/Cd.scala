package com.br.rockjvm.scala.oop.commands
import com.br.rockjvm.scala.oop.files.{DirEntry, Directory}
import com.br.rockjvm.scala.oop.filesystem.State

import scala.annotation.tailrec

class Cd (directory: String) extends Command {

  override def apply(state: State): State = {
    /*
      cd /a/b/c
      cd b/c = relative path to the current directory
      cd .././../a
     */

    //1. Find root

    val root = state.root
    val wd = state.wd

    //2. Find absolute path of the directory I want cd to

    val absolute_path: String =
      if (directory.startsWith(Directory.SEPARATOR)) directory
      else if (wd.isRoot) wd.path + directory
      else wd.path + Directory.SEPARATOR + directory

    //3. Find the directory to cd to, given the path

    lazy val destinationDirectory = doFindEntry(root, absolute_path)

    //4. Change the state given new directory
    if (destinationDirectory == null || !destinationDirectory.isDirectory)
      state.setMessage(s"Hey, this directory ${directory}  is not found")
    else State(root, destinationDirectory.asDirectory)

    def doFindEntry(root:Directory, path: String): DirEntry = {
      @tailrec
      def findEntryHelper(currentDirectory:Directory, path:List[String]):DirEntry = {
        if (path.isEmpty || path.head.isEmpty) currentDirectory
        else if (path.tail.isEmpty)  currentDirectory.findEntry(path.head)
        else {
          val nextDir = currentDirectory.findEntry(path.head)
          if (nextDir == null ) null
          else findEntryHelper(nextDir.asDirectory, path.tail)
        }
      }

      //1. tokens
      val tokens:List[String] = path.substring(1).split(Directory.SEPARATOR).toList

      //2. Navigate to the correct entry
      findEntryHelper(root, tokens)
    }
    state
  }


}
