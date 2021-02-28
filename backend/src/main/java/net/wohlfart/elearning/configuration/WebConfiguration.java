package net.wohlfart.elearning.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class WebConfiguration {

    @Bean
    public WebFluxConfigurer webConfigurer() {
        return new WebFluxConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**")
                        .addResourceLocations("classpath:/META-INF/resources/elearning/")
                        .resourceChain(false);
            }
        };
    }

}
