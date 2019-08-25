package com.br.rockjvm.scala.oop.commands

import com.br.rockjvm.scala.oop.files.{DirEntry, Directory}
import com.br.rockjvm.scala.oop.filesystem.State

class MkDir(name:String) extends Command {

    // The apply method carries about overall logic.
    override def apply(state: State ) : State = {
        val wd = state.wd
        if (wd.hasEntry(name)){
            state.setMessage(s"Entry ${name} already existis!")
        } else if (checkIllegal(name)) {
            //TODO
            //mkdir -p something/something_else
            state.setMessage(s"$name must not contain separator yet! To be implemented")
        } else if (checkIllegal(name)) {
            state.setMessage(s"$name is a illegal entry name")
        } else {
            createDir(state,name)
        }
    }

    def checkIllegal(name:String) : Boolean  = {
        name.contains(".") || name.contains(Directory.SEPARATOR)
    }
    def updateStructure(currentDirectory: Directory, path : List[String], newDir: DirEntry) : Directory = {
        if (path.isEmpty) currentDirectory.addEntry(newDir)
        else {
            val oldEntry = currentDirectory.findEntry(path.head).asDirectory
            currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newDir))
        }

    }

    def createDir(state:State, name:String): State = {
        val wd = state.wd

        //1. All the directory in the full path
        val allDirsInPath  = wd.getAllFolderInPath

        //2. Create new directory entry and working directory

        val newDir = Directory.empty(wd.parentPath, name)

        //3. Need to update the whole directory structure
        // (the directory structure is IMMUTABLE)
        val newRoot = updateStructure(state.root,allDirsInPath, newDir )

        //4. find new working directory instance given wd full path, in the NEW Directory structure
        val newWd = newRoot.findDescendant(allDirsInPath)

        State(newRoot, newWd)

    }
}
