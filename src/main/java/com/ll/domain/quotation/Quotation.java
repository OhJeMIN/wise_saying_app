package com.ll.domain.quotation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Quotation {
    @Getter
    private int id;
    @Getter
    @Setter
    private String content;
    @Getter
    @Setter
    private String authorName;

}
