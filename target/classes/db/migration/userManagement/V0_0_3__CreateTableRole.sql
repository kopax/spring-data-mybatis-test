CREATE TABLE "role" (
  "id" BIGSERIAL NOT NULL,
  "name" VARCHAR(35) NOT NULL,
  "description" VARCHAR(256) DEFAULT NULL,
  "code" VARCHAR(32) DEFAULT NULL,
  "version" BIGINT DEFAULT NULL,
  "created_date" TIMESTAMP DEFAULT NULL,
  "created_by_id" BIGINT DEFAULT NULL,
  "last_modified_date" TIMESTAMP DEFAULT NULL,
  "last_modified_by_id" BIGINT DEFAULT NULL,
  "deleted" BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY ("id"),
  CONSTRAINT role_name_unique UNIQUE ("name"),
  CONSTRAINT role_code_unique UNIQUE ("code")
);

