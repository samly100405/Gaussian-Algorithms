package cli;

import com.beust.jcommander.Parameter;

public class Args {
    @Parameter(names = {"-d", "--double"}, description = "enable double precision")
    boolean isDouble = false;

    @Parameter(names = {"--spp"}, description = "enable scaled partial pivoting")
    boolean isSPP = false;
}
