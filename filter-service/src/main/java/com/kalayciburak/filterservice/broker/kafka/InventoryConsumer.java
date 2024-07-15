package com.kalayciburak.filterservice.broker.kafka;

import com.kalayciburak.commonpackage.util.event.inventory.*;
import com.kalayciburak.filterservice.model.dto.response.CarResponse;
import com.kalayciburak.filterservice.service.CarService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryConsumer {
    private static final Logger log = LoggerFactory.getLogger(InventoryConsumer.class);
    private final CarService filterService;

    @KafkaListener(topics = "car.created", groupId = "inventory-consumer")
    public void consume(CarCreatedEvent event) {
        filterService.onCarCreated(event);
        logCarDetails(event.getCarId(), "car.created");
    }

    @KafkaListener(topics = "car.updated", groupId = "inventory-consumer")
    public void consume(CarUpdatedEvent event) {
        filterService.onCarUpdated(event);
        logCarDetails(event.getCarId(), "car.updated");
    }

    @KafkaListener(topics = "car.deleted", groupId = "inventory-consumer")
    public void consume(CarDeletedEvent event) {
        filterService.onCarDeleted(event);
        log.info("car.deleted event: Car ID={}", event.getId());
    }

    @KafkaListener(topics = "brand.updated", groupId = "inventory-consumer")
    public void consume(BrandUpdatedEvent event) {
        filterService.onBrandUpdated(event);
        log.info("brand.updated event: Brand ID={}", event.getId());
    }

    @KafkaListener(topics = "brand.deleted", groupId = "inventory-consumer")
    public void consume(BrandDeletedEvent event) {
        filterService.onBrandDeleted(event);
        log.info("brand.deleted event: Brand ID={}", event.getId());
    }
    
    private void logCarDetails(Long carId, String eventType) {
        var car = filterService.findByCarId(carId);
        var message = getMessage(eventType, car.getData());
        log.info(message);
    }

    private static String getMessage(String eventType, CarResponse response) {
        var carId = response.carId();
        var model = response.modelName();
        var brand = response.brandName();

        return String.format("%s event: Car ID=%d, Model=%s, Brand=%s", eventType, carId, model, brand);
    }
}
