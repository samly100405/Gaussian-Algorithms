public class Main {
    public static void main(String[] args) {
        int vars = 3;
        float[] constants = {1, 0, -2};
        float[][] coeff = {
                {3f, 4f, 1f},
                {2f, 3f, 0f},
                {4f, 3f, -1f},
        };
        NaiveSinglePrecision single = new NaiveSinglePrecision(vars, coeff, constants);

        final long startTime = System.nanoTime();
        single.solve();
        final long duration = System.nanoTime() - startTime;

        System.out.println(single.getSolutionString());
        System.out.println(duration);

        int vars1 = 3;
        double[] constants1 = {1, 0, -2};
        double[][] coeff1 = {
                {3, 4, 1},
                {2, 3, 0},
                {4, 3, -1},
        };
        NaiveDoublePrecision doublePrecision = new NaiveDoublePrecision(vars1, coeff1, constants1);
        final long startTime1 = System.nanoTime();
        doublePrecision.solve();
        final long duration1 = System.nanoTime() - startTime1;

        System.out.println(doublePrecision.getSolutionString());
        System.out.println(duration1);
    }
}
