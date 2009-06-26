package org.broadinstitute.sting.utils.genotype.glf;

import net.sf.samtools.util.BinaryCodec;
import net.sf.samtools.util.BlockCompressedOutputStream;

import java.io.File;
import java.io.DataOutputStream;

import org.broadinstitute.sting.utils.genotype.GenotypeWriter;
import org.broadinstitute.sting.utils.genotype.IndelLikelihood;
import org.broadinstitute.sting.utils.genotype.LikelihoodObject;
/*
 * Copyright (c) 2009 The Broad Institute
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

/**
 * @author aaron
 * @version 1.0
 *          <p/>
 *          This class writes GLF files. You can either specify GLFRecords, or programaticly generate
 *          single and variable length genotype calls using the provided functions.  When you've finished
 *          generating GLF records, make sure you close the file.
 */
public class GLFWriter implements GenotypeWriter {
    // our output codec
    private final BinaryCodec outputBinaryCodec;

    // the glf magic number, which identifies a properly formatted GLF file
    public static final short[] glfMagic = {'G', 'L', 'F', '\3'};

    // our header text, reference sequence name (i.e. chr1), and it's length
    private String headerText = "";
    private String referenceSequenceName = "";
    private long referenceSequenceLength = 0;

    /**
     * The public constructor for creating a GLF object
     *
     * @param headerText            the header text (currently unclear what the contents are)
     * @param referenceSequenceName the reference sequence name, i.e. "chr1", "chr2", etc
     */
    public GLFWriter( String headerText, String referenceSequenceName, int referenceSequenceLength, File writeTo ) {
        this.headerText = headerText;
        this.referenceSequenceName = referenceSequenceName;
        this.referenceSequenceLength = referenceSequenceLength;
        outputBinaryCodec = new BinaryCodec(new DataOutputStream(new BlockCompressedOutputStream(writeTo)));
        outputBinaryCodec.setOutputFileName(writeTo.toString());
        this.writeHeader();
    }

    /**
     * add a point genotype to the GLF writer
     *
     * @param refBase    the reference base, as a char
     * @param genomicLoc the location, as an offset from the previous glf record
     * @param readDepth  the read depth at the specified postion
     * @param rmsMapQ    the root mean square of the mapping quality
     * @param lhValues   the GenotypeLikelihoods object, representing the genotype likelyhoods
     */
    @Override
    public void addGenotypeCall( int genomicLoc,
                                 float rmsMapQ,
                                 char refBase,
                                 int readDepth,
                                 LikelihoodObject lhValues ) {


        SinglePointCall call = new SinglePointCall(refBase, genomicLoc,
                readDepth,
                (short) rmsMapQ,
                lhValues);
        call.write(this.outputBinaryCodec);
    }

    /**
     * add a variable length (indel, deletion, etc) to the genotype writer
     *
     * @param refBase       the reference base
     * @param genomicLoc    the location, as an offset from the previous glf record
     * @param readDepth     the read depth at the specified postion
     * @param rmsMapQ       the root mean square of the mapping quality
     * @param firstHomZyg   the first homozygous call
     * @param secondHomZyg  the second homozygous call
     * @param hetLikelihood the negitive log likelihood of the heterozygote,  from 0 to 255
     */
    @Override
    public void addVariableLengthCall( int genomicLoc,
                                       float rmsMapQ,
                                       int readDepth,
                                       char refBase,
                                       IndelLikelihood firstHomZyg,
                                       IndelLikelihood secondHomZyg,
                                       byte hetLikelihood ) {

        // in this context, the minumum likelihood is lowest of the three options
        double lowestLikelihood = Double.MAX_VALUE;
        if (firstHomZyg.getLikelihood() < lowestLikelihood) {
            lowestLikelihood = firstHomZyg.getLikelihood();
        } else if (secondHomZyg.getLikelihood() < lowestLikelihood) {
            lowestLikelihood = secondHomZyg.getLikelihood();
        } else if (hetLikelihood < lowestLikelihood) {
            lowestLikelihood = hetLikelihood;
        }

        // normalize the two
        VariableLengthCall call = new VariableLengthCall(refBase,
                genomicLoc,
                readDepth,
                lowestLikelihood,
                (short) rmsMapQ,
                firstHomZyg.getLikelihood(),
                secondHomZyg.getLikelihood(),
                hetLikelihood,
                firstHomZyg.getLengthOfIndel(),
                secondHomZyg.getLengthOfIndel(),
                firstHomZyg.getIndelSequence(),
                secondHomZyg.getIndelSequence());

        call.write(this.outputBinaryCodec);

    }

    /**
     * add a no call to the genotype file, if supported.
     *
     * @param position  the position
     * @param readDepth the read depth
     */
    @Override
    public void addNoCall( int position, int readDepth ) {
        // glf doesn't support this operation
        throw new UnsupportedOperationException("GLF doesn't support a 'no call' call.");
    }

    /**
     * add a GLF record to the output file
     *
     * @param rec the GLF record to write.
     */
    public void addGLFRecord( GLFRecord rec ) {
        rec.write(this.outputBinaryCodec);
    }

    /**
     * Write out the header information for the GLF file.  The header contains
     * the magic number, the length of the header text, the text itself, the reference
     * sequence (null terminated) preceeded by it's length, and the the genomic
     * length of the reference sequence.
     */
    private void writeHeader() {
        for (int x = 0; x < glfMagic.length; x++) {
            outputBinaryCodec.writeByte(glfMagic[x]);
        }
        if (!( headerText.equals("") )) {
            outputBinaryCodec.writeString(headerText, true, true);
        } else {
            outputBinaryCodec.writeInt(0);
        }
        outputBinaryCodec.writeString(referenceSequenceName, true, true);
        outputBinaryCodec.writeUInt(referenceSequenceLength);
    }

    /**
     * close the file.  You must close the file to ensure any remaining data gets
     * written out.
     */
    @Override
    public void close() {
        outputBinaryCodec.writeByte((byte) 0);
        outputBinaryCodec.close();
    }

    /**
     * normalize the values to the range of a byte (0 - 255)
     *
     * @param values the floating point values to normalize
     *
     * @return a byte array containing the normalized values
     */
    private byte[] normalizeToByte( double[] values ) {
        byte ret[] = new byte[values.length];
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (double d : values) {
            min = ( d < min ) ? d : min;
            max = ( d > max ) ? d : max;
        }
        double scale = max / 255.0;
        for (int x = 0; x < values.length; x++) {
            ret[x] = (byte) ( ( values[x] - min ) / scale );
        }
        return ret;
    }

}


