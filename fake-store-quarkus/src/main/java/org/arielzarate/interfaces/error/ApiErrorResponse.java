package org.arielzarate.interfaces.error;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {
    private String type;
    private String title;
    private Integer status;
    private String detail;
    private String instance;
}