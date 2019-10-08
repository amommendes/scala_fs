package com.br.rockjvm.scala.oop.files

import com.br.rockjvm.scala.oop.filesystem.FileSystemException

class File (override val parentPath: String, override val name: String, contents: String) extends DirEntry(parentPath, name) {

  def asDirectory : Directory =
    throw new FileSystemException("File is not a directory man. Take easy!")
  def asFile = this
  def getType : String = "File"
  override def isDirectory: Boolean = false
  override def isFile: Boolean = true

}

object File {
  def empty(parentPath: String, name:String): File ={
    new File(parentPath,name,"")
  }
}
