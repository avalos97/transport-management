package com.ingenio.transportmanagementservice.component.util;

import java.math.BigDecimal;

public class Constants {

    private Constants() {
    }

    public static final String API_URL_PREFIX = "/api/administration/";
    public static final String MEDIA_TYPE = "application/json";

    public static final String GENERIC_RECORD_NOT_FOUND_MESSAGE = "Registro no encontrado con ID ";
    public static final String SUCCESSFULLY_MESSAGE = "Operacion realizada con éxito";

    public static final BigDecimal PERCENTAGE_0_05 = BigDecimal.valueOf(0.05);
    public static final BigDecimal PERCENTAGE_0_03 = BigDecimal.valueOf(0.03);



}
