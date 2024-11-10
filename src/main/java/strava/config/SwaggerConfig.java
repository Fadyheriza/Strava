package strava.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Strava API")
                        .version("1.0")
                        .description("API documentation for the Strava Application"))
                .addTagsItem(new Tag().name("User Management").description("Operations related to user registration and login"))
                .addTagsItem(new Tag().name("Training Sessions").description("Operations for creating and querying training sessions"))
                .addTagsItem(new Tag().name("Challenges").description("Operations for setting up, downloading, and accepting challenges"));
    }
}
