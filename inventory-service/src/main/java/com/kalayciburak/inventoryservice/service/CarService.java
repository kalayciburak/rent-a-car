package com.kalayciburak.inventoryservice.service;

import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.inventoryservice.model.dto.response.CarResponse;

import java.util.List;

public interface CarService {
    ResponseItem<List<CarResponse>> findAll();
}
