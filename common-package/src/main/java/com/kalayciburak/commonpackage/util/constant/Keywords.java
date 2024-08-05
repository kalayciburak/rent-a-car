package com.kalayciburak.commonpackage.util.constant;

import java.util.ArrayList;
import java.util.List;

import static com.kalayciburak.commonpackage.util.enums.Languages.EN_CREATION;
import static com.kalayciburak.commonpackage.util.enums.Languages.TR_CREATION;

public class Keywords {
    public static final List<String> creationKeywords = new ArrayList<>();

    static {
        creationKeywords.addAll(TR_CREATION.getKeywords());
        creationKeywords.addAll(EN_CREATION.getKeywords());
    }
}