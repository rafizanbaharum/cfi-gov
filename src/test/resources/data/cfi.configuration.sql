-- local, development or production?
INSERT INTO CF_CFGN (ID, CONFIG_KEY, CONFIG_VALUE, DESCRIPTION, M_ST, C_ID, C_TS)
  VALUES (nextval('seq_cf_cfgn'), 'CfConfiguration.environment', 'local', 'Environment key', 1, 0, CURRENT_TIMESTAMP);

INSERT INTO CF_CFGN (ID, CONFIG_KEY, CONFIG_VALUE, DESCRIPTION, M_ST, C_ID, C_TS)
  VALUES (nextval('seq_cf_cfgn'), 'CfConfiguration.email.enable', 'false', 'Email Configuration', 1, 0, CURRENT_TIMESTAMP);

INSERT INTO CF_CFGN (ID, CONFIG_KEY, CONFIG_VALUE, DESCRIPTION, M_ST, C_ID, C_TS)
  VALUES (nextval('seq_cf_cfgn'), 'CfConfiguration.email.contact', 'rafizan.baharum@gmail.com', 'Admin contact email', 1, 0, CURRENT_TIMESTAMP);

INSERT INTO CF_CFGN (ID, CONFIG_KEY, CONFIG_VALUE, DESCRIPTION, M_ST, C_ID, C_TS)
  VALUES (nextval('seq_cf_cfgn'), 'CfConfiguration.reportServer', 'http://report/', 'Report Server URL', 1, 0, CURRENT_TIMESTAMP);
