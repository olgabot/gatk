package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class FastqSort(@Input inFastq_1: File, @Input inFastq_2: File, @Output outFastq_1: File, @Output outFastq_2: File) extends CommandLineFunction {
  override def shortDescription = "fastq-sort"
  this.wallTime = Option((3 * 60 * 60).toLong)
  def commandLine = "fastq-sort " + required("--id") + required(inFastq_1) + " > " + required(outFastq_1) + " && fastq-sort " + required("--id") + required(inFastq_2) + " > " + required(outFastq_2)
  this.isIntermediate = true
}
