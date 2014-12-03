package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class ParseOldSplice extends CommandLineFunction {

 @Input(doc="input sample file", shortName = "sample", fullName = "input_sample_files", required = true) 
 var inBam: List[File] = Nil
 
 @Argument(doc="species name", shortName = "s", fullName = "species", required = true) 
 var in_species: String = _

 val make_input:(File)=> String = (x) => x.toString + " " + x.getName.split("""\.""")(0)  
 this.wallTime = Option((.5 * 60 * 60).toLong)
 
 def commandLine = "parse_oldsplice.py " + 
     		    repeat("--sample", inBam map make_input, escape=false) + 
		    required("--species", in_species) 
					      			        
 this.isIntermediate = false
}
