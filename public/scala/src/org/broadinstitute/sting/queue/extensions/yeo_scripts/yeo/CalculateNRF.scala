package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class CalculateNRF(@Input inBam: File, @Output outNRF: File, @Argument genomeSize: String) extends CommandLineFunction {
 this.wallTime = Option((2 * 60 * 60).toLong)

  override def shortDescription = "CalculateNRF"
  def commandLine = "calculate_NRF.py " + required("--bam", inBam) + required("--genome", genomeSize) + " > " + outNRF

}
