package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class MiClip extends CommandLineFunction {

 @Input(doc="input bam file", shortName = "inBam", fullName = "input_bam_file", required = true)
 var inBam: File = _

 @Output(doc="bed file of peaks to output", shortName = "outBed", fullName = "outBed", required = true)
 var outBed: File = _ 

 @Argument(doc="genome location", shortName = "genome", fullName = "genome", required = true)
 var genome: String = _
 
  override def shortDescription = "MiClip"
  this.nCoresRequest = Option(1)
  def commandLine = "samtools fillmd " + inBam + " " + genome + " > " + inBam + ".sam && " + 
      		    " Rscript ~/gscripts/gscripts/clipseq/run_miclip.R " + required(inBam + ".sam") + required(outBed) + 
		    " && rm " + inBam + ".sam"
}
