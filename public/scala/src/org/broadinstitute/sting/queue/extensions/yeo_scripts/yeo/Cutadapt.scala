package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class Cutadapt extends CommandLineFunction {
  //see argunments on cutadapt command line for more documentation
 
  @Input(doc="input fastq file", shortName = "inFastq", fullName = "input_fastq_file", required = true) 
  var inFastq: File = _

  @Input(doc="input paired file", shortName = "inPair", fullName = "input_fastq_file", required = false) 
  var inPair: File = _
  
  @Output(doc="output fastq file", shortName = "outFastq", fullName = "out_fastq", required = true) 
  var outFastq: File = _

  @Output(doc="output fastq file", shortName = "outFastq", fullName = "out_fastq") 
  var outPair: File = _
  
  @Argument(doc="Stats report for cutadapt", shortName = "report", fullName = "report", required = true) 
  var report: String = _
  
  @Argument(doc="Adapters to trim anywhere (-b) for cutadapt", shortName = "anywhere", fullName = "anywhere", required = false) 
  var anywhere: List[String] = Nil

  @Argument(doc="Adapters to trim anywhere (-g) for cutadapt", shortName = "five_prime", fullName = "five_prime", required = false) 
  var five_prime: List[String] = Nil

  @Argument(doc="Adapters to trim anywhere (-g) for cutadapt", shortName = "five_prime2", fullName = "five_prime2", required = false) 
  var five_prime2: List[String] = Nil

  @Argument(doc="Adapters to trim anywhere (-a) for cutadapt", shortName = "three_prime", fullName = "three_prime", required = false) 
  var three_prime: List[String] = Nil

  @Argument(doc="Adapters to trim anywhere (-A) for cutadapt", shortName = "three_prime2", fullName = "three_prime2", required = false) 
  var three_prime2: List[String] = Nil
  
  @Argument(doc="Adapters to trim at front (-f) for cutadapt", shortName = "front", fullName = "front", required = false) 
  var front: List[String] = Nil
  
  @Argument(doc="overlapping minimum", shortName = "overlap", fullName = "overlap", required = false) 
  var overlap: Int = _
  
  @Argument(doc="error rate", shortName = "error_rate", fullName = "error_rate", required = false) 
  var error_rate: Double = _
  
  @Argument(doc="length", shortName = "length", fullName = "length", required = false) 
  var length: Int = _
  
  @Argument(doc="quality_cutoff", shortName = "quality_cutoff", fullName = "quality_cutoff", required = false) 
  var quality_cutoff: Int = _

  @Argument(doc="times", shortName = "times", fullName = "times", required = false) 
  var times: Int = 2

  var anywhere2: List[String] = Nil
  if(inPair != null) {
    anywhere2 = anywhere
  }
  override def shortDescription = "cutadapt"
  //Option[Int] 
  this.wallTime = Option((16 * 60 * 60).toLong)

  def commandLine = "cutadapt -f fastq" + 
  required("--match-read-wildcards") + 
  required("--times", times) + 
  optional("-e", error_rate) + 
  optional("-O", overlap) + 
  optional("--quality-cutoff", quality_cutoff) + 
  optional("-m", length) + 
  repeat("-a", three_prime) +
  repeat("-g", five_prime) +
  repeat("-A", three_prime2) +
  repeat("-G", five_prime2) + 
  repeat("-b", anywhere) + 
  repeat("-B", anywhere2) + 
  repeat("-f", front) + 
  required("-o", outFastq) +
  optional("-p", outPair) + 
  required(inFastq) + optional(inPair) + " > " + report
  this.isIntermediate = false
}
