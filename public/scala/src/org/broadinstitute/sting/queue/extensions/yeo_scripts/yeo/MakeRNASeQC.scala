package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class MakeRNASeQC extends CommandLineFunction {

 @Input(doc="input sample files", shortName = "sample", fullName = "input_sample_files", required = true) 
 var inBam: List[File] = Nil
 
 @Output(doc="ouf file", shortName = "f", fullName = "flip", required = true) 
 var out: File = _
 this.wallTime = Option((.5 * 60 * 60).toLong)

 override def shortDescription = "MakeRNASeQC" 
 def commandLine = "make_rnaseqc.py " + 
     		   repeat(inBam) + 
		   " > " + required(out) 
					      	    
 this.isIntermediate = true

}
