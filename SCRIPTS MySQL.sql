CREATE TABLE `db_example`.`stocks` (
 `id` INT NOT NULL,
 `content` VARCHAR(255) NULL,
 PRIMARY KEY (`id`));

 ALTER TABLE `db_example`.`stocks`
 CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ,
 ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC);


