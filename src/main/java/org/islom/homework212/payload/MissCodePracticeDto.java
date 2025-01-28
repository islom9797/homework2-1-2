package org.islom.homework212.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MissCodePracticeDto {

    private Integer id;

    private String name;

    private Integer programmingLanguageId;
}
