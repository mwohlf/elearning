package net.wohlfart.elearning.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


/**
 * if the user clicks reload in the browser we need to deliver the index.html file
 */
@Slf4j
@Component
public class AngularRoutesFilter implements WebFilter {

    static final String API_PATH =  "/api";
    static final String SWAGGER_CONFIG_PATH =  "/v3/api-docs";

    static final String INDEX_HTML = "/index.html";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if (isAngularRoute(exchange.getRequest().getURI().getPath())) {
            return chain.filter(exchange.mutate().request(exchange.getRequest().mutate().path(INDEX_HTML).build()).build());
        }
        return chain.filter(exchange);
    }

    private boolean isAngularRoute(@NonNull String path) {
        final boolean result = !path.contains(".")
            && !path.startsWith(API_PATH)
            && !path.startsWith(SWAGGER_CONFIG_PATH);
        if (result) {
            log.debug("rerouting: '{}' to the {} resource", path, INDEX_HTML);
        }
        return result;
    }


}
