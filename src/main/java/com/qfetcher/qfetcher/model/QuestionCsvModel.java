package com.qfetcher.qfetcher.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class QuestionCsvModel {
    private Long id;
    private String text;
    private String field;
}
