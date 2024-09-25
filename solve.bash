#!/bin/bash

JAR="target/gaussian-1.0-SNAPSHOT-runnable.jar"
INPUT="data/$1.lin"
OUTPUT_NAIVE_SINGLE="data/$1_naive_single.sol"
OUTPUT_SPP_SINGLE="data/$1_spp_single.sol"
OUTPUT_NAIVE_DOUBLE="data/$1_naive_double.sol"
OUTPUT_SPP_DOUBLE="data/$1_spp_double.sol"

if [ ! -f $JAR ]; then
  echo "Jar not found. Use 'mvn install'."
  exit 1
fi

if [ ! -f "$INPUT" ]; then
  echo "Input not found."
  exit 1
fi

echo "$INPUT solutions"

echo "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"

java -jar $JAR "$INPUT" -o "$OUTPUT_NAIVE_SINGLE"

echo "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"

java -jar $JAR "$INPUT" --double -o "$OUTPUT_NAIVE_DOUBLE"

echo "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"

java -jar $JAR "$INPUT" --spp -o "$OUTPUT_SPP_SINGLE"

echo "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"

java -jar $JAR "$INPUT" --spp --double -o "$OUTPUT_SPP_DOUBLE"

echo "Successfully solved $INPUT. Solutions stored in  data/."