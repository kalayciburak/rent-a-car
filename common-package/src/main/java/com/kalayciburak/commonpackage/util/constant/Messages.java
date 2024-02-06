package com.kalayciburak.commonpackage.util.constant;

public class Messages {
    public static class Entity {
        public static final String NOT_FOUND = "Herhangi bir kayıt bulunamadı.";
        public static final String FOUND = "kayıt bulundu.";
        public static final String SAVED = "Veri başarıyla kaydedildi.";
        public static final String UPDATED = "Veri başarıyla güncellendi.";
        public static final String DELETED = "Veri başarıyla silindi.";
    }

    public static class Entities {
        public static final String NOT_FOUND = "Herhangi bir kayıt bulunamadı.";
        public static final String FOUND = "Kayıtlar listelendi.";
        public static final String SAVED = "Veriler başarıyla kaydedildi.";
        public static final String UPDATED = "Veriler başarıyla güncellendi.";
        public static final String DELETED = "Veriler başarıyla silindi.";
    }

    public static class Inventory {
        public static class Car {
            public static final String NOT_FOUND = "Herhangi bir araç bulunamadı.";
            public static final String FOUND = "Araç bulundu.";
            public static final String LISTED = "Araçlar listelendi.";
            public static final String SAVED = "Araç başarıyla kaydedildi.";
            public static final String UPDATED = "Araç başarıyla güncellendi.";
            public static final String DELETED = "Araç başarıyla silindi.";
            public static final String NOT_AVAILABLE = "Araç şu anda müsait değil.";
            public static final String NOT_AVAILABLE_TO_DELETE = "Araç silinemez, çünkü şu anda müsait değil.";
            public static final String PLATE_EXISTS = "Araç plakası zaten mevcut.";
        }

        public static class Brand {
            public static final String NOT_FOUND = "Herhangi bir marka bulunamadı.";
            public static final String FOUND = "Marka bulundu.";
            public static final String LISTED = "Markalar listelendi.";
            public static final String SAVED = "Marka başarıyla kaydedildi.";
            public static final String UPDATED = "Marka başarıyla güncellendi.";
            public static final String DELETED = "Marka başarıyla silindi.";
        }

        public static class Model {
            public static final String NOT_FOUND = "Herhangi bir model bulunamadı.";
            public static final String FOUND = "Model bulundu.";
            public static final String LISTED = "Modeller listelendi.";
            public static final String SAVED = "Model başarıyla kaydedildi.";
            public static final String UPDATED = "Model başarıyla güncellendi.";
            public static final String DELETED = "Model başarıyla silindi.";
        }

        public static class Location {
            public static final String NOT_FOUND = "Herhangi bir lokasyon bulunamadı.";
            public static final String FOUND = "Lokasyon bulundu.";
            public static final String LISTED = "Lokasyonlar listelendi.";
            public static final String SAVED = "Lokasyon başarıyla kaydedildi.";
            public static final String UPDATED = "Lokasyon başarıyla güncellendi.";
            public static final String DELETED = "Lokasyon başarıyla silindi.";
        }

        public static class City {
            public static final String NOT_FOUND = "Herhangi bir şehir bulunamadı.";
            public static final String FOUND = "Şehir bulundu.";
            public static final String LISTED = "Şehirler listelendi.";
            public static final String SAVED = "Şehir başarıyla kaydedildi.";
            public static final String UPDATED = "Şehir başarıyla güncellendi.";
            public static final String DELETED = "Şehir başarıyla silindi.";
        }
    }

    public static class Lookup {
        public static final String NOT_A_VALID_FUEL_TYPE = "Geçersiz yakıt türü.";
        public static final String NOT_A_VALID_TRANSMISSION_TYPE = "Geçersiz vites türü.";
        public static final String NOT_A_VALID_COLOR_TYPE = "Geçersiz renk türü.";
        public static final String NOT_A_VALID_CAR_STATUS = "Geçersiz araç durumu.";
    }
}