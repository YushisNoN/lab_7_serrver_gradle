package org.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import utils.kernel.Kernel;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
/*        if (args.length == 1) {
            try {
                FileReader.setFileName(args[0]);
            } catch (FileNotFoundException exception) {
                System.out.println(exception.getMessage());
            }
        }*/
        SpringApplication.run(Kernel.class, args);
    }
}