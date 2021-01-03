CREATE TABLE IF NOT EXISTS `companies` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(32) NOT NULL COLLATE `utf8_general_ci`
);

CREATE TABLE IF NOT EXISTS `pollutants` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(32) NOT NULL COLLATE `utf8_general_ci`
);

CREATE TABLE IF NOT EXISTS `targets` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(32) NOT NULL COLLATE `utf8_general_ci`,
    `distance` DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS `discharges` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(32) NOT NULL COLLATE `utf8_general_ci`,
    `date` DATETIME NOT NULL,
    `company` INT NOT NULL,
    `diameter` DOUBLE NOT NULL,
    `minimal_water_speed` DOUBLE NOT NULL,
    `wastewater_consumption` DOUBLE NOT NULL,
    `angle` DOUBLE NOT NULL,
    `distance_to_surface` DOUBLE NOT NULL,
    `distance_to_shore` DOUBLE NOT NULL,
    `used_target` INT NOT NULL,

    INDEX `company_idx` (`company`),
    INDEX `used_target_idx` (`used_target`),
    FOREIGN KEY (`company`) REFERENCES `companies`(`id`),
    FOREIGN KEY (`used_target`) REFERENCES `targets`(`id`)
);

CREATE TABLE IF NOT EXISTS `company_pollutants_classes` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `company` INT NOT NULL,
    `pollutant` INT NOT NULL,
    `danger_class` VARCHAR(8) NOT NULL COLLATE `utf8_general_ci`,
    `lfv_group` VARCHAR(8) NOT NULL COLLATE `utf8_general_ci`,

    INDEX `company_idx` (`company`),
    INDEX `pollutant_idx` (`pollutant`),
    FOREIGN KEY (`company`) REFERENCES `companies`(`id`),
    FOREIGN KEY (`pollutant`) REFERENCES `pollutants`(`id`)
);

CREATE TABLE IF NOT EXISTS `discharge_pollutants` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `discharge` INT NOT NULL,
    `pollutant` INT NOT NULL,
    `concentration` DOUBLE NOT NULL,
    `ncc` DOUBLE NOT NULL COMMENT 'КНК',
    `background_concentration` DOUBLE NOT NULL,
    `mpc` DOUBLE NOT NULL COMMENT 'ПДК',

    INDEX `discharge_idx` (`discharge`),
    INDEX `pollutant_idx` (`pollutant`),
    FOREIGN KEY (`discharge`) REFERENCES `discharges`(`id`),
    FOREIGN KEY (`pollutant`) REFERENCES `pollutants`(`id`)
)