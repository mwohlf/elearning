package net.wohlfart.elearning.quiz;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Locale;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizItemDto {

    @NotNull
    @Size(max = 255)
    String id;

    @NotNull
    HashMap<Locale, String> labels;




}
