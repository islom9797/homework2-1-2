package org.islom.homework212.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HelpsDto {


    private Integer id;

    private String title;

    private Integer programmingLanguageId;
}
