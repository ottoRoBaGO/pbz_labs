-- Вариант 6. Задания: 27, 16, 24, 9, 22, 6, 10, 35, 15, 30

-- 27. Получить номера поставщиков, поставляющих деталь Д1 для некоторого проекта в количестве, большем среднего
-- количества деталей Д1 в поставках для этого проекта
CREATE VIEW `temp_sspwp` AS (
    SELECT *
    FROM `suppliers_supply_projects_with_parts`
);
SELECT DISTINCT `supplier_id`
FROM
    `temp_sspwp`
WHERE
        `part_id` = 'Д1' AND
        (
            SELECT SUM(`parts_count`)
            FROM `suppliers_supply_projects_with_parts`
            WHERE `part_id` = 'Д1'
              AND `supplier_id` = `temp_sspwp`.`supplier_id`
              AND `project_id` = `temp_sspwp`.`project_id`)
        >
        (
            SELECT AVG(`parts_count`)
            FROM `suppliers_supply_projects_with_parts`
            WHERE `part_id` = 'Д1' AND
                    `project_id` = `temp_sspwp`.`project_id`
        );
DROP VIEW `temp_sspwp`;

-- 16. Получить общее количество деталей Д1, поставляемых поставщиком П1
SELECT SUM(`parts_count`) AS `sum`
FROM `suppliers_supply_projects_with_parts`
WHERE
    `part_id` = 'Д1' AND
    `supplier_id` = 'П1';

-- 24. Получить номера поставщиков со статусом, меньшим чем у поставщика П1
WITH `p1_status` AS (
    SELECT `status`
    FROM `suppliers`
    WHERE `id` = 'П1'
)
SELECT DISTINCT `id`
FROM `suppliers`
WHERE `status` < (SELECT * FROM `p1_status`);

-- 9. Получить номера деталей, поставляемых поставщиком в Лондоне (Москве)
-- Прим.: не знаю почему, но Лондон не упоминается ни в одной из таблиц, так что он был заменен на Москву
SELECT DISTINCT `part_id`
FROM
    `suppliers` INNER JOIN `suppliers_supply_projects_with_parts`
                    ON `suppliers`.`id` = `suppliers_supply_projects_with_parts`.`supplier_id`
WHERE
    `city` = 'Москва';

-- 22. Получить номера проектов, использующих по крайней мере одну деталь, имеющуюся у поставщика П1
WITH `p1_parts` AS (
    SELECT DISTINCT `part_id`
    FROM `suppliers_supply_projects_with_parts`
    WHERE `supplier_id` = 'П1'
)
SELECT DISTINCT `project_id`
FROM `suppliers_supply_projects_with_parts`
WHERE
    `part_id` IN (SELECT * FROM `p1_parts`);

-- 6. Получить все такие тройки "номера поставщиков-номера деталей-номера проектов", для которых выводимые поставщик,
-- деталь и проект размещены в одном городе
SELECT `supplier_id`, `part_id`, `project_id`
FROM
    `suppliers` INNER JOIN `suppliers_supply_projects_with_parts`
                            ON `suppliers`.`id` = `suppliers_supply_projects_with_parts`.`supplier_id`
                INNER JOIN `parts`
                           ON `suppliers_supply_projects_with_parts`.`part_id` = `parts`.`id`
                INNER JOIN `projects`
                           ON `suppliers_supply_projects_with_parts`.`project_id` = `projects`.`id`
WHERE
    `suppliers`.`city` = `parts`.`city` AND
    `parts`.`city` = `projects`.`city`;

-- 10. Получить номера деталей, поставляемых поставщиком в Лондоне (Москве) для проекта в Лондоне (Москве)
-- Прим.: Лондон заменен на Москву
SELECT `part_id`
FROM
    `suppliers` INNER JOIN `suppliers_supply_projects_with_parts`
                           ON `suppliers`.`id` = `suppliers_supply_projects_with_parts`.`supplier_id`
                INNER JOIN `projects`
                           ON `suppliers_supply_projects_with_parts`.`project_id` = `projects`.`id`
WHERE
        `suppliers`.`city` = 'Москва' AND
        `projects`.`city` = 'Москва';

-- 35. Получить пары "номер поставщика-номер детали", такие, что данный поставщик не поставляет данную деталь
-- Прим.: сортировка по паре `supplier_id`, `part_id` наличествует исключительно в целях повышения наглядности вывода
CREATE VIEW `temp_all_combinations_of_suppliers_and_details` AS (
    SELECT DISTINCT `suppliers`.`id` AS `supplier_id`, `parts`.`id` AS `part_id`
    FROM
        `suppliers` INNER JOIN `parts`
);
SELECT * FROM `temp_all_combinations_of_suppliers_and_details`;
SELECT DISTINCT `temp_all_combinations_of_suppliers_and_details`.`supplier_id`,
                `temp_all_combinations_of_suppliers_and_details`.`part_id`
FROM
    `temp_all_combinations_of_suppliers_and_details` LEFT JOIN `suppliers_supply_projects_with_parts`
            ON `temp_all_combinations_of_suppliers_and_details`.`supplier_id` = `suppliers_supply_projects_with_parts`.`supplier_id`
            AND `temp_all_combinations_of_suppliers_and_details`.`part_id` = `suppliers_supply_projects_with_parts`.`part_id`
WHERE
    `suppliers_supply_projects_with_parts`.`supplier_id` IS NULL
    AND `suppliers_supply_projects_with_parts`.`part_id` IS NULL
ORDER BY `supplier_id`, `part_id`;
DROP VIEW `temp_all_combinations_of_suppliers_and_details`;

-- 15. Получить общее число проектов, обеспечиваемых поставщиком П1
SELECT COUNT(DISTINCT `project_id`) AS `total_count`
FROM `suppliers_supply_projects_with_parts`
WHERE `supplier_id` = 'П1';

-- 30. Получить номера деталей, поставляемых для лондонских (московских) проектов
-- Прим.: как и полагается Лондон заменен на Москву
SELECT DISTINCT `part_id`
FROM
    `projects` INNER JOIN `suppliers_supply_projects_with_parts`
        ON `projects`.`id` = `suppliers_supply_projects_with_parts`.`project_id`
WHERE
    `projects`.`city` = 'Москва';