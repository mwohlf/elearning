package net.wohlfart.elearning.image;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Slf4j
@Service
public class UploadService {

    public Mono<Object> process(Flux<FilePart> filePartFlux) {
        //noinspection Convert2MethodRef
        return filePartFlux
                .flatMap(filePart -> filePart.content())
                // writing buffers into dataBuffer
                .reduce((dataBuffer, buffers) -> {
                    //noinspection Convert2MethodRef
                    return dataBuffer.write(buffers);
                })
                .flatMap(dataBuffer -> {
                    System.out.println("test");
                    // parsing the uploaded data
                    try (InputStream input = dataBuffer.asInputStream()) {
                        // TODO: store the file somewhere...
                        return Mono.empty();
                    } catch (Exception ex) {
                        return Mono.error(ex);
                    } finally {
                        DataBufferUtils.release(dataBuffer);
                    }
                });
    }


}
