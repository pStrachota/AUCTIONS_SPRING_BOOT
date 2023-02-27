package pl.lodz.p.pstrachota.auctions_spring_boot_project.controller;

import jakarta.transaction.Transactional;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Testcontainers
@Sql("/data-test.sql")
public class BaseTest {

    @Container
    private static final MSSQLServerContainer<?> MSSQL_SERVER_CONTAINER =
            new MSSQLServerContainer<>("mcr.microsoft.com/mssql/server:2022-latest")
                    .withEnv("ACCEPT_EULA", "Y")
                    .withPassword("9R%49Zf41Ov2GS");

    @DynamicPropertySource
    public static void containerConfig(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MSSQL_SERVER_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MSSQL_SERVER_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MSSQL_SERVER_CONTAINER::getPassword);
    }
}
