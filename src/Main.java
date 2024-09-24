import cli.Args;
import com.beust.jcommander.JCommander;
import solvers.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    private static LinearSolver generateSolver(int variables, float[][] coefficients, float[] constants, boolean spp) {
        if (spp) {
            return new SPPSinglePrecision(variables, coefficients, constants);
        }

        return new NaiveSinglePrecision(variables, coefficients, constants);
    }

    private static LinearSolver generateSolver(int variables, double[][] coefficients, double[] constants, boolean spp) {
        if (spp) {
            return new SPPDoublePrecision(variables, coefficients, constants);
        }

        return new NaiveDoublePrecision(variables, coefficients, constants);
    }

    private static void readFloat(Path filePath, float[][] coefficients, float[] constants) throws IOException {
        Scanner s = new Scanner(filePath);
        int vars = s.nextInt();

        coefficients = new float[vars][vars];
        constants = new float[vars];

        for (int row = 0; row < vars; row++) {
            for (int col = 0; col < vars; col++) {
                coefficients[row][col] = s.nextFloat();
            }
        }

        for (int row = 0; row < vars; row++) {
            constants[row] = s.nextFloat();
        }
    }

    public static void main(String[] args) {
        // TODO: implement file reading
        int vars = 0;
        float[] constantsFloat;
        float[][] coefficientsFloat;
        double[] constantsDouble;
        double[][] coefficientsDouble;

        final String fileName = "data/sys1.lin";

        Args a = new Args();
        JCommander.newBuilder()
                .addObject(a)
                .build()
                .parse(args);

        try {
            Path filePath = Paths.get(fileName);
            readFloat(filePath, coefficientsFloat, constantsFloat);
            // fuck

        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
