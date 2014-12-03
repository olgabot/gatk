package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class NormalizeBedGraph(@Input inBedGraph: File, @Input inBam: File, @Output outBedGraph: File) extends CommandLineFunction {
  override def shortDescription = "NormalizeBedGraph"
  this.wallTime = Option((.5 * 60 * 60).toLong)

  def commandLine = "normalize_bedGraph.py " + 
      		    required("--bg", inBedGraph) +
		    required("--bam", inBam) +  
		    " > " + required(outBedGraph)
  this.isIntermediate = true
}
