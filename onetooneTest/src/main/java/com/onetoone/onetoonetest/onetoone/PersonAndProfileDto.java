package com.onetoone.onetoonetest.onetoone;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PersonAndProfileDto {

    private Long id;

    private String name;

    private String bodyname;
}
