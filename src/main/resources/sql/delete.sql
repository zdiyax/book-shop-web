
ALTER TABLE "book" ADD CONSTRAINT "book_fk0" FOREIGN KEY ("authorId") REFERENCES "author"("authorId");
ALTER TABLE "book" ADD CONSTRAINT "book_fk1" FOREIGN KEY ("domainId") REFERENCES "domain"("domainId");
ALTER TABLE "book" ADD CONSTRAINT "book_fk2" FOREIGN KEY ("publisherId") REFERENCES "publisher"("publisherId");
ALTER TABLE "language" ADD CONSTRAINT "language_fk0" FOREIGN KEY ("languageId") REFERENCES "book"("languageId");
ALTER TABLE "order" ADD CONSTRAINT "order_fk0" FOREIGN KEY ("shippingAddressId") REFERENCES "shippingAddress"("shippingAddressId");
ALTER TABLE "user" ADD CONSTRAINT "user_fk0" FOREIGN KEY ("userInfoId") REFERENCES "userInfo"("userInfoId");
ALTER TABLE "user" ADD CONSTRAINT "user_fk1" FOREIGN KEY ("shippingAddressId") REFERENCES "shippingAddress"("shippingAddressId");
ALTER TABLE "userInfo" ADD CONSTRAINT "userInfo_fk0" FOREIGN KEY ("genderId") REFERENCES "gender"("genderId");


