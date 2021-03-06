/*
* Copyright (c) 2012 The Broad Institute
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
* FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR
* THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package org.broadinstitute.sting;

import java.util.Collections;
import java.util.List;

/**
 * Assertion failure representing an MD5 mismatch between expected and actual
 *
 * @author Your Name
 * @since Date created
 */
public class MD5Mismatch extends Exception {
    final List<String> actuals, expecteds;

    public MD5Mismatch(final String actual, final String expected) {
        this(Collections.singletonList(actual), Collections.singletonList(expected));
    }

    public MD5Mismatch(final List<String> actuals, final List<String> expecteds) {
        super(formatMessage(actuals, expecteds));
        this.actuals = actuals;
        this.expecteds = expecteds;
    }

    @Override
    public String toString() {
        return formatMessage(actuals, expecteds);
    }

    private final static String formatMessage(final List<String> actuals, final List<String> expecteds) {
        final StringBuilder b = new StringBuilder("MD5 mismatch: ");
        for ( int i = 0; i < actuals.size(); i++ ) {
            if ( i > 1 ) b.append("\t\t\n");
            b.append("actual ").append(actuals.get(i));
            b.append(" expected ").append(expecteds.get(i));
        }
        return b.toString();
    }
}
