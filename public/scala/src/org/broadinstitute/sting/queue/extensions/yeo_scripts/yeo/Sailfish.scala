package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class Sailfish extends CommandLineFunction {
  
  
  @Input(doc="input fastq file, read1", shortName = "inFastq", fullName = "input_fastq_file", required = true) 
  var inFastq: File = _
    
  @Input(doc="input fastq file, read 2", shortName = "inFastqPair", fullName = "input_fastq_file", required = false) 
  var inFastqPair: File = _

  @Output(doc="output dir file", shortName = "outDir", fullName = "out_dir_file", required = true) 
  var outDir: File = _
  
  @Argument(doc="sailfish index location", shortName = "index", fullName = "index", required = true) 
  var index: String = _
    
  @Argument(doc="sh script to output", shortName = "shScript", fullName = "ash_script", required = false)
  var shScript: String = _

  @Argument(doc="If provided, then the reads are strand-specific. Otherwise, the reads are assumed to be unstranded.", 
    shortName = "stranded", fullName = "strand_specific", required = false)
  var stranded: Boolean = false
  
  override def shortDescription = "sailfish"
  this.nCoresRequest = Option(16)

  this.wallTime = Option((4 * 60 * 60).toLong) 
   
  def commandLine = "sailfish_quant.py" +
  required("-1", inFastq) +
  optional("-2", inFastqPair) +
  conditional(stranded, "--stranded") +
  required("--out-dir", outDir) +
  required("--index", index) +
  required("-n", shScript) +
  required("--out-sh", shScript) +
  required("-i", index) +
  "-p 16 --not-gzipped --do-not-submit && echo $(tail -n 2 " +
  required(shScript) +
  ") | bash"            
}


