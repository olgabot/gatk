package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class Miso(@Input inBam: File, @Input indexFile: File, @Argument species: String, @Argument pairedEnd: Boolean, @Output output: File) extends CommandLineFunction {
  override def shortDescription = "miso"
  this.nCoresRequest(16)
  var sh_file = swapExt(output, "", ".sh")
  def commandLine = "submit_miso_pipeline.py " +
    required("--bam", inBam) +
    required("--sample-id", inBam.getName.split("""\.""")(0)) +
    required("--genome", species) +
    required("--do-not-submit") +
    // required("--single-end") +
    // conditional( pairedEnd, "--paired-end") +
    // conditional(!pairedEnd, "--single-end") +
    required("--output-sh", sh_file) + " && " +
    required("sh ", sh_file) + " && " +
    required("touch ", output)
}
