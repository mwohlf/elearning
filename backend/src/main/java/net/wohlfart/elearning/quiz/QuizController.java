package net.wohlfart.elearning.quiz;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quiz")
public class QuizController {

    @GetMapping(value = "/",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Read the initial quiz data structure.")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<QuizNodeDto> readQuizTree(ServerHttpRequest request) {
        System.out.println("path: " + request.getPath().contextPath());
        return Mono.empty();
    }

}
