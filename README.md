# Gaussian Algorithms

# How to run

## Running manually

1. Make sure you have maven (mvm) installed on your system.
2. Navigate to project root and run `mvn install`.
3. The compiled `.jar` file will be located in the `/target` directory.
4. Use `java -jar target/gaussian-1.0-SNAPSHOT-runnable.jar <flags> <input file>`. *Note: the other .jar file doesn't work*
5. By default, the program will output results to `sys1.sol`. Use `-o <filepath>` to specify name or path.
6. Solutions and running time will be printed to `stdout`.
7. `--steps` flag can be used to show each iteration of the algorithm. *Note: this flag significantly slows performance*

## Using provided scripts

1. Make sure you have run `mvn install`.
2. Give the scripts execute permissions.
3. The `solve.bash sys1` script will sys1 using all four methods and output to respective file.
4. The `clean.bash` script will remove all `.sol` files in the `data/` directory.
5. Use the `generate_rand_system.py` script to generate a random solvable system of linear equations of `n` size.g