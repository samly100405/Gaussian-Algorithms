public class Main {

    private static LinearSolver generateSolver(int variables, float[][] coefficients, float[] constants, boolean spp) {
        if (spp) {
            return new SPPSinglePrecision(variables,coefficients, constants);
        }

        return new NaiveSinglePrecision(variables, coefficients, constants);
    }

    private static LinearSolver generateSolver(int variables, double[][] coefficients, double[] constants, boolean spp) {
        if (spp) return null; // TODO: implement SPP

        return new NaiveDoublePrecision(variables, coefficients, constants);
    }

    public static void main(String[] args) {
        // TODO: implement file reading
        int vars = 3;
        float[] constantsFloat = {1, 0, -2};
        float[][] coefficientsFloat = {
                {3f, 4f, 1f},
                {2f, 3f, 0f},
                {4f, 3f, -1f},
        };
        double[] constantsDouble = {1, 0, -2};
        double[][] coefficientsDouble = {
                {3, 4, 1},
                {2, 3, 0},
                {4, 3, -1},
        };

        LinearSolver naiveSingle = generateSolver(vars, coefficientsFloat, constantsFloat, false);
        LinearSolver sPPSingle   = generateSolver(vars, coefficientsFloat, constantsFloat, true);
        LinearSolver naiveDouble = generateSolver(vars, coefficientsDouble, constantsDouble, false);
//        LinearSolver SPPDouble   = generateSolver(vars, coefficientsDouble, constantsDouble, true);

        System.out.println(naiveSingle.getSolutionString());
        System.out.println(sPPSingle.getSolutionString());
        System.out.println(naiveDouble.getSolutionString());
    }
}
