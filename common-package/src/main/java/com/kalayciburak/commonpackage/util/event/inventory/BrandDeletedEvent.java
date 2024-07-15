package com.kalayciburak.commonpackage.util.event.inventory;

import com.kalayciburak.commonpackage.util.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandDeletedEvent implements BaseEvent {
    private Long id;
}
