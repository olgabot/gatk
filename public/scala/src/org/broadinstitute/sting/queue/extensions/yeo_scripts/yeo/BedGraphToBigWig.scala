package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class BedGraphToBigWig(@Input inBedGraph: File, @Argument genomeSize: String, @Output bigWig: File) extends CommandLineFunction {
  this.wallTime = Option((.5 * 60 * 60).toLong)

  override def shortDescription = "bedgraphtobigwig"
  def commandLine = "bedGraphToBigWig %s %s %s".format(inBedGraph, genomeSize, bigWig)

}
