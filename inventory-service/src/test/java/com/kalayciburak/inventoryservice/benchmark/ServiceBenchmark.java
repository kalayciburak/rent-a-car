package com.kalayciburak.inventoryservice.benchmark;

import com.kalayciburak.inventoryservice.InventoryServiceApplication;
import com.kalayciburak.inventoryservice.service.BrandService;
import com.kalayciburak.inventoryservice.service.CarService;
import com.kalayciburak.inventoryservice.service.ModelService;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class ServiceBenchmark {
    private CarService carService;
    private BrandService brandService;
    private ModelService modelService;
    private ConfigurableApplicationContext context;

    @Setup(Level.Trial)
    public void setUp() {
        context = new SpringApplicationBuilder(InventoryServiceApplication.class)
                .properties("server.port=0") // ? Boş bir port numarası almak için
                .run();
        carService = context.getBean(CarService.class);
        brandService = context.getBean(BrandService.class);
        modelService = context.getBean(ModelService.class);
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        context.close();
    }

    @Benchmark
    @Threads(2)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 5, time = 1)
    public void testCarFindAll() {
        carService.findAll();
    }

    @Benchmark
    @Threads(4)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 5, time = 1)
    public void testBrandFindAll() {
        brandService.findAll();
    }

    @Benchmark
    @Threads(6)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 5, time = 1)
    public void testModelFindAll() {
        modelService.findAll();
    }

    public static void main(String[] args) throws RunnerException {
        var options = new OptionsBuilder()
                // ? ServiceBenchmark class'ı içindeki tüm benchmark'ları çalıştırır.
                .include(ServiceBenchmark.class.getSimpleName())
                .resultFormat(ResultFormatType.CSV)
                .result("inventory-service-benchmark-result.csv")
                .build();

        new Runner(options).run();
    }
}