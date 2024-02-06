package com.kalayciburak.inventoryservice.service;

import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.commonpackage.util.constant.Messages;
import com.kalayciburak.inventoryservice.advice.exception.LookupNotFoundException;
import com.kalayciburak.inventoryservice.model.dto.response.basic.LookupResponse;
import com.kalayciburak.inventoryservice.model.entity.Lookup;
import com.kalayciburak.inventoryservice.repository.LookupRepository;
import com.kalayciburak.inventoryservice.util.mapper.LookupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kalayciburak.commonpackage.model.response.GenericResponse.createNotFoundResponse;
import static com.kalayciburak.commonpackage.model.response.GenericResponse.createSuccessResponse;
import static com.kalayciburak.commonpackage.util.constant.Messages.Entities.FOUND;
import static com.kalayciburak.commonpackage.util.constant.Messages.Entity.NOT_FOUND;
import static com.kalayciburak.commonpackage.util.constant.Messages.Lookup.*;
import static com.kalayciburak.inventoryservice.model.enums.LookupKey.*;

@Service
@RequiredArgsConstructor
public class LookupService {
    private final LookupMapper mapper;
    private final LookupRepository repository;

    /**
     * <b>Verilen ID'ye göre Lookup nesnesini bulur ve döndürür.</b>
     *
     * @param id Aranan Lookup nesnesinin ID'si.
     * @return LookupResponse içeren ResponseItem nesnesi.
     */
    public ResponseItem<LookupResponse> findById(Long id) {
        var lookup = findByIdOrThrow(id);
        var data = mapper.toDto(lookup);

        return createSuccessResponse(data, Messages.Entity.FOUND);
    }

    /**
     * <b>Verilen 'key' değerine göre Lookup nesnesini bulur ve döndürür.</b>
     *
     * @param key Aranan Lookup nesnesinin 'key' değeri.
     * @return LookupResponse içeren ResponseItem nesnesi.
     */
    public ResponseItem<LookupResponse> findByKey(String key) {
        var lookup = repository.findByKey(key).orElseThrow(() -> new LookupNotFoundException(NOT_FOUND));
        var data = mapper.toDto(lookup);

        return createSuccessResponse(data, Messages.Entity.FOUND);
    }

    /**
     * <b>Verilen 'label' değerine göre Lookup nesnesini bulur ve döndürür.</b>
     *
     * @param label Aranan Lookup nesnesinin etiket değeri.
     * @return LookupResponse içeren ResponseItem nesnesi.
     */
    public ResponseItem<LookupResponse> findByLabel(String label) {
        var lookup = repository.findByLabel(label).orElseThrow(() -> new LookupNotFoundException(NOT_FOUND));
        var data = mapper.toDto(lookup);

        return createSuccessResponse(data, Messages.Entity.FOUND);
    }

