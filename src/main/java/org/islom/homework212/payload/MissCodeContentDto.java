package org.islom.homework212.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MissCodeContentDto {

    private Integer id;

    private String content;

    private String title;

    private Integer missCodePracticeId;
}
