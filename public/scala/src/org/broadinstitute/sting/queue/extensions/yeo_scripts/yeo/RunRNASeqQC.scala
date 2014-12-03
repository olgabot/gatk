package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class RunRNASeQC extends CommandLineFunction {

 @Input(doc="input sample file", shortName = "sample", fullName = "input_sample_files", required = true) 
 var input: File = _
 
 @Argument(doc="gc content file", shortName = "gc", fullName = "gc_content", required = true) 
 var gc: String = _
 
 @Argument(doc="output dir", shortName = "out", fullName = "output_dir", required = true) 
 var output: String = _
 
 @Argument(doc="genome fa file", shortName = "genome", fullName = "genome_file", required = true) 
 var genome: String = _
 
 @Argument(doc="gtf file", shortName = "gtf", fullName = "gtf_file", required = true) 
 var gtf: String = _
 
 @Argument(doc="reads are single ended", shortName = "single_end", fullName = "single_end", required = false) 
 var singleEnd: Boolean = true
 
 override def shortDescription = "RunRNASeQC"
 this.wallTime = Option((16 * 60 * 60).toLong)

 def commandLine = "java -jar /home/yeo-lab/software/rnaseqc/RNA-SeQC_v1.1.7.jar " + 
		     optional("-gc", gc) +
		     optional("-o", output) +
		     required("-r", genome) +
		     required("-s", input) +
		     required("-t", gtf) +		    		   
		     conditional(singleEnd, "-singleEnd")
 this.isIntermediate = false
}
