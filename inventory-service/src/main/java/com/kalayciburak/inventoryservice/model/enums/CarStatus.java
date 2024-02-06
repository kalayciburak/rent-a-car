package com.kalayciburak.inventoryservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <b>Araç durumlarını tutan enum.</b>
 * <p>
 * Bu enum swagger'da kullanılmak üzere oluşturuldu.
 * Lookup tablosunda ki <b>car-status</b> key değerlerini tutar. Bu sayede filtrelemelerde kullanılabilir.
 *
 * @see LookupKey
 */
@Getter
@AllArgsConstructor
public enum CarStatus {
    MUSAIT(LookupKey.AVAILABLE.getKey()),
    KIRALANDI(LookupKey.RENTED.getKey()),
    BAKIMDA(LookupKey.UNDER_MAINTENANCE.getKey()),
    MUSAIT_DEGIL(LookupKey.NOT_AVAILABLE.getKey());

    private final String status;
}
