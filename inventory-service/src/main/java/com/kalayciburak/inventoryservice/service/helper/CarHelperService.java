package com.kalayciburak.inventoryservice.service.helper;

import com.kalayciburak.inventoryservice.advice.exception.CarNotAvailableException;
import com.kalayciburak.inventoryservice.model.entity.Car;
import com.kalayciburak.inventoryservice.service.CarService;
import com.kalayciburak.inventoryservice.service.LookupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.kalayciburak.commonpackage.util.constant.Messages.Inventory.Car.NOT_AVAILABLE_TO_DELETE;

@Service
@RequiredArgsConstructor
public class CarHelperService {
    private final CarService service;
    private final LookupService lookupService;

    /**
     * <b>Verilen aracı doğrular ve siler.</b>
     *
     * @param car Silinecek araç.
     */
    public void validateAvailabilityAndDeleteCar(Car car) {
        validateCarAvailability(car);
        service.delete(car.getId());
    }

    /**
     * <b>Bir aracın kullanılabilirliğini doğrular.</b>
     * <p>
     * Kullanılabilir bir durumda olmadığında bir CarNotAvailableException fırlatır.
     *
     * @param car Doğrulanacak araç.
     * @throws CarNotAvailableException Araç kullanılabilir değilse fırlatılır.
     */
    private void validateCarAvailability(Car car) {
        if (!Objects.equals(car.getCarStatus().getId(), lookupService.getAvailableId())) {
            throw new CarNotAvailableException(NOT_AVAILABLE_TO_DELETE);
        }
    }
}
