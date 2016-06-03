package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class FastQC extends CommandLineFunction {
  @Input(doc="input fastq file", shortName = "inFastq", fullName = "in_fastq", required = true)
  var inFastq: File = _
  
  @Argument(doc="outDir", shortName = "outDir", fullName = "out_dir", required = false)
  var outDir: String = null

  @Output(doc="dummyOut", shortName = "dummyOut", fullName = "dummy_out", required = false)
  var dummyOut: File = null
  
  override def shortDescription = "FastQC"
  this.wallTime = Option((1.5 * 60 * 60).toLong)
  
  def swapExt(file: File, oldExtension: String, newExtension: String) =
    new File(file.getName.stripSuffix(oldExtension) + newExtension)
  
  override def freezeFieldValues() {
    super.freezeFieldValues()
    var newFile = swapExt(swapExt(inFastq, ".gz", ""), ".fastq", "")
    if (outDir == null) {
      dummyOut = new File(newFile.getPath + ".dummy_fastqc")
    } else {
      dummyOut = new File(outDir, newFile.getName + ".dummy_fastqc")
    }
  }
  
  

  def commandLine = "fastqc" +
  required(inFastq) + 
  optional("-o", outDir) +
  conditional(outDir == null, "-o `cwd`") +
  " > " + dummyOut
 
}
