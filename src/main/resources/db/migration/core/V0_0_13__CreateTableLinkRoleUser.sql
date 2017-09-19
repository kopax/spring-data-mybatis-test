CREATE TABLE "link_role_user" (
  "user_id" BIGINT NOT NULL,
  "role_id" BIGINT NOT NULL,
  CONSTRAINT user_fk FOREIGN KEY("user_id") REFERENCES "user"("id"),
  CONSTRAINT role_fk FOREIGN KEY("role_id") REFERENCES "role"("id"),
  PRIMARY KEY ("user_id", "role_id")
);
