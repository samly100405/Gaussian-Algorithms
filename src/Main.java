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

    public static void main(String[] args) {
        // TODO: implement file reading
        int vars = 0;
        float[] constantsFloat;
        float[][] coefficientsFloat;
        double[] constantsDouble;
        double[][] coefficientsDouble;

        final String fileName = "data/sys1.lin";
        Path filePath = Paths.get(fileName);
        try {
            Scanner s = new Scanner(filePath);
            vars = s.nextInt();
            System.out.println(vars);

            // assume is single precision
            coefficientsFloat = new float[vars][vars];
            constantsFloat = new float[vars];

            for (int row = 0; row < vars; row++) {
                for (int col = 0; col < vars; col++) {
                    coefficientsFloat[row][col] = s.nextFloat();
                }
            }

            for (int i = 0; i < vars; i++) {
                constantsFloat[i] = s.nextFloat();
            }


            LinearSolver naiveSingle = generateSolver(vars, coefficientsFloat, constantsFloat, false);
            LinearSolver sPPSingle = generateSolver(vars, coefficientsFloat, constantsFloat, true);

            System.out.println(naiveSingle.getSolutionString());
            System.out.println(sPPSingle.getSolutionString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


//        LinearSolver naiveSingle = generateSolver(vars, coefficientsFloat, constantsFloat, false);
//        LinearSolver sPPSingle   = generateSolver(vars, coefficientsFloat, constantsFloat, true);
//        LinearSolver naiveDouble = generateSolver(vars, coefficientsDouble, constantsDouble, false);
//        LinearSolver sPPDouble   = generateSolver(vars, coefficientsDouble, constantsDouble, true);
//
//        System.out.println(naiveSingle.getSolutionString());
//        System.out.println(sPPSingle.getSolutionString());
//        System.out.println(naiveDouble.getSolutionString());
//        System.out.println(sPPDouble.getSolutionString());
    }
}
