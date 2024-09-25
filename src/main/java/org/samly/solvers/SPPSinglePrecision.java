package org.samly.solvers;

import java.util.Arrays;

public class SPPSinglePrecision implements LinearSolver {
    private final float[][] coefficients;
    private final float[] constants;
    private final float[] solutions;
    private final int[] idx;
    private final float[] scalingFactors;
    private final int variables;
    private final boolean showSteps;
    private boolean solved = false;

    public SPPSinglePrecision(int variables, float[][] coefficients, float[] constants, boolean showSteps) {
        this.variables = variables;
        this.showSteps = showSteps;

        this.coefficients = new float[variables][variables];
        for (int i = 0; i < variables; i++) {
            System.arraycopy(coefficients[i], 0, this.coefficients[i], 0, variables);
        }

        this.constants = new float[variables];
        System.arraycopy(constants, 0, this.constants, 0, variables);

        this.solutions = new float[variables];

        idx = new int[variables];
        for (int i = 0; i < variables; i++) {
            idx[i] = i;
        }

        scalingFactors = new float[variables];
    }

    public void solve() {
        if(solved) return;
        // Forward elimination

        // Init scaling factors
        // NOTE: Math.abs() for some reason is especially slow with floats (not doubles) ????
        // Manually finding the abs value improved performance significantly.
        for (int row = 0; row < variables; row++) {
            float max = -1;
            for (int col = 0; col < variables; col++) {
                float val = coefficients[row][col];
                if (val < 0) val = -val;
                if (val > max) max = val;
            }
            scalingFactors[row] = max;
        }

        // Pivot goes from 0 to variables - 1
        for (int pivot = 0; pivot < variables - 1; pivot++) {
            // Use SPP to determine pivot
            int maxIndex = pivot;
            for (int pivot1 = pivot + 1; pivot1 < variables; pivot1++) {
                float p1Val = coefficients[idx[pivot1]][pivot] / scalingFactors[idx[pivot1]];
                if (p1Val < 0) p1Val = -p1Val;

                float pVal = coefficients[idx[pivot]][pivot] / scalingFactors[idx[pivot]];

                if (p1Val > pVal) {
                    maxIndex = pivot1;
                }
            }
            swapRow(pivot, maxIndex);

            // Go through each row below the pivot
            for (int row = pivot + 1; row < variables; row++) {
                // Each row gets scaled so that a coefficient can be eliminated
                float scale = coefficients[idx[row]][pivot] / coefficients[idx[pivot]][pivot];

                // Eliminate
                for (int col = pivot; col < variables; col++) {
                    coefficients[idx[row]][col] -= scale * coefficients[idx[pivot]][col];
                }

                // do same for constants
                constants[idx[row]] -= scale * constants[idx[pivot]];
            }
            if (showSteps) printSystem();
        }


        // Back substitution
        solutions[variables-1] = constants[idx[variables-1]] / coefficients[idx[variables-1]][variables-1];

        for (int i = variables-2; i >= 0; i--) {
            float sum = constants[idx[i]];
            for (int j = i + 1; j < variables; j++) {
                sum = sum -coefficients[idx[i]][j] * solutions[j];
            }
            solutions[i] = sum / coefficients[idx[i]][i];
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

    private void swapRow(int a, int b) {
        int temp = idx[a];
        idx[a] = idx[b];
        idx[b] = temp;
    }

}

