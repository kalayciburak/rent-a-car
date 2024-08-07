insert into inventorydb.lookups (id, created, deleted, is_active, updated, label, parent_id, key)
values (1, '2024-01-23 20:29:49.000000', null, true, null, 'Yakıt Türü', null, 'fuel-type'),
       (2, '2024-01-23 20:29:49.000000', null, true, null, 'Benzin', 1, 'gasoline'),
       (3, '2024-01-23 20:29:49.000000', null, true, null, 'Dizel', 1, 'diesel'),
       (4, '2024-01-23 20:29:49.000000', null, true, null, 'Elektrik', 1, 'electric'),
       (5, '2024-01-23 20:29:49.000000', null, true, null, 'Hibrit', 1, 'hybrid'),
       (6, '2024-01-23 20:29:49.000000', null, true, null, 'LPG', 1, 'lpg'),
       (7, '2024-01-23 20:29:49.000000', null, true, null, 'Vites Türü', null, 'transmission-type'),
       (8, '2024-01-23 20:29:49.000000', null, true, null, 'Otomatik', 7, 'automatic'),
       (9, '2024-01-23 20:29:49.000000', null, true, null, 'Manuel', 7, 'manual'),
       (10, '2024-01-23 20:29:49.000000', null, true, null, 'Yarı Otomatik', 7, 'semi-automatic'),
       (11, '2024-01-23 20:29:49.000000', null, true, null, 'Renk Türü', null, 'color-type'),
       (12, '2024-01-23 20:29:49.000000', null, true, null, 'Beyaz', 11, 'white'),
       (13, '2024-01-23 20:29:49.000000', null, true, null, 'Siyah', 11, 'black'),
       (14, '2024-01-23 20:29:49.000000', null, true, null, 'Kırmızı', 11, 'red'),
       (15, '2024-01-23 20:29:49.000000', null, true, null, 'Mavi', 11, 'blue'),
       (16, '2024-01-23 20:29:49.000000', null, true, null, 'Yeşil', 11, 'green'),
       (17, '2024-01-23 20:29:49.000000', null, true, null, 'Sarı', 11, 'yellow'),
       (18, '2024-01-23 20:29:49.000000', null, true, null, 'Pembe', 11, 'pink'),
       (19, '2024-01-23 20:29:49.000000', null, true, null, 'Turuncu', 11, 'orange'),
       (20, '2024-01-23 20:29:49.000000', null, true, null, 'Mor', 11, 'purple'),
       (21, '2024-01-23 20:29:49.000000', null, true, null, 'Gri', 11, 'gray'),
       (22, '2024-01-25 15:11:46.000000', null, true, null, 'Araç Durumu', null, 'filter-status'),
       (23, '2024-01-25 15:11:46.000000', null, true, null, 'Müsait', 22, 'available'),
       (24, '2024-01-25 15:11:46.000000', null, true, null, 'Kiralandı', 22, 'rented'),
       (25, '2024-01-25 15:11:46.000000', null, true, null, 'Bakımda', 22, 'under-maintenance'),
       (26, '2024-01-25 15:11:46.000000', null, true, null, 'Müsait Değil', 22, 'not-available');