package tech.features;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
import tech.fiap.project.infra.configuration.Configuration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = { Configuration.class, ServletWebServerFactoryAutoConfiguration.class })
public class CucumberSpringConfiguration {
}