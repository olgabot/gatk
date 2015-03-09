package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline.Output
import java.io.File
import org.broadinstitute.sting.commandline._
import org.broadinstitute.sting.queue.function.CommandLineFunction

class CountRepetitiveRegions extends CommandLineFunction {
  override def shortDescription = "CountRepetitiveRegions"

  @Input(doc="input bam file", shortName = "inBam", fullName = "input_bam_file", required = true)
  var inBam: File = _

  @Output(doc="metrics file for what reads got removed", shortName = "outFile", fullName = "out_file", required = true)
  var outFile: File = _

  this.wallTime = Option((1 * 60 * 60).toLong)
  def commandLine = "samtools view %s | count_aligned_from_sam.py > %s".format(inBam, outFile)

}
