package com.kalayciburak.inventoryservice.service.helper;

import com.kalayciburak.inventoryservice.advice.exception.LookupNotFoundException;
import com.kalayciburak.inventoryservice.advice.exception.car.CarNotAvailableException;
import com.kalayciburak.inventoryservice.advice.exception.car.CarNotFoundException;
import com.kalayciburak.inventoryservice.model.entity.Car;
import com.kalayciburak.inventoryservice.repository.CarRepository;
import com.kalayciburak.inventoryservice.repository.LookupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.kalayciburak.commonpackage.util.constant.Messages.Entity.NOT_FOUND;
import static com.kalayciburak.commonpackage.util.constant.Messages.Inventory.Car.NOT_AVAILABLE_TO_DELETE;
import static com.kalayciburak.inventoryservice.model.enums.LookupKey.AVAILABLE;

@Service
@RequiredArgsConstructor
public class CarHelperService {
    private final CarRepository carRepository;
    private final LookupRepository lookupRepository;

    /**
     * <b>Verilen aracı doğrular ve siler.</b>
     *
     * @param car Silinecek araç.
     */
    public void validateAvailabilityAndDeleteCar(Car car) {
        validateCarAvailability(car);
        carRepository.findById(car.getId()).orElseThrow(() -> new CarNotFoundException(NOT_FOUND));
        carRepository.softDeleteById(car.getId());
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
        if (!Objects.equals(car.getCarStatus().getId(), getAvailableCarStatusId())) {
            throw new CarNotAvailableException(NOT_AVAILABLE_TO_DELETE);
        }
    }

    /**
     * <b>Kullanılabilir araç durumu ID'sini döndürür.</b>
     *
     * @return Kullanılabilir araç durumu ID'si.
     * @throws LookupNotFoundException Kullanılabilir araç durumu bulunamazsa fırlatılır.
     */
    private Long getAvailableCarStatusId() {
        return lookupRepository.findByKey(AVAILABLE.getKey())
                .orElseThrow(() -> new LookupNotFoundException(NOT_FOUND)).getId();
    }
}
