CREATE TABLE IF NOT EXISTS `suppliers` (
    `id` VARCHAR(16) PRIMARY KEY COLLATE `utf8_general_ci`,
    `name` VARCHAR(32) NOT NULL COLLATE `utf8_general_ci`,
    `status` INT NOT NULL,
    `city` VARCHAR(16) NOT NULL COLLATE `utf8_general_ci`
);

CREATE TABLE IF NOT EXISTS `parts` (
    `id` VARCHAR(16) PRIMARY KEY COLLATE `utf8_general_ci`,
    `name` VARCHAR(32) NOT NULL COLLATE `utf8_general_ci`,
    `color` VARCHAR(16) NOT NULL COLLATE `utf8_general_ci`,
    `size` INT NOT NULL,
    `city` VARCHAR(32) NOT NULL COLLATE `utf8_general_ci`
);

CREATE TABLE IF NOT EXISTS `projects` (
    `id` VARCHAR(16) PRIMARY KEY COLLATE `utf8_general_ci`,
    `name` VARCHAR(32) NOT NULL COLLATE `utf8_general_ci`,
    `city` VARCHAR(32) NOT NULL COLLATE `utf8_general_ci`
);

CREATE TABLE IF NOT EXISTS `suppliers_supply_projects_with_parts` (
    `supplier_id` VARCHAR(16) COLLATE `utf8_general_ci`,
    `part_id` VARCHAR(16) COLLATE `utf8_general_ci`,
    `project_id` VARCHAR(16) COLLATE `utf8_general_ci`,
    `parts_count` INT NOT NULL,
    INDEX `supplier_id_idx` (`supplier_id`),
    INDEX `part_id_idx` (`part_id`),
    INDEX `project_id_idx` (`project_id`),
    FOREIGN KEY (`supplier_id`) REFERENCES `suppliers`(`id`),
    FOREIGN KEY (`part_id`) REFERENCES `parts`(`id`),
    FOREIGN KEY (`project_id`) REFERENCES `projects`(`id`),
    CONSTRAINT `pk_supplier_part_project` PRIMARY KEY (`supplier_id`, `part_id`, `project_id`)
);

INSERT INTO `suppliers` (`id`, `name`, `status`, `city`)
VALUES
    ('П1', 'Петров', 20, 'Москва'),
    ('П2', 'Синицын', 10, 'Таллинн'),
    ('П3', 'Федоров', 30, 'Таллинн'),
    ('П4', 'Чаянов', 20, 'Минск'),
    ('П5', 'Крюков', 30, 'Киев');

INSERT INTO `parts` (`id`, `name`, `color`, `size`, `city`)
VALUES
    ('Д1', 'Болт', 'Красный', 12, 'Москва'),
    ('Д2', 'Гайка', 'Зеленая', 17, 'Минск'),
    ('Д3', 'Диск', 'Черный', 17, 'Вильнюс'),
    ('Д4', 'Диск', 'Черный', 14, 'Москва'),
    ('Д5', 'Корпус', 'Красный', 12, 'Минск'),
    ('Д6', 'Крышки', 'Красный', 19, 'Москва');

INSERT INTO `projects` (`id`, `name`, `city`)
VALUES
    ('ПР1', 'ИПР1', 'Минск'),
    ('ПР2', 'ИПР2', 'Таллинн'),
    ('ПР3', 'ИПР3', 'Псков'),
    ('ПР4', 'ИПР4', 'Псков'),
    ('ПР5', 'ИПР5', 'Москва'),
    ('ПР6', 'ИПР6', 'Саратов'),
    ('ПР7', 'ИПР7', 'Москва');

INSERT INTO `suppliers_supply_projects_with_parts` (`supplier_id`, `part_id`, `project_id`, `parts_count`)
VALUES
    ('П1', 'Д1', 'ПР1', 200),
    ('П1', 'Д1', 'ПР2', 700),

    ('П2', 'Д3', 'ПР1', 400),
    ('П2', 'Д2', 'ПР2', 200),
    ('П2', 'Д3', 'ПР3', 200),
    ('П2', 'Д3', 'ПР4', 500),
    ('П2', 'Д3', 'ПР5', 600),
    ('П2', 'Д3', 'ПР6', 400),
    ('П2', 'Д3', 'ПР7', 800),
    ('П2', 'Д5', 'ПР2', 100),

    ('П3', 'Д3', 'ПР1', 200),
    ('П3', 'Д4', 'ПР2', 500),

    ('П4', 'Д6', 'ПР3', 300),
    ('П4', 'Д6', 'ПР7', 300),

    ('П5', 'Д2', 'ПР2', 200),
    ('П5', 'Д2', 'ПР4', 100),
    ('П5', 'Д5', 'ПР5', 500),
    ('П5', 'Д5', 'ПР7', 100),

    ('П5', 'Д6', 'ПР2', 200),
    ('П5', 'Д1', 'ПР2', 100),
    ('П5', 'Д3', 'ПР4', 200),
    ('П5', 'Д4', 'ПР4', 800),

    ('П5', 'Д5', 'ПР4', 400),
    ('П5', 'Д6', 'ПР4', 500);