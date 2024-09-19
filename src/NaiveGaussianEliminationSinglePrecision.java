import java.util.Arrays;

public final class NaiveGaussianEliminationSinglePrecision {
    private final float[][] coefficients;
    private final float[] constants;
    private final int variables;

    public NaiveGaussianEliminationSinglePrecision(int variables, float[][] coefficients, float[] constants) {
        this.variables = variables;
        this.coefficients = coefficients;
        this.constants = constants;
    }

    public float[] solve() {
        // Forward elimination

        // Pivot goes from 0 to variables - 1
        for (int pivot = 0; pivot < variables - 1; pivot++) {
            // Go through each row below the pivot
            for (int row = pivot + 1; row < variables; row++) {
                // Each row gets scaled so that a coefficient can be eliminated
                //
                float scale = coefficients[row][pivot] / coefficients[pivot][pivot];
                System.out.println("scale: " + scale);

                // Eliminate
                for (int col = pivot; col < variables; col++) {
                    coefficients[row][col] -= scale * coefficients[pivot][col];
                }

                // do same for constants
                constants[row] -= scale * constants[pivot];
                System.out.println(Arrays.deepToString(coefficients));
            }
        }


        float[] sol = new float[variables];
        sol[variables-1] = constants[variables-1] / coefficients[variables-1][variables-1];

        for (int i = variables-2; i >= 0; i--) {
            float sum = constants[i];
            for (int j = i + 1; j < variables; j++) {
                sum = sum -coefficients[i][j] * sol[j];
            }
            sol[i] = sum / coefficients[i][i];
        }

        return sol;
    }
}
