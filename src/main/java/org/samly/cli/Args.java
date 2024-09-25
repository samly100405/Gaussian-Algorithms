package org.samly.cli;

import com.beust.jcommander.Parameter;

public class Args {
    @Parameter(description = "Linear System file")
    private String fileName;

    @Parameter(names = {"-d", "--double"}, description = "enable double precision")
    private boolean isDouble = false;

    @Parameter(names = {"--spp"}, description = "enable scaled partial pivoting")
    private boolean isSPP = false;

    @Parameter(names = {"-o", "--output"}, description = "set output file")
    private String outputFile = "data/sys1.sol"; // This is default for assignment

    public String getFileName() {
        return fileName;
    }

    public boolean isDouble() {
        return isDouble;
    }

    public boolean isSPP() {
        return isSPP;
    }

    public String getOutputFile() {
        return outputFile;
    }
}
