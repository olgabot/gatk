package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class RnaEditing(@Input inBam: File, @Argument snpEffDb: String, @Argument snpDb: String, @Argument genome: String, @Argument flipped: String, @Output output: File) extends CommandLineFunction {
  this.wallTime = Option((336 * 60 * 60).toLong)
  def commandLine = "create_rna_editing_makefile.py " +
      		     required("--bam", inBam) +
		     required("--snpEffDb", snpEffDb) +
		     required("--snpDb", snpDb) +
		     required("--genome", genome) +
		     required("--flipped", flipped) + 
		     required("--outfile", output) + " && make -j 2 -f " + output    			           
	}		     