package pl.lodz.p.pstrachota.auctions_spring_boot_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AuctionsSpringBootProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuctionsSpringBootProjectApplication.class, args);
    }

}
