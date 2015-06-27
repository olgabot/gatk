package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class Clipper extends CommandLineFunction {

  @Input(doc="input bam file", shortName = "inBam", fullName = "input_bam_file", required = true)
  var inBam: File = _
  
  @Argument(doc="species (hg19, mm9.. ect)", shortName = "species", fullName = "species", required = true)
  var species: String = _

  @Argument(doc="specify if data is on the premRNA", shortName = "premRNA", fullName = "premRNA", required = false)
  var premRNA: Boolean = false

  @Output(doc="bed file of peaks to output", shortName = "outBed", fullName = "outBed", required = true)
  var outBed: File = _

  @Argument(doc="specify if you want to use superlocal", shortName = "superlocal", fullName = "superlocal", required = false)
  var superlocal: Boolean = true

  @Argument(doc="reverse strand", shortName = "reverse_strand", fullName = "reverse_strand", required = false)
  var reverse_strand: Boolean = false

  override def shortDescription = "clipper"
  this.nCoresRequest = Option(8)
  this.wallTime = Option((4 * 60 * 60).toLong)
  def commandLine = "clipper " +
    required("-b", inBam) +
    required("-s", species) +
    required("-o", outBed) +
    conditional(premRNA, "--premRNA") +
    conditional(reverse_strand, "--reverse_strand") +
    required("--bonferroni") +
    conditional(superlocal, "--superlocal") +
    required("--threshold-method", "binomial") +
    required("--save-pickle")
}
