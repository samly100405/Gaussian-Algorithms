package org.samly.solvers;

import java.util.Arrays;

public final class NaiveSinglePrecision implements LinearSolver {
    private final float[][] coefficients;
    private final float[] constants;
    private final float[] solutions;
    private final int variables;
    private final boolean showSteps;
    private boolean solved = false;

    public NaiveSinglePrecision(int variables, float[][] coefficients, float[] constants, boolean showSteps) {
        this.variables = variables;
        this.showSteps = showSteps;

        this.coefficients = new float[variables][variables];
        for (int i = 0; i < variables; i++) {
            System.arraycopy(coefficients[i], 0, this.coefficients[i], 0, variables);
        }

        this.constants = new float[variables];
        System.arraycopy(constants, 0, this.constants, 0, variables);

        this.solutions = new float[variables];
    }

    public void solve() {
        if(solved) return;
        // Forward elimination

        // Pivot goes from 0 to variables - 1
        for (int pivot = 0; pivot < variables - 1; pivot++) {
            // Go through each row below the pivot
            for (int row = pivot + 1; row < variables; row++) {
                // Each row gets scaled so that a coefficient can be eliminated
                float scale = coefficients[row][pivot] / coefficients[pivot][pivot];

                // Eliminate
                for (int col = pivot; col < variables; col++) {
                    coefficients[row][col] -= scale * coefficients[pivot][col];
                }

                // do same for constants
                constants[row] -= scale * constants[pivot];
            }
            if (showSteps) printSystem();
        }


        // Back substitution
        solutions[variables-1] = constants[variables-1] / coefficients[variables-1][variables-1];

        for (int i = variables-2; i >= 0; i--) {
            float sum = constants[i];
            for (int j = i + 1; j < variables; j++) {
                sum = sum -coefficients[i][j] * solutions[j];
            }
            solutions[i] = sum / coefficients[i][i];
        }

        solved = true;
    }

    private void printSystem() {
        for (int i = 0; i < variables; i++) {
            System.out.println(Arrays.toString(coefficients[i]) + "\t" +constants[i]);
        }
        System.out.println("~~~");
    }

    // This design is because I will need to implement the same alg with a different
    // primitive for double precision.
    public String getSolutionString() {
        if (!solved) solve();

        StringBuilder out = new StringBuilder();
        for (float n : solutions) {
            out.append(n).append(" ");
        }
        return out.toString();
    }
}

