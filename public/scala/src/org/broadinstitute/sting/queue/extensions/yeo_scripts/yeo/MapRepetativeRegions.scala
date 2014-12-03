package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline.Output
import java.io.File
import org.broadinstitute.sting.commandline._
import org.broadinstitute.sting.queue.function.CommandLineFunction

class MapRepetitiveRegions extends CommandLineFunction {
  override def shortDescription = "FilterRepetitiveRegions"

  @Input(doc="input fastq file", shortName = "inFastq", fullName = "input_fastq_file", required = true) 
  var inFastq: File = _
  
  @Output(doc="Mapped file for reads that got removed", shortName = "outRep", fullName = "out_rep", required = true) 
  var outRep: File = _
 
  @Output(doc="fastq file with repetive elements removed", shortName = "outNoRep", fullName = "out_no_rep", required = true) 
  var outNoRep: File = _
  this.wallTime = Option((4 * 60 * 60).toLong)
  this.nCoresRequest = Option(16) 
  def commandLine = "bowtie -S -q -p 16 -e 100 -l 20 --un %s all_ref %s | samtools view -F 4 -Sb - > %s".format(outNoRep, inFastq, outRep)

}
