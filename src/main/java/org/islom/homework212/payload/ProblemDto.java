package org.islom.homework212.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProblemDto {
    private Integer id;

    private String name;

    private String content;

    private Integer categoryId;
}
