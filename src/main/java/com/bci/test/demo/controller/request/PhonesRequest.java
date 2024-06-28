package com.bci.test.demo.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhonesRequest {

    private String number;
    private String cityCode;
    private String countryCode;

}
