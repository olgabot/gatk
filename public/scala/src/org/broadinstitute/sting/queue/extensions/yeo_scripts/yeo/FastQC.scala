package org.broadinstitute.sting.queue.extensions.yeo

import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction
import org.broadinstitute.sting.commandline._

class FastqQC(@Input inFastq: File, @Argument outDir: String= null) extends CommandLineFunction {

  this.wallTime = Option((2 * 60 * 60).toLong)
  var out = outDir
  if (out == null) {
    out = "`cwd`"
  }
  override def shortDescription = "FastQC"
  
  def commandLine = "fastqc" +
  required(inFastq) + 
  required("-o", outDir)
  
  
  }
