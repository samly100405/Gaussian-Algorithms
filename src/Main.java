import java.util.Date;

public class Main {
    public static void main(String[] args) {
        int vars = 3;
        float[] constants = {1, 0, -2};
        float[][] coeff = {
                {3f, 4f, 1f},
                {2f, 3f, 0f},
                {4f, 3f, -1f},
        };
        NaiveGaussianEliminationSinglePrecision test = new NaiveGaussianEliminationSinglePrecision(vars, coeff, constants);

        final long startTime = System.nanoTime();
        test.solve();
        final long duration = System.nanoTime() - startTime;

        System.out.println(test.getSolution());
        System.out.println(duration);
    }
}
