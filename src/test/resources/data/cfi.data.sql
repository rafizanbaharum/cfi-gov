INSERT INTO CF_FUND (ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS,D_ID,D_TS)
VALUES(SEQ_CF_FUND.NEXTVAL,'U','MENGURUS/MANAGEMENT',1, 0,SYSDATE,NULL,NULL);
INSERT INTO CF_FUND (ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS,D_ID,D_TS)
VALUES(SEQ_CF_FUND.NEXTVAL,'D','PEMBANGUNAN/DEVELOPMENT',1, 0,SYSDATE,NULL,NULL);
INSERT INTO CF_FUND (ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS,D_ID,D_TS)
VALUES(SEQ_CF_FUND.NEXTVAL,'A','TRUST/AMANAH',1, 0,SYSDATE,NULL,NULL);
INSERT INTO CF_FUND (ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS,D_ID,D_TS)
VALUES(SEQ_CF_FUND.NEXTVAL,'R','PENYELIDIKAN/RESEARCH',1, 0,SYSDATE,NULL,NULL);
INSERT INTO CF_FUND (ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS,D_ID,D_TS)
VALUES(SEQ_CF_FUND.NEXTVAL,'Q','UNIVERSITI PENYELIDIKAN/RESEARCH UNIVERSITY',1, 0,SYSDATE,NULL,NULL);


insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (1, null, 1, 'J070000', 'PUSAT TEKNOLOGI MAKLUMAT & KOMUNIKASI', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (2, null, 1, 'J270000', 'FAKULTI KEJURUTERAAN DAN SAINS GEOINFORMASI', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (3, null, 1, 'J250000', 'FAKULTI KEJURUTERAAN KIMIA & KEJURUTERAAN SUMBER ASLI', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (4, null, 1, 'J020000', 'PENDAFTAR', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (5, null, 2, 'K060000', 'PEJABAT HEP UTM INTERNATIONAL CAMPUS KL', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (6, null, 2, 'K150500', 'OVERHEAD - PEJABAT PENGARAH KERJA', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (7, null, 1, 'J260900', 'INSTITUT IBNU SINA', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (8, null, 1, 'J150200', 'OVERHEAD - PEJABAT PENDAFTAR', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (9, null, 1, 'J060000', 'PEJABAT HAL EHWAL MAHASISWA DAN ALUMNI', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (10, null, 1, 'J220000', 'FAKULTI KEJURUTERAAN AWAM', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (11, null, 1, 'J350000', 'FAKULTI BIOSAINS & BIOKEJURUTERAAN', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (12, null, 2, 'K040000', 'PERPUSTAKAAN SULTANAH ZANARIAH', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (13, null, 1, 'J230000', 'FAKULTI KEJURUTERAAN ELEKTRIK', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (14, null, 2, 'K070000', 'PUSAT TEKNOLOGI MAKLUMAT & KOMUNIKASI', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (15, null, 2, 'K012900', 'SEKOLAH PERNIAGAAN ANTARABANGSA  (IBS )', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (16, null, 2, 'K012800', 'PUSAT PERNIAGAAN TEKNOLOGI TERMAJU ( BATC )', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (17, null, 1, 'J120000', 'PEJABAT PENERBIT', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (18, null, 1, 'J160000', 'PUSAT PENGAJARAN DAN PEMBELAJARAN (CTL)', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (19, null, 2, 'K130000', 'PUSAT PENGURUSAN  PENYELIDIKAN', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (20, null, 1, 'J030000', 'BENDAHARI', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (21, null, 1, 'J251500', 'LOJI PANDU KEJURUTERAAN KIMIA (CEPP)', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (22, null, 1, 'J040000', 'PERPUSTAKAAN SULTANAH ZANARIAH', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (23, null, 1, 'J050000', 'PEJABAT HARTA BINA', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (24, null, 1, 'J210000', 'FAKULTI ALAM BINA', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (25, null, 2, 'K150200', 'OVERHEAD - PEJABAT PENDAFTAR', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (26, null, 1, 'J150300', 'OVERHEAD - PEJABAT BENDAHARI', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (27, null, 2, 'K010000', 'CANSELORI', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (28, null, 1, 'J130000', 'PUSAT PENGURUSAN  PENYELIDIKAN', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (29, null, 1, 'J100000', 'SEKOLAH PENGAJIAN SISWAZAH', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (30, null, 1, 'J140000', 'BIRO INOVASI DAN PERUNDINGAN (BIP)', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (31, null, 2, 'K140000', 'BIRO INOVASI DAN PERUNDINGAN CITY CAMPUS', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (32, null, 1, 'J360000', 'FAKULTI KEJURUTERAAN BIOPERUBATAN & SAINS KESIHATAN', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (33, null, 1, 'J150500', 'OVERHEAD - PEJABAT HARTA BINA', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (34, null, 1, 'J280000', 'FAKULTI SAINS KOMPUTER & SISTEM MAKLUMAT', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (35, null, 2, 'K150300', 'OVERHEAD - PEJABAT BENDAHARI', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (36, null, 1, 'J310000', 'FAKULTI PENDIDIKAN', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (37, null, 2, 'K150100', 'OVERHEAD - PEJABAT CANSELORI', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (38, null, 1, 'J330000', 'FAKULTI TAMADUN ISLAM', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (39, null, 2, 'K410000', 'AKADEMI BAHASA', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (40, null, 1, 'J290000', 'FAKULTI PENGURUSAN DAN PEMBANGUNAN SUMBER MANUSIA', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (41, null, 1, 'J500000', 'PUSAT TANGGUNGJAWAB UMUM UTM SKUDAI', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (42, null, 1, 'J260000', 'FAKULTI SAINS', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (43, null, 2, 'K030000', 'BENDAHARI', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (44, null, 2, 'K050000', 'PEJABAT HARTA BINA', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (45, null, 2, 'K320000', 'KOLEJ SAINS & TEKNOLOGI', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (46, null, 2, 'K020000', 'PENDAFTAR', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (47, null, 1, 'J150600', 'OVERHEAD - PEJABAT HAL EHWAL PELAJAR', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (48, null, 1, 'J010300', 'PEJABAT HAL EHWAL KORPORAT', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (49, null, 1, 'J080000', 'PEJABAT TNC (A&A)', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (50, null, 1, 'J420000', 'FAKULTI KEJURUTERAAN PETROLEUM DAN KEJURUTERAAN TENAGA DIPERBAHARUI', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (51, null, 2, 'K500000', 'PUSAT TANGGUNGJAWAB UMUM UTM INTERNATIONAL CAMPUS KUALA LUMPUR', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (52, null, 1, 'J240000', 'FAKULTI KEJURUTERAAN MEKANIKAL', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (53, null, 1, 'J010000', 'CANSELORI', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (54, null, 1, 'J090000', 'PEJABAT TNC(P&I)', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (55, null, 1, 'J410000', 'AKADEMI BAHASA', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (56, null, 2, 'K150600', 'OVERHEAD - PEJABAT HAL EHWAL PELAJAR', 1, 1, TIMESTAMP '2011-05-23 17:40:09');
insert into CF_DPMT (ID, PARENT_ID, CAMPUS_ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (57, null, 1, 'J150100', 'OVERHEAD - PEJABAT CANSELORI', 1, 1, TIMESTAMP '2011-05-23 17:40:09');