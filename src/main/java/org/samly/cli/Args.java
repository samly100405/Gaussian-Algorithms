package org.samly.cli;

import com.beust.jcommander.Parameter;

public class Args {
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Parameter(description = "Linear System file")
    private String fileName;

    @Parameter(names = {"-d", "--double"}, description = "enable double precision")
    private boolean isDouble = false;

    @Parameter(names = {"--spp"}, description = "enable scaled partial pivoting")
    private boolean isSPP = false;

    public boolean isDouble() {
        return isDouble;
    }

    public void setDouble(boolean aDouble) {
        isDouble = aDouble;
    }

    public boolean isSPP() {
        return isSPP;
    }

    public void setSPP(boolean SPP) {
        isSPP = SPP;
    }
}
