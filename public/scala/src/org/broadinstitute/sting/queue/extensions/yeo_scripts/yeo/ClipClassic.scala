package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class ClipClassic extends CommandLineFunction {

 @Input(doc="input bam file", shortName = "inBam", fullName = "input_bam_file", required = true)
 var inBam: File = _

 @Output(doc="output bed file", shortName = "out_file", fullName = "output_bed_file", required = true)
 var out_file: File = _

 @Argument(doc="species (hg19, mm9.. ect)", shortName = "species", fullName = "species", required = true)
 var species: String = _
 this.wallTime = Option((4 * 60 * 60).toLong)
 
  override def shortDescription = "ClipClassic"
  def commandLine = "run_kasey.py " +
    required("-b", inBam) +
    required("-s", species) +
    required("-o", out_file)
}
