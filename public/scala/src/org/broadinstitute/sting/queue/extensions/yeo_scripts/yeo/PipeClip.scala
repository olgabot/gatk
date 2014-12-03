package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class PipeClip extends CommandLineFunction {

 @Input(doc="input bam file", shortName = "inBam", fullName = "input_bam_file", required = true)
 var inBam: File = _

 @Output(doc="bed file of peaks to output", shortName = "outBed", fullName = "outBed", required = true)
 var outBed: File = _ 

 @Argument(doc="species string", shortName = "species", fullName = "species", required = true)
 var species: String = _
 
  override def shortDescription = "MiClip"
  this.nCoresRequest = Option(1)
  def commandLine = "python /home/yeo-lab/software/PIPE-CLIP/pipeclip.py -i " + inBam + " -o " + outBed + " -l 1 -m 10 -c 3 -r 0 -M .05 -C .05 -s " + species
}
