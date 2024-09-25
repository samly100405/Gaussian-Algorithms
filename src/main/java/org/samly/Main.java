package org.samly;

import com.beust.jcommander.JCommander;
import org.samly.cli.Args;
import org.samly.solvers.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    private static LinearSolver generateSolver(Args args)
            throws IOException {
        Path filePath = Paths.get(args.getFileName());
        Scanner s = new Scanner(filePath);

        int vars = s.nextInt();


        if (args.isDouble()){
            double[][] coefficients = new double[vars][vars];
            double[] constants    = new double[vars];

            for (int row = 0; row < vars; row++) {
                for (int col = 0; col < vars; col++) {
                    coefficients[row][col] = s.nextDouble();
                }
            }

            for (int i = 0; i < vars; i++) {
                constants[i] = s.nextDouble();
            }

            if (args.isSPP()) return new SPPDoublePrecision(vars, coefficients, constants, args.isShowSteps());
            return new NaiveDoublePrecision(vars, coefficients, constants, args.isShowSteps());
        }
        else {
            float[][] coefficients = new float[vars][vars];
            float[] constants      = new float[vars];


            for (int row = 0; row < vars; row++) {
                for (int col = 0; col < vars; col++) {
                    coefficients[row][col] = s.nextFloat();
                }
            }

            for (int i = 0; i < vars; i++) {
                constants[i] = s.nextFloat();
            }

            if (args.isSPP()) return new SPPSinglePrecision(vars, coefficients, constants, args.isShowSteps());
            return new NaiveSinglePrecision(vars, coefficients, constants, args.isShowSteps());
        }
    }

    public static void main(String[] args) {
        // TODO: implement file reading

        Args a = new Args();
        JCommander.newBuilder()
                .addObject(a)
                .build()
                .parse(args);
        System.out.println(a.getDescription());

        try {
            LinearSolver solver = generateSolver(a);

            long start = System.nanoTime();
            solver.solve();
            long duration = System.nanoTime() - start;

            String solution = solver.getSolutionString();
            FileWriter f = new FileWriter(a.getOutputFile());
            f.write(solution);
            f.close();
            System.out.println("Solution: " + solution);
            System.out.println("Runtime (nanoseconds): " + duration);
        }
        catch (NullPointerException e) {
            System.out.println("You must provide a file name.");
        }
        catch (IOException e) {
            System.out.println(a.getFileName());
            System.out.println("File does not exist.");
        }

    }
}
