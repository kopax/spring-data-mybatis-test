CREATE TABLE "user" (
  "id" BIGSERIAL NOT NULL,
  "username" VARCHAR(60) DEFAULT NULL,
  "password" VARCHAR(60) DEFAULT NULL,
  "first_name" VARCHAR(60) DEFAULT NULL,
  "last_name" VARCHAR(60) DEFAULT NULL,
  "middle_name" VARCHAR(60) DEFAULT NULL,
  "email" citext UNIQUE,
  "mobile" VARCHAR(32) NULL,
  "version" BIGINT DEFAULT NULL,
  "created_date" TIMESTAMP DEFAULT NULL,
  "created_by_id" BIGINT DEFAULT NULL,
  "last_modified_date" TIMESTAMP DEFAULT NULL,
  "last_modified_by_id" BIGINT DEFAULT NULL,
  "deleted" BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY ("id"),
  CONSTRAINT user_username_unique UNIQUE ("username"),
  CONSTRAINT user_mobile_unique UNIQUE ("mobile"),
  CONSTRAINT user_email_unique UNIQUE ("email")
);
