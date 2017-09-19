CREATE TABLE "link_client_role" (
  "client_id" VARCHAR(256) NOT NULL,
  "role_id" BIGINT NOT NULL,
  CONSTRAINT client_fk FOREIGN KEY("client_id") REFERENCES "oauth_client_details"("client_id"),
  CONSTRAINT role_fk FOREIGN KEY("role_id") REFERENCES "role"("id"),
  PRIMARY KEY ("client_id", "role_id")
);