    /**
     * <b>Belirtilen 'parent' ID'sine sahip Lookup nesnelerinin 'children' listesini döndürür.</b>
     *
     * @param parentId Parent ID değeri.
     * @return LookupResponse listesi içeren ResponseItem nesnesi.
     */
    public ResponseItem<List<LookupResponse>> findChildrenByParentId(Long parentId) {
        var lookups = repository.findChildrenByParentId(parentId);
        if (lookups.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var data = lookups.stream().map(mapper::toDto).toList();

        return createSuccessResponse(data, FOUND);
    }

    /**
     * <b>Belirtilen 'parent' 'key' değerine sahip Lookup nesnelerinin 'children' listesini döndürür.</b>
     *
     * @param parentKey Parent 'key' değeri.
     * @return LookupResponse listesi içeren ResponseItem nesnesi.
     */
    public ResponseItem<List<LookupResponse>> findChildrenByParentKey(String parentKey) {
        var lookups = repository.findChildrenByParentKey(parentKey);
        if (lookups.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var data = lookups.stream().map(mapper::toDto).toList();

        return createSuccessResponse(data, FOUND);
    }

    /**
     * <b>Belirtilen 'parent' 'label' değerine sahip Lookup nesnelerinin 'children' listesini döndürür.</b>
     *
     * @param parentLabel Parent 'label' değeri.
     * @return LookupResponse listesi içeren ResponseItem nesnesi.
     */
    public ResponseItem<List<LookupResponse>> findChildrenByParentLabel(String parentLabel) {
        var lookups = repository.findChildrenByParentLabel(parentLabel);
        if (lookups.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var data = lookups.stream().map(mapper::toDto).toList();

        return createSuccessResponse(data, FOUND);
    }

    /**
     * <b>Tüm Lookup nesnelerini döndürür.</b>
     *
     * @return LookupResponse listesi içeren ResponseItem nesnesi.
     */
    public ResponseItem<List<LookupResponse>> findAll() {
        var lookups = repository.findAll();
        if (lookups.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var data = lookups.stream().map(mapper::toDto).toList();

        return createSuccessResponse(data, FOUND);
    }

    /**
     * <b>Verilen ID'ye sahip Lookup nesnesini bulur.</b>
     *
     * @param id Aranan Lookup nesnesinin ID'si.
     * @return Bulunan Lookup nesnesi.
     */
    public Lookup findEntityById(Long id) {
        return findByIdOrThrow(id);
    }

    /**
     * <b>Yakıt türünün geçerli olup olmadığını kontrol eder.</b>
     * <p>
     * Bu method, parametre olarak verilen ID'nin geçerli bir yakıt türü olup olmadığını kontrol eder.
     *
     * @param id Aranan Lookup nesnesinin ID'si.
     * @throws IllegalArgumentException Eğer bulunamazsa fırlatılır.
     */
    public void validateFuelTypeId(Long id) {
        var fuelTypes = findChildrenByParentKey(FUEL_TYPE.getKey());
        var isValid = fuelTypes.getData().stream().anyMatch(fuelType -> fuelType.id().equals(id));
        if (!isValid) throw new IllegalArgumentException(NOT_A_VALID_FUEL_TYPE);
    }

    /**
     * <b>Vites türünün geçerli olup olmadığını kontrol eder.</b>
     * <p>
     * Bu method, parametre olarak verilen ID'nin geçerli bir vites türü olup olmadığını kontrol eder.
     *
     * @param id Aranan Lookup nesnesinin ID'si.
     * @throws IllegalArgumentException Eğer bulunamazsa fırlatılır.
     */
    public void validateTransmissionTypeId(Long id) {
        var transmissionTypes = findChildrenByParentKey(TRANSMISSION_TYPE.getKey());
        var isValid = transmissionTypes.getData().stream().anyMatch(transmissionType -> transmissionType.id().equals(id));
        if (!isValid) throw new IllegalArgumentException(NOT_A_VALID_TRANSMISSION_TYPE);
    }

    /**
     * <b>Renk türünün geçerli olup olmadığını kontrol eder.</b>
     * <p>
     * Bu method, parametre olarak verilen ID'nin geçerli bir renk türü olup olmadığını kontrol eder.
     *
     * @param id Aranan Lookup nesnesinin ID'si.
     * @throws IllegalArgumentException Eğer bulunamazsa fırlatılır.
     */
    public void validateColorTypeId(Long id) {
        var colorTypes = findChildrenByParentKey(COLOR_TYPE.getKey());
        var isValid = colorTypes.getData().stream().anyMatch(colorType -> colorType.id().equals(id));
        if (!isValid) throw new IllegalArgumentException(NOT_A_VALID_COLOR_TYPE);
    }

    /**
     * <b>Araç durumunun geçerli olup olmadığını kontrol eder.</b>
     * <p>
     * Bu method, parametre olarak verilen ID'nin geçerli bir araç durumu olup olmadığını kontrol eder.
     *
     * @param id Aranan Lookup nesnesinin ID'si.
     * @throws IllegalArgumentException Eğer bulunamazsa fırlatılır.
     */
    public void validateCarStatusId(Long id) {
        var carStatuses = findChildrenByParentKey(CAR_STATUS.getKey());
        var isValid = carStatuses.getData().stream().anyMatch(carStatus -> carStatus.id().equals(id));
        if (!isValid) throw new IllegalArgumentException(NOT_A_VALID_CAR_STATUS);
    }

    // ? Yakıt Türleri

    /**
     * <b>Benzin 'key' değerine sahip yakıt türünün id değerini döndürür.</b>
     *
     * @return Benzin yakıt türünün id değeri.
     */
    public Long getGasolineId() {
        return findByKey(GASOLINE.getKey()).getData().id();
    }

    /**
     * <b>Dizel 'key' değerine sahip yakıt türünün id değerini döndürür.</b>
     *
     * @return Dizel yakıt türünün id değeri.
     */
    public Long getDieselId() {
        return findByKey(DIESEL.getKey()).getData().id();
    }

    /**
     * <b>Elektrik 'key' değerine sahip yakıt türünün id değerini döndürür.</b>
     *
     * @return Elektrik yakıt türünün id değeri.
     */
    public Long getElectricId() {
        return findByLabel(ELECTRIC.getKey()).getData().id();
    }

    /**
     * <b>Hibrit 'key' değerine sahip yakıt türünün id değerini döndürür.</b>
     *
     * @return Hibrit yakıt türünün id değeri.
     */
    public Long getHybridId() {
        return findByLabel(HYBRID.getKey()).getData().id();
    }

    /**
     * <b>LPG 'key' değerine sahip yakıt türünün id değerini döndürür.</b>
     *
     * @return LPG yakıt türünün id değeri.
     */
    public Long getLpgId() {
        return findByLabel(LPG.getKey()).getData().id();
    }

    // ? Vites Türleri

    /**
     * <b>Otomatik 'key' değerine sahip vites türünün id değerini döndürür.</b>
     *
     * @return Otomatik vites türünün id değeri.
     */
    public Long getAutomaticId() {
        return findByKey(AUTOMATIC.getKey()).getData().id();
    }

    /**
     * <b>Manuel 'key' değerine sahip vites türünün id değerini döndürür.</b>
     *
     * @return Manuel vites türünün id değeri.
     */
    public Long getManualId() {
        return findByKey(MANUAL.getKey()).getData().id();
    }

    /**
     * <b>Yarı Otomatik 'key' değerine sahip vites türünün id değerini döndürür.</b>
     *
     * @return Yarı Otomatik vites türünün id değeri.
     */
    public Long getSemiAutomaticId() {
        return findByKey(SEMI_AUTOMATIC.getKey()).getData().id();
    }

    // ? Renk Türleri

    /**
     * <b>Beyaz 'key' değerine sahip renk türünün id değerini döndürür.</b>
     *
     * @return Beyaz renk türünün id değeri.
     */
    public Long getWhiteId() {
        return findByKey(WHITE.getKey()).getData().id();
    }

    /**
     * <b>Siyah 'key' değerine sahip renk türünün id değerini döndürür.</b>
     *
     * @return Siyah renk türünün id değeri.
     */
    public Long getBlackId() {
        return findByKey(BLACK.getKey()).getData().id();
    }

    /**
     * <b>Kırmızı 'key' değerine sahip renk türünün id değerini döndürür.</b>
     *
     * @return Kırmızı renk türünün id değeri.
     */
    public Long getRedId() {
        return findByKey(RED.getKey()).getData().id();
    }

    /**
     * <b>Mavi 'key' değerine sahip renk türünün id değerini döndürür.</b>
     *
     * @return Mavi renk türünün id değeri.
     */
    public Long getBlueId() {
        return findByKey(BLUE.getKey()).getData().id();
    }

    /**
     * <b>Yeşil 'key' değerine sahip renk türünün id değerini döndürür.</b>
     *
     * @return Yeşil renk türünün id değeri.
     */
    public Long getGreenId() {
        return findByKey(GREEN.getKey()).getData().id();
    }

    /**
     * <b>Sarı 'key' değerine sahip renk türünün id değerini döndürür.</b>
     *
     * @return Sarı renk türünün id değeri.
     */
    public Long getYellowId() {
        return findByKey(YELLOW.getKey()).getData().id();
    }

    /**
     * <b>Pembe 'key' değerine sahip renk türünün id değerini döndürür.</b>
     *
     * @return Pembe renk türünün id değeri.
     */
    public Long getPinkId() {
        return findByKey(PINK.getKey()).getData().id();
    }

    /**
     * <b>Turuncu 'key' değerine sahip renk türünün id değerini döndürür.</b>
     *
     * @return Turuncu renk türünün id değeri.
     */
    public Long getOrangeId() {
        return findByKey(ORANGE.getKey()).getData().id();
    }

    /**
     * <b>Mor 'key' değerine sahip renk türünün id değerini döndürür.</b>
     *
     * @return Mor renk türünün id değeri.
     */
    public Long getPurpleId() {
        return findByKey(PURPLE.getKey()).getData().id();
    }

    /**
     * <b>Gri 'key' değerine sahip renk türünün id değerini döndürür.</b>
     *
     * @return Gri renk türünün id değeri.
     */
    public Long getGrayId() {
        return findByKey(GRAY.getKey()).getData().id();
    }

    // ? Araç Durumları

    /**
     * <b>Müsait 'key' değerine sahip araç durumunun id değerini döndürür.</b>
     *
     * @return Müsait araç durumunun id değeri.
     */
    public Long getAvailableId() {
        return findByKey(AVAILABLE.getKey()).getData().id();
    }

    /**
     * <b>Kiralandı 'key' değerine sahip araç durumunun id değerini döndürür.</b>
     *
     * @return Kiralandı araç durumunun id değeri.
     */
    public Long getRentedId() {
        return findByKey(RENTED.getKey()).getData().id();
    }

    /**
     * <b>Bakımda 'key' değerine sahip araç durumunun id değerini döndürür.</b>
     *
     * @return Bakımda araç durumunun id değeri.
     */
    public Long getUnderMaintenanceId() {
        return findByKey(UNDER_MAINTENANCE.getKey()).getData().id();
    }

    /**
     * <b>Müsait Değil 'key' değerine sahip araç durumunun id değerini döndürür.</b>
     *
     * @return Müsait Değil araç durumunun id değeri.
     */
    public Long getNotAvailableId() {
        return findByKey(NOT_AVAILABLE.getKey()).getData().id();
    }

    /**
     * <b>Belirtilen ID'ye sahip bir Lookup nesnesi bulur.</b>
     * <p>
     * Eğer bulunamazsa LookupNotFoundException fırlatır.
     *
     * @param id Aranan Lookup nesnesinin ID'si.
     * @return Bulunan Lookup nesnesi.
     * @throws LookupNotFoundException Eğer Lookup nesnesi bulunamazsa fırlatılır.
     */
    private Lookup findByIdOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new LookupNotFoundException(NOT_FOUND));
    }
}
