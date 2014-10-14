package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class BedToBigBed(@Input inBed: File, @Argument genomeSize: String, @Output outBigBed: File) extends CommandLineFunction {
  override def shortDescription = "bedToBigBed"
  def commandLine = "bedToBigBed %s %s %s -type=bed6+4".format(inBed, genomeSize, outBigBed)

}