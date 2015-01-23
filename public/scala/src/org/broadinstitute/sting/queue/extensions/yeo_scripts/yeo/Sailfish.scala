package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class Sailfish extends CommandLineFunction {
  
  @Input(doc="input fastq file (Read 1)", shortName = "inFastq", fullName = "input_fastq_file", required = true) 
  var inFastq: File = _

  @Input(doc="input fastq pair file (Read 2)", shortName = "inFastqPair", fullName = "input_fastq_pair_file", required = false) 
  var inFastqPair: File = _ 

  @Output(doc="output dir file", shortName = "outDir", fullName = "out_dir_file", required = true) 
  var outDir: File = _
  
  @Argument(doc="sailfish index location", shortName = "index", fullName = "index", required = true) 
  var index: String = _
    
  @Argument(doc="sh script to output", shortName = "shScript", fullName = "ash_script", required = false)
  var shScript: String = _
  
  override def shortDescription = "Run Sailfish k-mer counting based transcript quantification"
  this.nCoresRequest = Option(8)
   
  def commandLine = "sailfish_quant.py" +
  required("-1", inFastq) +
  optional("-2", inFastqPair) +
  required("--out-dir", outDir) +
  required("--index", index) +
  required("-n", shScript) +
  required("--out-sh", shScript) +
  required("-i", index) +
  "-p 8 --do-not-submit && echo $(tail -n 2 " +
  required(shScript) +
  ") | bash"            
}


