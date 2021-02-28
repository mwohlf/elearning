package net.wohlfart.elearning.configuration;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.all;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

public class CustomErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

    public CustomErrorWebExceptionHandler(
        ErrorAttributes errorAttributes,
        WebProperties.Resources resourceProperties,
        ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, applicationContext);
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return route(all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest serverRequest) {
        final Throwable throwable = (Throwable) serverRequest
            .attribute("org.springframework.boot.web.reactive.error.DefaultErrorAttributes.ERROR")
            .orElseThrow(() -> new IllegalStateException("Missing exception attribute in ServerWebExchange"));

        return ServerResponse.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(String.valueOf(throwable)), String.class);
    }

}
