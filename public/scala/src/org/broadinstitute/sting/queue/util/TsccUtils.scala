
/* The above copyright notice and this permission notice shall be
* included in all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
* EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
* OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
* NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
* HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
* WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
* FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR
* THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package org.broadinstitute.sting.queue.util

import java.io.File
import collection.JavaConversions._

object TsccUtils {

    def starGenomeLocation(genome: String) : String = {
  //Returns star genome Location for TSCC, could eventually be factored out into conf file

   var retval = "none"
   if (genome == "hg19") {
      retval = "/projects/ps-yeolab/genomes/hg19/star_sjdb"
   }else if(genome == "mm9") {
      retval = "/projects/ps-yeolab/genomes/mm9/star"
   }else if(genome == "mm10") {
      retval = "/projects/ps-yeolab/genomes/mm10/star_sjdb"
   }else if(genome == "ce10") {
      retval = "/projects/ps-yeolab/genomes/ce10/star"
   }else if(genome == "ce10_flag") {
     retval = "/projects/ps-yeolab/genomes/ce10/ce10_genome_adr-1_flag_adjusted_heather_hundley/"
   }else if(genome == "dm3") {
      retval = "/projects/ps-yeolab/genomes/dm3/star"
   }else if(genome == "S288C_R64") {
     retval = "/projects/ps-yeolab/genomes/S288C_R64/star_sjdb"
   }
   retval
  }


  def repBaseGenomeLocation(genome: String) : String = {
    //Returns star genome Location for TSCC, could eventually be factored out into conf file
    
    var retval = "none"
    if (genome == "hg19") {
      retval = "/projects/ps-yeolab/genomes/RepBase18.05.fasta/species_specic/homo_sapiens_repbase"
    }else if(genome == "mm9") {
      retval = "/projects/ps-yeolab/genomes/RepBase18.05.fasta/species_specic/mus_musculus_repbase"
    }else if(genome == "mm10") {
      retval = "/projects/ps-yeolab/genomes/RepBase18.05.fasta/species_specic/mus_musculus_repbase"
    }else {
      retval = "/projects/ps-yeolab/genomes/RepBase18.05.fasta/STAR_fixed/"
    }
    retval
  }
  
  def sailfishGenomeIndexLocation(genome: String) : String = {
   //returns sailfish location for TSCC
     var retval = "none"
     if (genome == "hg19") {
       retval = "/projects/ps-yeolab/genomes/hg19/sailfish/gencode.v19.pc_lncRNA_transcripts.ercc_fluidigm_spikein.fa_sailfish_index_k31"
     }else if(genome == "mm10") {
       retval = "/projects/ps-yeolab/genomes/mm10/sailfish_fixed/gencode.vM2.pc_lncRNA_transcripts.ercc_fluidigm_spikein.gfp.fa_sailfish_index"
     }else if(genome == "mm9") {
       retval = "/projects/ps-yeolab/genomes/mm9/sailfish"
     }
     retval
  }


  def chromSizeLocation(genome: String) : String = {
  //Returns star genome Location for TSCC, could eventually be factored out into conf file

   var retval = "none"
   if (genome == "hg19") {
      retval = "/projects/ps-yeolab/genomes/hg19/hg19.chrom.sizes"
   }else if(genome == "mm9") {
      retval = "/projects/ps-yeolab/genomes/mm9/mm9.chrom.sizes"
   }else if(genome == "ce10") {
      retval = "/projects/ps-yeolab/genomes/ce10/ce10.chrom.sizes"
   }else if(genome == "dm3") {
      retval = "/projects/ps-yeolab/genomes/dm3/dm3.chrom.sizes"
   }else if(genome == "S288C_R64") {
     retval = "/projects/ps-yeolab/genomes/S288C_R64/sacCer3.chrom.sizes"
 }

   retval
  }

  def regionsLocation(genome: String) : String = {
  //Returns star genome Location for TSCC, could eventually be factored out into conf file

   var retval = "none"
   if (genome == "hg19") {
      retval = "/projects/ps-yeolab/genomes/hg19/hg19data4"
   }else if(genome == "mm9") {
      retval = "/projects/ps-yeolab/genomes/mm9/mm9data4"
   }

   retval
  }

  def asStructureLocation(genome: String) : String = {
  //Returns star genome Location for TSCC, could eventually be factored out into conf file

   var retval = "none"
   if (genome == "hg19") {
      retval = "/projects/ps-yeolab/genomes/hg19/hg19data4"
   }else if(genome == "mm9") {
      retval = "/projects/ps-yeolab/genomes/mm9/mm9data4"
   }

   retval
  }


  def genomeLocation(genome: String) : String = {
  //Returns star genome Location for TSCC, could eventually be factored out into conf file

   var retval = "none"
   if (genome == "hg19") {
      retval = "/projects/ps-yeolab/genomes/hg19/chromosomes/all.fa"
   }else if(genome == "mm9") {
      retval = "/projects/ps-yeolab/genomes/mm9/chromosomes/all.fa"
   }else if(genome == "dm3") {
      retval = "/projects/ps-yeolab/genomes/dm3/chromosomes/all.fa"
   }else if(genome == "S288C_R64") {
     retval = "/projects/ps-yeolab/genomes/S288C_R64/S288C_reference_genome_R64-2-1_20150113/S288C_reference_sequence_R64-2-1_20150113.fsa"
   } 
   retval
  }

  def phastconsLocation(genome: String) : String = {
  //Returns star genome Location for TSCC, could eventually be factored out into conf file

   var retval = "none"
   if (genome == "hg19") {
      retval = "/projects/ps-yeolab/genomes/hg19/hg19_phastcons.bw"
   }else if(genome == "mm9") {
      retval = "/projects/ps-yeolab/genomes/mm9/mm9_phastcons.bw"
   }

   retval
  }

  def genicRegionsLocation(genome: String) : String = {
  //Returns star genome Location for TSCC, could eventually be factored out into conf file

   var retval = "none"
   if (genome == "hg19") {
      retval = "/home/gpratt/clipper/clipper/data/regions/hg19_genes.bed"
   }else if(genome == "mm9") {
      retval = "/home/gpratt/clipper/clipper/data/regions/mm9_genes.bed"
   }else if(genome == "ce10") {
      retval = "/home/gpratt/clipper/clipper/data/regions/ce10_genes.bed"
   }

   retval
  }

  def gffDbLocation(genome: String) : String = {
  //Returns star genome Location for TSCC, could eventually be factored out into conf file

   var retval = "none"
   if (genome == "hg19") {
      retval = "/projects/ps-yeolab/genomes/hg19/gencode.v17.annotation.gtf.db"
   }else if(genome == "mm9") {
      retval = "/projects/ps-yeolab/genomes/mm9/gencode.vM1.annotation.gtf.db"
   }else if(genome == "ce10") {
      retval = "/projects/ps-yeolab/genomes/ce10/WS244.genes.gff3.db"
   }

   retval
  }

  def gffLocation(genome: String) : String = {
  //Returns star genome Location for TSCC, could eventually be factored out into conf file

   var retval = "none"
   if (genome == "hg19") {
      retval = "/projects/ps-yeolab/genomes/hg19/gencode_v17/gencode.v17.annotation.gtf"
   }else if(genome == "mm9") {
      retval = "/projects/ps-yeolab/genomes/mm9/gencode.vM1.annotation.gtf"
   } else if(genome == "ce10") {
      retval = "/projects/ps-yeolab/genomes/ce10/WS244.genes.gff3"
}

   retval
  }

  def exonLocation(genome: String) : String = {
  //Returns star genome Location for TSCC, could eventually be factored out into conf file

   var retval = "none"
   if (genome == "hg19") {
      retval = "/projects/ps-yeolab/genomes/hg19/gencode_v17/gencode.v17.annotation.exons.bed"
   }else if(genome == "mm9") {
      retval = "/projects/ps-yeolab/genomes/mm9/Mus_musculus.NCBIM37.64.fixed.exons.bed"
   }else if(genome == "ce10") {
      retval = "/projects/ps-yeolab/genomes/ce10/ce10.exons.BED"
   }

   retval
  }

def gcLocation(genome: String) : String = {
  //Returns star genome Location for TSCC, could eventually be factored out into conf file

   var retval = "none"
   if (genome == "hg19") {
      retval = "/projects/ps-yeolab/genomes/hg19/gencode.v17.gc.txt"
   }
   retval
  }

def snpDbLocation(genome: String) : String = {
  //Returns star genome Location for TSCC, could eventually be factored out into conf file

   var retval = "none"
   if (genome == "hg19") {
      retval = "/projects/ps-yeolab/genomes/hg19/snp137.txt.gz"
   }
   retval
  }
}
