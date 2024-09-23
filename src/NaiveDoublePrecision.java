import java.util.Arrays;

public class NaiveDoublePrecision implements LinearSolver {
    private final double[][] coefficients;
    private final double[] constants;
    private final double[] solutions;
    private final int variables;

    public NaiveDoublePrecision(int variables, double[][] coefficients, double[] constants) {
        this.variables = variables;
        this.coefficients = coefficients;
        this.constants = constants;

        this.solutions = new double[variables];
    }

    public void solve() {
        // Forward elimination

        // Pivot goes from 0 to variables - 1
        for (int pivot = 0; pivot < variables - 1; pivot++) {
            // Go through each row below the pivot
            for (int row = pivot + 1; row < variables; row++) {
                // Each row gets scaled so that a coefficient can be eliminated
                double scale = coefficients[row][pivot] / coefficients[pivot][pivot];

                // Eliminate
                for (int col = pivot; col < variables; col++) {
                    coefficients[row][col] -= scale * coefficients[pivot][col];
                }

                // do same for constants
                constants[row] -= scale * constants[pivot];
            }
        }


        // Back substitution
        solutions[variables-1] = constants[variables-1] / coefficients[variables-1][variables-1];

        for (int i = variables-2; i >= 0; i--) {
            double sum = constants[i];
            for (int j = i + 1; j < variables; j++) {
                sum = sum -coefficients[i][j] * solutions[j];
            }
            solutions[i] = sum / coefficients[i][i];
        }
    }

    // This design is because I will need to implement the same alg with a different
    // primitive for double precision.
    public String getSolutionString() {
        return Arrays.toString(solutions);
    }
}
