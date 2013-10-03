insert into CF_PCPL (ID, NAME, PRINCIPAL_TYPE, LOCKED, M_ST, C_ID) values (1, 'root', 0, true, 1, 0);

-- 6367c48dd193d56ea7b0baad25b19455e529f5ee
insert into CF_USER (ID, REALNAME, PASSWORD, EMAIL, ACTOR_ID)
values (1, 'System Root', 'abc123', 'rafizan.baharum@gmail.com', null);

insert into CF_PCPL_ROLE (ID, ROLE_TYPE, PRINCIPAL_ID, M_ST, C_ID) values(1, 0, 1, 1, 1);
insert into CF_PCPL_ROLE (ID, ROLE_TYPE, PRINCIPAL_ID, M_ST, C_ID) values(1, 1, 1, 1, 1);
