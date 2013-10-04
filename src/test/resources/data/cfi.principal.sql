-- 6367c48dd193d56ea7b0baad25b19455e529f5ee
insert into CF_PCPL (ID, NAME, PRINCIPAL_TYPE, LOCKED, M_ST, C_ID, C_TS) values (1, 'root', 0, true, 1, 0, CURRENT_TIMESTAMP );
insert into CF_USER (ID, REALNAME, PASSWORD, EMAIL, ACTOR_ID)
values (1, 'System Root', 'abc123', 'rafizan.baharum@gmail.com', null);

insert into CF_PCPL_ROLE (ID, ROLE_TYPE, PRINCIPAL_ID, M_ST, C_ID, C_TS) values(1, 0, 1, 1, 1, CURRENT_TIMESTAMP );
insert into CF_PCPL_ROLE (ID, ROLE_TYPE, PRINCIPAL_ID, M_ST, C_ID, C_TS) values(2, 1, 1, 1, 1, CURRENT_TIMESTAMP );

insert into CF_PCPL (ID, NAME, PRINCIPAL_TYPE, LOCKED, M_ST, C_ID, C_TS) values (2, 'GRP_ADM', 1, true, 1, 0, CURRENT_TIMESTAMP );
insert into CF_PCPL (ID, NAME, PRINCIPAL_TYPE, LOCKED, M_ST, C_ID, C_TS) values (3, 'GRP_BDH_PGW', 1, true, 1, 0, CURRENT_TIMESTAMP );
insert into CF_PCPL (ID, NAME, PRINCIPAL_TYPE, LOCKED, M_ST, C_ID, C_TS) values (4, 'GRP_BDH_KRN', 1, true, 1, 0, CURRENT_TIMESTAMP );
insert into CF_PCPL (ID, NAME, PRINCIPAL_TYPE, LOCKED, M_ST, C_ID, C_TS) values (5, 'GRP_PTJ_PGW', 1, true, 1, 0, CURRENT_TIMESTAMP );
insert into CF_PCPL (ID, NAME, PRINCIPAL_TYPE, LOCKED, M_ST, C_ID, C_TS) values (6, 'GRP_PTJ_KRN', 1, true, 1, 0, CURRENT_TIMESTAMP );
insert into CF_GROP(ID) values (2);
insert into CF_GROP(ID) values (3);
insert into CF_GROP(ID) values (4);
insert into CF_GROP(ID) values (5);
insert into CF_GROP(ID) values (6);

insert into CF_GROP_MMBR (ID, GROUP_ID, MEMBER_ID, M_ST, C_ID, C_TS) values (1, 2, 1, 1, 1, CURRENT_TIMESTAMP);
insert into CF_GROP_MMBR (ID, GROUP_ID, MEMBER_ID, M_ST, C_ID, C_TS) values (2, 3, 1, 1, 1, CURRENT_TIMESTAMP);
insert into CF_GROP_MMBR (ID, GROUP_ID, MEMBER_ID, M_ST, C_ID, C_TS) values (3, 4, 1, 1, 1, CURRENT_TIMESTAMP);
insert into CF_GROP_MMBR (ID, GROUP_ID, MEMBER_ID, M_ST, C_ID, C_TS) values (4, 5, 1, 1, 1, CURRENT_TIMESTAMP);
insert into CF_GROP_MMBR (ID, GROUP_ID, MEMBER_ID, M_ST, C_ID, C_TS) values (5, 6, 1, 1, 1, CURRENT_TIMESTAMP);



