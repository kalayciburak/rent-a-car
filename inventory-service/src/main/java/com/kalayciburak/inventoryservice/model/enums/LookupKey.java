package com.kalayciburak.inventoryservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <b>Veritanında tutulan lookup tablosunun key değerlerini tutan enum.</b>
 * <p>
 * Lookup tablosunda değişmeyecek olan tek veri <b>key</b> değerleri olduğu için enum olarak tutuldu.
 *
 * @see com.kalayciburak.inventoryservice.model.entity.Lookup
 */
@Getter
@AllArgsConstructor
public enum LookupKey {
    // ! Parent Key
    FUEL_TYPE("fuel-type"),
    // * Yakıt türleri
    GASOLINE("gasoline"),
    DIESEL("diesel"),
    ELECTRIC("electric"),
    HYBRID("hybrid"),
    LPG("lpg"),

    // ! Parent Key
    TRANSMISSION_TYPE("transmission-type"),
    // * Vites türleri
    AUTOMATIC("automatic"),
    MANUAL("manual"),
    SEMI_AUTOMATIC("semi-automatic"),

    // ! Parent Key
    COLOR_TYPE("color-type"),
    // * Renk türleri
    WHITE("white"),
    BLACK("black"),
    RED("red"),
    BLUE("blue"),
    GREEN("green"),
    YELLOW("yellow"),
    PINK("pink"),
    ORANGE("orange"),
    PURPLE("purple"),
    GRAY("gray"),

    // ! Parent Key
    CAR_STATUS("car-status"),
    // * Araç durumları
    AVAILABLE("available"),
    RENTED("rented"),
    UNDER_MAINTENANCE("under-maintenance"),
    NOT_AVAILABLE("not-available");

    private final String key;
}
