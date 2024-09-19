import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int vars = 3;
        float[][] coeff =
                {
                        {3f, 4f, 1f},
                        {2f, 3f, 0f},
                        {4f, 3f, -1f},
                };
        float[] constants = {1, 0, -2};
        NaiveGaussianEliminationSinglePrecision test = new NaiveGaussianEliminationSinglePrecision(vars, coeff, constants);

        System.out.println(Arrays.toString(test.solve()));
    }
}
