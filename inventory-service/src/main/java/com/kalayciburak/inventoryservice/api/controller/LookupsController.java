package com.kalayciburak.inventoryservice.api.controller;

import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.inventoryservice.model.dto.response.LookupResponse;
import com.kalayciburak.inventoryservice.service.LookupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lookups")
public class LookupsController {
    private final LookupService service;

    @GetMapping("/{id}")
    @Operation(summary = "Belirli bir ID'ye göre kayıt getirir",
            description = "Verilen ID'ye sahip spesifik kaydı getirir.")
    public ResponseItem<LookupResponse> findById(
            @PathVariable @Parameter(description = "ID") Long id) {
        return service.findById(id);
    }

    @GetMapping("/key")
    @Operation(summary = "Belirli bir anahtar değerine göre kayıt getirir",
            description = "Verilen anahtar (key) değerine sahip spesifik kaydı getirir.")
    public ResponseItem<LookupResponse> findByKey(
            @RequestParam @Parameter(description = "Anahtar değer") String key) {
        return service.findByKey(key);
    }

    @GetMapping("/label")
    @Operation(summary = "Tüm aktif kayıtları getirir",
            description = "Sistemdeki tüm aktif kayıtların bir listesini getirir.")
    public ResponseItem<LookupResponse> findByLabel(
            @RequestParam @Parameter(description = "Etiket değer") String label) {
        return service.findByLabel(label);
    }

    @GetMapping("/children/parent/{parentId}")
    @Operation(summary = "Parent ID'ye göre alt elemanları getirir",
            description = "Belirli bir üst eleman (parent) ID'sine göre ilgili alt elemanların (children) listesini getirir.")
    public ResponseItem<List<LookupResponse>> findChildrenByParentId(
            @PathVariable @Parameter(description = "Üst eleman id'si") Long parentId) {
        return service.findChildrenByParentId(parentId);
    }

    @GetMapping("/children/parent/key")
    @Operation(summary = "Key değerine göre alt elemanları getirir",
            description = "Belirli bir anahtar (key) değerine göre ilgili alt elemanların listesini getirir.")
    public ResponseItem<List<LookupResponse>> findChildrenByParentKey(
            @RequestParam @Parameter(description = "Anahtar değer") String parentKey) {
        return service.findChildrenByParentKey(parentKey);
    }

    @GetMapping("/children/parent/label")
    @Operation(summary = "Label değerine göre alt elemanları getirir",
            description = "Belirli bir etiket (label) değerine göre ilgili alt elemanların listesini getirir.")
    public ResponseItem<List<LookupResponse>> findChildrenByParentLabel(
            @RequestParam @Parameter(description = "Etiket değer") String parentLabel) {
        return service.findChildrenByParentLabel(parentLabel);
    }

    @GetMapping
    @Operation(summary = "Tüm aktif kayıtları getirir",
            description = "Sistemdeki tüm aktif kayıtların bir listesini getirir.")
    public ResponseItem<List<LookupResponse>> findAll() {
        return service.findAll();
    }
}
