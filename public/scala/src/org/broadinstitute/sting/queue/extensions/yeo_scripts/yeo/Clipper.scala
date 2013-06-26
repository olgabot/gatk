package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class Clipper(@Input inBam: File, @Argument species: String, @Argument premRNA: Boolean, @Output outBed: File) extends CommandLineFunction {
  override def shortDescription = "clipper"
  this.nCoresRequest = Option(16)
  
  def commandLine = "clipper " +
    required("-b", inBam) +
    required("-s", species) +
    required("-o", outBed) +
    conditional(premRNA, "--premRNA") +
    required("--bonferroni") +
    required("--superlocal") +
    required("--threshold-method binomial"
}