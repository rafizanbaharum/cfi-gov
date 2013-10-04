insert into CF_MODL(ID, CODE, DESCRIPTION, ORDR, M_ST, C_ID, C_TS) values (1,'SC', 'System Configuration', 1, 1, 0, CURRENT_TIMESTAMP);
insert into CF_MODL(ID, CODE, DESCRIPTION, ORDR, M_ST, C_ID, C_TS) values (2,'DD', 'Data Dictionary', 2, 1, 0, CURRENT_TIMESTAMP);
insert into CF_MODL(ID, CODE, DESCRIPTION, ORDR, M_ST, C_ID, C_TS) values (3,'AM', 'Access Management', 3, 1, 0, CURRENT_TIMESTAMP);
insert into CF_MODL(ID, CODE, DESCRIPTION, ORDR, M_ST, C_ID, C_TS) values (4,'GL', 'General Ledger', 4, 1, 0, CURRENT_TIMESTAMP);
insert into CF_MODL(ID, CODE, DESCRIPTION, ORDR, M_ST, C_ID, C_TS) values (5,'VM', 'Vot Management', 5, 1, 0, CURRENT_TIMESTAMP);
insert into CF_MODL(ID, CODE, DESCRIPTION, ORDR, M_ST, C_ID, C_TS) values (6,'BM', 'Budget Management', 6, 1, 0, CURRENT_TIMESTAMP);
insert into CF_MODL(ID, CODE, DESCRIPTION, ORDR, M_ST, C_ID, C_TS) values (7,'AP', 'Account Payable', 7, 1, 0, CURRENT_TIMESTAMP);
insert into CF_MODL(ID, CODE, DESCRIPTION, ORDR, M_ST, C_ID, C_TS) values (8,'AR', 'Account Receivable', 8, 1, 0, CURRENT_TIMESTAMP);
insert into CF_MODL(ID, CODE, DESCRIPTION, ORDR, M_ST, C_ID, C_TS) values (9,'JM', 'Journal Management', 9, 1, 0, CURRENT_TIMESTAMP);


insert into CF_SMDL (ID, MODULE_ID, CODE, DESCRIPTION, ORDR, M_ST, C_ID, C_TS) values (1, 5, '1', 'Distribution', 1, 1, 1, CURRENT_TIMESTAMP);
insert into CF_SMDL (ID, MODULE_ID, CODE, DESCRIPTION, ORDR, M_ST, C_ID, C_TS) values (2, 5, '2', 'Addition', 2, 1, 1, CURRENT_TIMESTAMP);
insert into CF_SMDL (ID, MODULE_ID, CODE, DESCRIPTION, ORDR, M_ST, C_ID, C_TS) values (3, 7, '3', 'Bill Invoice', 3, 1, 1, CURRENT_TIMESTAMP);
insert into CF_SMDL (ID, MODULE_ID, CODE, DESCRIPTION, ORDR, M_ST, C_ID, C_TS) values (4, 7, '4', 'Claim', 4, 1, 1, CURRENT_TIMESTAMP);
insert into CF_SMDL (ID, MODULE_ID, CODE, DESCRIPTION, ORDR, M_ST, C_ID, C_TS) values (5, 7, '5', 'Referral', 5, 1, 1, CURRENT_TIMESTAMP);
insert into CF_SMDL (ID, MODULE_ID, CODE, DESCRIPTION, ORDR, M_ST, C_ID, C_TS) values (6, 8, '6', 'Counter Receipt', 1, 1, 1, CURRENT_TIMESTAMP);
insert into CF_SMDL (ID, MODULE_ID, CODE, DESCRIPTION, ORDR, M_ST, C_ID, C_TS) values (7, 9, '7', 'Manual Journal', 2, 1, 1, CURRENT_TIMESTAMP);
