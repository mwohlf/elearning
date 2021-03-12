package net.wohlfart.elearning.image;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/upload")
public class UploadController {

    private final UploadService uploadService;

    @PostMapping(value = "/",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Updates the Backend for resolving Product Overview data")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Backend was successfully updates"),
            @ApiResponse(responseCode = "400", description = "Update failed")
    })
    public Mono<Object> upload(@RequestPart("fileupload") Mono<FilePart> filePartMono) {
        return uploadService.process(filePartMono.flux());

        /*
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(uploadService.process(filePartMono.flux())
                                .onErrorMap(
                                        RuntimeException.class,
                                        ex -> new ResponseStatusException(BAD_REQUEST, ex.getMessage())),
                        String.class);
                        */
    }

}
