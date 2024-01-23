package com.kalayciburak.inventoryservice.model.enums;

/**
 * <b>Aracın durumunu belirten enum.</b>
 * <ul>
 *  <li><i>AVAILABLE</i> : Aracın kiralanabilir olduğunu belirtir.</li>
 *  <li><i>RENTED</i> : Aracın kiralandığını belirtir.</li>
 *  <li><i>UNDER_MAINTENANCE</i> : Aracın bakımda olduğunu belirtir.</li>
 *  <li><i>NOT_AVAILABLE</i> : Aracın kiralanamaz olduğunu belirtir.</li>
 * </ul>
 */
public enum CarStatus {
    AVAILABLE, RENTED, UNDER_MAINTENANCE, NOT_AVAILABLE
}