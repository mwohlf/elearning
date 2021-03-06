package net.wohlfart.elearning.config;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class AngularRoutesFilter implements WebFilter {

    static final String API_PATH = "/api";

    static final String INDEX_HTML = "/index.html";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if (isAngularRoute(exchange.getRequest().getURI().getPath())) {
            return chain.filter(exchange.mutate().request(exchange.getRequest().mutate().path(INDEX_HTML).build()).build());
        }
        return chain.filter(exchange);
    }

    private boolean isAngularRoute(@NonNull  String path) {
        return !path.startsWith(API_PATH) && path.matches("[^\\\\.]*");
    }


}
