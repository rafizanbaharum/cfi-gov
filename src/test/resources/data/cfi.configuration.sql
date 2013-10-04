-- local, development or production?
INSERT INTO FS_CONFIGURATION (ID, CONFIG_KEY, CONFIG_VALUE, DESCRIPTION, M_ST, C_ID, C_TS)
VALUES(SEQ_FS_CONFIGURATION.NEXTVAL, 'CfConfiguration.environment', 'local', 'Environment key', 1, 0, SYSDATE);

INSERT INTO FS_CONFIGURATION (ID, CONFIG_KEY, CONFIG_VALUE, DESCRIPTION, M_ST, C_ID, C_TS)
VALUES(SEQ_FS_CONFIGURATION.NEXTVAL, 'CfConfiguration.email.enable', 'false', 'Email Configuration', 1, 0, SYSDATE);

INSERT INTO FS_CONFIGURATION (ID, CONFIG_KEY, CONFIG_VALUE, DESCRIPTION, M_ST, C_ID, C_TS)
VALUES(SEQ_FS_CONFIGURATION.NEXTVAL, 'CfConfiguration.email.contact', 'rafizan.baharum@gmail.com', 'Admin contact email', 1, 0, SYSDATE);

INSERT INTO FS_CONFIGURATION (ID, CONFIG_KEY, CONFIG_VALUE, DESCRIPTION, M_ST, C_ID, C_TS)
VALUES(SEQ_FS_CONFIGURATION.NEXTVAL, 'CfConfiguration.reportServer', 'http://report/', 'Report Server URL', 1, 0, SYSDATE);
