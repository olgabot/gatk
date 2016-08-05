package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class Sailfish extends CommandLineFunction {
  
  
  @Input(doc="input fastq file", shortName = "inFastq", fullName = "input_fastq_file", required = true) 
  var inFastq: File = _
    
  @Output(doc="output dir file", shortName = "outDir", fullName = "out_dir_file", required = true) 
  var outDir: File = _
  
  @Argument(doc="sailfish index location", shortName = "index", fullName = "index", required = true) 
  var index: String = _
    
  @Argument(doc="sh script to output", shortName = "shScript", fullName = "ash_script", required = false)
  var shScript: String = _
  
  override def shortDescription = "kallisto quasi-mapping gene expression quantification"
  this.nCoresRequest = Option(8)
   
  def commandLine = "kallisto" +
  required("-1", inFastq) +
  required("--out-dir", outDir) +
  required("--index", index) +
  required("-n", shScript) +
  required("--out-sh", shScript) +
  required("-i", index) +
  "-p 8 --do-not-submit && echo $(tail -n 2 " +
  required(shScript) +
  ") | bash"            
}


