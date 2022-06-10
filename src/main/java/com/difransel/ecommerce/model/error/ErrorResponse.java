package com.difransel.ecommerce.model.error;



import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private String exception;
    private Long timestamp;

    @Builder.Default
    private List<String> errors = new ArrayList<>();

    @Builder.Default
    private Map<String, List<String>> fieldErrors = new HashMap<>();

}
