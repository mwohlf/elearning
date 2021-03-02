package net.wohlfart.elearning.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class WebFluxConfig {

    /*
    @Bean
    public RouterFunction<ServerResponse> indexRouter(@Value("classpath:/META-INF/resources/index.html") final Resource indexHtml) {
        return route(GET("/**"), request -> ok().contentType(MediaType.TEXT_HTML).bodyValue(indexHtml));
    }
    */


    @Bean
    public WebFluxConfigurer webConfigurer() {

        return new WebFluxConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**")
                        .addResourceLocations("classpath:/META-INF/resources/")
                        .resourceChain(false);

                // TODO: this doesn't work:
                registry.addResourceHandler("/start")
                    .addResourceLocations("classpath:/META-INF/resources/index.html")
                    .resourceChain(false);
            }
        };

    }


}
