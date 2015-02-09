package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline.Output
import java.io.File
import org.broadinstitute.sting.commandline._
import org.broadinstitute.sting.queue.function.CommandLineFunction

class MapRepetitiveRegions2 extends CommandLineFunction {
  override def shortDescription = "FilterRepetitiveRegions"

  @Input(doc="input fastq file", shortName = "inFastq", fullName = "input_fastq_file", 
    required = true) 
  var inFastq: File = _

  @Input(doc="input fastq file paired end read", shortName = "inFastqPair", 
    fullName = "input_fastq_pair_file", required = true) 
  var inFastqPair: File = _

  @Input(doc="Dummy input so this gets run after TrimGalore", required=true)
  var fakeVariable: File = _

  @Output(doc="Mapped file for reads that got removed", shortName = "outRepetitive", 
    fullName = "out_repetitive", required = true) 
  var outRepetitive: File = _
 
  @Output(doc="fastq file with repetive elements removed", 
    shortName = "outNoRepetitive", fullName = "out_no_repetitive", required = true) 
  var outNoRepetitive: File = _
 
  @Output(doc="fastq file with repetive elements removed", 
    shortName = "outNoRepetitivePair", fullName = "out_no_repetitive_pair", required = false) 
  var outNoRepetitivePair: File = _

  var paired = inFastqPair != null
  println("paired: {}".format(paired))

  this.wallTime = Option((4 * 60 * 60).toLong)
  this.nCoresRequest = Option(16) 
  def commandLine = "bowtie2 -q -p 16 -L 20 --local --no-unal" +
    conditional(paired, " --un " + outNoRepetitive) +
    conditional(paired, "--un-conc " + outNoRepetitive) +
    "-x repbase18.05.all.ref " + 
    conditional(!paired, "-U %s".format(inFastq)) + 
    conditional(paired, "-1 %s -2 %s".format(inFastq, inFastqPair)) +
    " | samtools view -F 4 -Sb - > %s".format(outRepetitive)

}
