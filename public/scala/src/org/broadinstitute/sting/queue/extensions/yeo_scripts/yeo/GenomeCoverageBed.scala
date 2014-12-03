package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class GenomeCoverageBed extends CommandLineFunction {

 @Input(doc="input bed file", shortName = "inBed", fullName = "input_bed_files", required = false) 
 var inBed: File = _
 
 @Input(doc="input bam file", shortName = "inBam", fullName = "input_bam_files", required = false) 
 var inBam: File = _
 
 @Argument(doc="file contating chromosome sizes", shortName = "genomeSize", fullName = "chromosome_sizes", required = true) 
 var genomeSize: String = _
 
 @Output(doc="output bed graph file", shortName = "bedGraph", fullName = "out_bed_graph", required = true) 
 var bedGraph: File = _
 
 @Argument(doc="use a single strand when generating the bed graph (+ / -)", shortName = "strand", fullName = "strand", required = false) 
 var strand: String = null
 
 @Argument(doc="split gapped alignments", shortName = "split", fullName = "split", required = false) 
 var split: Boolean = true 
 override def shortDescription = "GenomeCoverageBed"

 
 this.wallTime = Option((.5 * 60 * 60).toLong)
 
 //This is a huge hack to flip second strand reads to first strand for pe data without effecting the rest of the pipeline
 //need to refactor to take into account bam (se and pe) and bed in a way thats readable 
  def commandLine = conditional(inBam != null, "samtools view -h " + inBam + 
      		    " | awk 'BEGIN {OFS=\"\\t\"} {if(!!and($2,0x0080)) {if(!!and($2, 0x0004)) {$2 = $2 - 16} else {$2 = $2 + 16}}; print $0}' " +
		    " | samtools view -bS - | genomeCoverageBed -ibam stdin ", escape=false) +
      		    conditional(inBed != null, "genomeCoverageBed") +
		    optional("-i", inBed) +   
		    required("-bg") + 
		    optional("-strand", strand) + 
		    conditional(split, "-split") + 
		    required("-g", genomeSize) +  
		    required(">", bedGraph, escape=false)

 this.isIntermediate = true
}
