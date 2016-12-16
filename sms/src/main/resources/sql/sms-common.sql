CREATE TABLE common.sms_logs (
  id     BIGSERIAL PRIMARY KEY NOT NULL,
  phone  CHAR(10)              NOT NULL,
  text   TEXT                  NOT NULL,
  status TEXT                  NOT NULL
);