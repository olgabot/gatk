package org.broadinstitute.sting.queue.extensions.yeo

import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction
import org.broadinstitute.sting.commandline.Input

class FastQC(@Input inFastq: File) extends CommandLineFunction {
 this.wallTime = Option((2 * 60 * 60).toLong)

  override def shortDescription = "FastQC"
  def commandLine = "fastqc %s -o `cwd`".format(inFastq)

}
