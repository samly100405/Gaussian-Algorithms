# Gaussian Algorithms

# How to run

1. Make sure you have maven (mvm) installed on your system.
2. Navigate to project root and run `mvn install`.
3. The compiled `.jar` file will be located in the `/target` directory.
4. Use `java -jar target/gaussian-1.0-SNAPSHOT-runnable.jar <flags> <input file>`. *Note: the other .jar file doesn't work*
5. By default, the program will output results to `sys1.sol`. Use `-o <filepath>` to specify name or path.
6. Each step of the algorithm will be printed to `stdout`.