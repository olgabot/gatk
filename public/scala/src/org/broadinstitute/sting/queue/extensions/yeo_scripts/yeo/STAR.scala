package org.broadinstitute.sting.queue.extensions.yeo

import org.broadinstitute.sting.commandline._
import java.io.File
import org.broadinstitute.sting.queue.function.CommandLineFunction

class STAR extends CommandLineFunction {
  
  
  @Input(doc="input fastq file", shortName = "inFastq", fullName = "input_fastq_file", required = true) 
  var inFastq: File = _
  
  @Input(doc="input fastq pair file", shortName = "inFastqPair", fullName = "input_fastq_pair_file", required = false) 
  var inFastqPair: File = _ 
  
  @Output(doc="output sam file", shortName = "outSam", fullName = "out_sam_file", required = true) 
  var outSam: File = _

  @Output(doc="output fastq file", shortName = "outFastq", fullName = "out_fastq_file", required = false) 
  var outFastq: File = _
  
  @Argument(doc="genome location", shortName = "genome", fullName = "genome", required = true) 
  var genome: String = _
  
  @Argument(doc="maximum number of reads to multimap", 
	    shortName = "multimapNMax", 
	   fullName = "multimapNMax", required = true)     
  var multimapNMax: Int = 10
  
  @Argument(doc="the score range below the maximum score for multimapping alignments", 
	    shortName = "outFilterMultimapScoreRange", 
	    fullName = "outFilterMultimapScoreRange", required = true) 
  var outFilterMultimapScoreRange: Int = 1
  
  @Argument(doc="use intronMotif", shortName = "intronMotif", fullName = "intronMotif", required = false) 
  var intronMotif: Boolean = false
  
  @Argument(doc="fastq is compressed", shortName = "isCompressed", fullName = "isCompressed", required = false)
  var isCompressed: Boolean = false
  
  @Argument(doc="use alpha version of STAR instead of normal version", shortName = "alpha", fullName = "alphaStar", required = false)
  var alpha: Boolean = false

  this.wallTime = Option((8 * 60 * 60).toLong)  
  this.nCoresRequest = Option(16) 
  var gzip_regex = ".gz$".r
  
  override def shortDescription = "STAR"  
  var STAR = "STAR "
  if(alpha) {
   STAR = "~/software/STAR_2.3.1x/STAR "
 }

  def commandLine = STAR +
  required("--runMode", "alignReads") +
  required("--runThreadN", "16") +
  required("--genomeDir", genome) +
  required("--genomeLoad", "LoadAndRemove") +
  required("--readFilesIn", inFastq) +
  optional(inFastqPair) +
  required("--outSAMunmapped", "Within") +
  required("--outFilterMultimapNmax", multimapNMax) +
  required("--outFilterMultimapScoreRange", outFilterMultimapScoreRange ) +
  required("--outFileNamePrefix", outSam) +
  required("--outSAMattributes", "All") +
  // required("--limitBAMsortRAM", "60000000000") +
  conditional(intronMotif, "--outSAMstrandField intronMotif") +
  conditional(gzip_regex.findFirstIn(inFastq.toString()) != None, "--readFilesCommand zcat")+
  required("--outStd", "BAM_Unsorted") +
  required("--outSAMtype", "BAM", "Unsorted") +
  required("--outFilterType", "BySJout") + 
  required("--outReadsUnmapped", "Fastx") + 
  required("--outFilterScoreMin", "10") +
  required("--outSAMattrRGline", "ID:foo") +
  "> " + outSam
		
 //this.isIntermediate = true
}
