ALTER TABLE "order"
  DROP CONSTRAINT IF EXISTS "order_fk0";
ALTER TABLE "order"
  DROP CONSTRAINT IF EXISTS "order_fk1";

ALTER TABLE "user"
  DROP CONSTRAINT IF EXISTS "user_fk0";
ALTER TABLE "user"
  DROP CONSTRAINT IF EXISTS "user_fk1";
ALTER TABLE "user"
  DROP CONSTRAINT IF EXISTS "user_fk3";

ALTER TABLE "order_items"
  DROP CONSTRAINT IF EXISTS "order_items_fk0";
ALTER TABLE "order_items"
  DROP CONSTRAINT IF EXISTS "order_items_fk1";

DROP TABLE "order_items";

DROP TABLE "order_status";

DROP TABLE "locale";

DROP TABLE "user_role";

DROP TABLE "gender";

DROP TABLE "user";

DROP TABLE "order";

DROP TABLE "book";

