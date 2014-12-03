package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class SingleRPKM extends CommandLineFunction {

 @Input(doc="input count file", shortName = "inCount", fullName = "input_count_file", required = true) 
 var inCount: File = _

 @Output(doc="output RPKM file", shortName = "outRPKM", fullName = "out_rpkm_file", required = true) 
 var outRPKM: File = _
 this.wallTime = Option((.5 * 60 * 60).toLong)
 
 override def shortDescription = "SingleRPKM" 
 def commandLine = "single_RPKM.py " + 
		   required("--input", inCount) + 
		   required("--output", outRPKM)  
		   
		   
		    
 this.isIntermediate = false
}

