package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class NegBedGraph(@Input inBedGraph: File, @Output outBedGraph: File) extends CommandLineFunction {
  override def shortDescription = "NegBedGraph"
  this.wallTime = Option((.5 * 60 * 60).toLong)
  def commandLine = "negBedGraph.py " + required("--bg", inBedGraph) + " > " + required(outBedGraph)
  this.isIntermediate = true
}
