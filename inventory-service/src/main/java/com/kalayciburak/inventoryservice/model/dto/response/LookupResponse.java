package com.kalayciburak.inventoryservice.model.dto.response;

import java.util.List;

public record LookupResponse(Long id, String key, String label, List<LookupResponse> childLookups) {}
