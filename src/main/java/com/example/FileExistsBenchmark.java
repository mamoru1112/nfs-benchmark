package com.example;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@Warmup(iterations=3)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.Throughput)
@Fork(1)
public class FileExistsBenchmark {

    private static final Path filePath = Paths.get("/Users/mshimizu/sample1.txt");

    @Benchmark
    public void File_exists() {
        filePath.toFile().exists();
    }

    @Benchmark
    public void Files_exists() {
        Files.exists(filePath);
    }

    @Benchmark
    public void File_isfile() {
        filePath.toFile().isFile();
    }

    @Benchmark
    public void Files_isreadble() {
        Files.isReadable(filePath);
    }

    @Benchmark
    public void File_canread() {
        filePath.toFile().canRead();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(FileExistsBenchmark.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }

}
