-- 1.1 Получить полную информацию обо всех преподавателях
SELECT * FROM `teachers`;

-- 1.2 Получить полную информацию обо всех студенческих группах на специальности ЭВМ
SELECT * FROM `student_groups`
WHERE `speciality` = 'ЭВМ';

-- 1.3 Получить личный номер преподавателя и номера аудиторий, в которых они преподают предмет с кодовым номером 18П
SELECT `teacher_id`, `lecture_hall`
FROM `teacher_teaches_subjects_in_groups`
WHERE `subject_id` = '18П';

-- 1.4 Получить номера предметов и названия предметов, которые ведет преподаватель Костин
SELECT DISTINCT `subjects`.`id` AS `subject_id`, `subjects`.`name` AS `subject_name`
FROM
    `teachers` INNER JOIN `teacher_teaches_subjects_in_groups`
                          ON `teachers`.`id` = `teacher_teaches_subjects_in_groups`.`teacher_id`
               INNER JOIN `subjects`
                          ON `teacher_teaches_subjects_in_groups`.`subject_id` = `subjects`.`id`
WHERE `teachers`.`last_name` = 'Костин';

-- 1.5 Получить номер группы, в которой ведутся предметы преподавателем Фроловым
SELECT DISTINCT `teacher_teaches_subjects_in_groups`.`student_group_id`
FROM
    `teachers` INNER JOIN `teacher_teaches_subjects_in_groups`
                          ON `teachers`.`id` = `teacher_teaches_subjects_in_groups`.`teacher_id`
WHERE `teachers`.`last_name` = 'Фролов';

-- 1.6 Получить информацию о предметах, которые ведутся на специальности АСОИ
SELECT `id`, `name`, `hours_amount`, `year`
FROM `subjects`
WHERE `speciality` = 'АСОИ';

-- 1.7 Получить информацию о преподавателях, которые ведут предметы на специальности АСОИ
SELECT DISTINCT `teachers`.*
FROM
    `teachers` INNER JOIN `teacher_teaches_subjects_in_groups`
                          ON `teachers`.`id` = `teacher_teaches_subjects_in_groups`.`teacher_id`
               INNER JOIN `student_groups`
                          ON `teacher_teaches_subjects_in_groups`.`student_group_id` = `student_groups`.`id`
WHERE `student_groups`.`speciality` = 'АСОИ';

-- 1.8 Получить фамилии преподавателей, которые ведут предметы в 210 аудитории
SELECT DISTINCT `teachers`.`last_name`
FROM
    `teachers` INNER JOIN `teacher_teaches_subjects_in_groups`
                          ON `teachers`.`id` = `teacher_teaches_subjects_in_groups`.`teacher_id`
WHERE `teacher_teaches_subjects_in_groups`.`lecture_hall` = 210;

-- 1.9 Получить названия предметов и названия групп, которые ведут занятия в аудиториях с 100 по 200
SELECT DISTINCT `student_groups`.`name`, `subjects`.`name`
FROM
    `subjects` INNER JOIN `teacher_teaches_subjects_in_groups`
                          ON `subjects`.`id` = `teacher_teaches_subjects_in_groups`.`subject_id`
               INNER JOIN `student_groups`
                          ON `teacher_teaches_subjects_in_groups`.`student_group_id` = `student_groups`.`id`
WHERE `teacher_teaches_subjects_in_groups`.`lecture_hall` BETWEEN 100 AND 200;

-- 1.10 Получить пары номеров групп с одной специальности
SELECT DISTINCT `id`, `speciality`
FROM `student_groups`
WHERE `speciality` IN (
    SELECT DISTINCT `speciality`
    FROM `student_groups`
    GROUP BY `speciality`
    HAVING COUNT(`id`) = 2
);

-- 1.11 Получить общее количество студентов, обучающихся на специальности ЭВМ
SELECT SUM(`people_count`) FROM `student_groups`
WHERE `speciality` = 'ЭВМ'
GROUP BY `speciality`;

-- 1.12 Получить номера преподавателей, обучающих студентов по специальности ЭВМ
SELECT DISTINCT `teachers`.`id`
FROM
    `teachers` INNER JOIN `teacher_teaches_subjects_in_groups`
                          ON `teachers`.`id` = `teacher_teaches_subjects_in_groups`.`teacher_id`
               INNER JOIN `student_groups`
                          ON `teacher_teaches_subjects_in_groups`.`student_group_id` = `student_groups`.`id`
WHERE `student_groups`.`speciality` = 'ЭВМ';

-- 1.13 Получить номера предметов, изучаемых всеми студенческими группами
SELECT DISTINCT `subject_id`
FROM `teacher_teaches_subjects_in_groups`
GROUP BY `subject_id`
HAVING COUNT(DISTINCT `student_group_id`) = (
    SELECT COUNT(DISTINCT `student_group_id`)
    FROM `teacher_teaches_subjects_in_groups`
);

-- 1.14 Получить фамилии преподавателей, преподающих те же предметы, что и преподаватель преподающий предмет с номером 14П
WITH TheTeacher AS (
        SELECT DISTINCT `teacher_id`
        FROM `teacher_teaches_subjects_in_groups`
        WHERE `subject_id` = '14П'
    )
SELECT DISTINCT `teachers`.`last_name`
FROM
    `teachers` INNER JOIN `teacher_teaches_subjects_in_groups`
                          ON `teachers`.`id` = `teacher_teaches_subjects_in_groups`.`teacher_id`
WHERE `teacher_teaches_subjects_in_groups`.`subject_id` IN (
    SELECT DISTINCT `subject_id`
    FROM `teacher_teaches_subjects_in_groups`
    WHERE `teacher_id` = (SELECT `teacher_id` FROM `TheTeacher`)
)
AND NOT `teacher_teaches_subjects_in_groups`.`teacher_id` = (SELECT `teacher_id` FROM `TheTeacher`);

-- 1.15 Получить информацию о предметах, которые не ведет преподаватель с личным номером 221П
SELECT DISTINCT `subjects`.*
FROM
    `subjects` INNER JOIN `teacher_teaches_subjects_in_groups`
                          ON `subjects`.`id` = `teacher_teaches_subjects_in_groups`.`subject_id`
WHERE NOT `subject_id` IN (
    SELECT `subject_id`
    FROM `teacher_teaches_subjects_in_groups`
    WHERE `teacher_id` = '221Л'
);

-- 1.16 Получить информацию о предметах, которые не изучаются в группе М-6
SELECT DISTINCT `subjects`.*
FROM
    `subjects` INNER JOIN `teacher_teaches_subjects_in_groups`
                          ON `subjects`.`id` = `teacher_teaches_subjects_in_groups`.`subject_id`
WHERE NOT `subject_id` IN (
    SELECT `subject_id`
    FROM
        `teacher_teaches_subjects_in_groups` INNER JOIN `student_groups`
                   ON `teacher_teaches_subjects_in_groups`.`student_group_id` = `student_groups`.`id`
    WHERE `student_groups`.`name` = 'М-6'
);

-- 1.17 Получить информацию о доцентах, преподающих в группах 3Г и 8Г
SELECT DISTINCT `teachers`.*
FROM
    `teachers` INNER JOIN `teacher_teaches_subjects_in_groups`
                          ON `teachers`.`id` = `teacher_teaches_subjects_in_groups`.`teacher_id`
WHERE `teachers`.`position` = 'Доцент'
        AND `student_group_id` IN('3Г', '8Г');

/* 1.18 Получить номера предметов, номера преподавателей, номера групп, в которых ведут занятия преподаватели с
 кафедры ЭВМ, имеющих специальность АСОИ */
SELECT DISTINCT `teacher_teaches_subjects_in_groups`.`subject_id`,
                `teacher_teaches_subjects_in_groups`.`teacher_id`,
                `teacher_teaches_subjects_in_groups`.`student_group_id`
FROM
    `teachers` INNER JOIN `teacher_teaches_subjects_in_groups`
                          ON `teachers`.`id` = `teacher_teaches_subjects_in_groups`.`teacher_id`
               INNER JOIN `student_groups`
                          ON `teacher_teaches_subjects_in_groups`.`student_group_id` = `student_groups`.`id`
WHERE `teachers`.`department` = 'ЭВМ'
  AND `student_groups`.`speciality` = 'АСОИ';

-- 1.19 Получить номера групп с такой же специальностью, что и специальность преподавателей
SELECT DISTINCT `teacher_teaches_subjects_in_groups`.`student_group_id`
FROM
    `teachers` INNER JOIN `teacher_teaches_subjects_in_groups`
                          ON `teachers`.`id` = `teacher_teaches_subjects_in_groups`.`teacher_id`
               INNER JOIN `student_groups`
                          ON `teacher_teaches_subjects_in_groups`.`student_group_id` = `student_groups`.`id`
WHERE `teachers`.`speciality` REGEXP (CONCAT('\w', `student_groups`.`speciality`, '\w'));

/* 1.20 Получить номера преподавателей с кафедры ЭВМ, преподающих предметы по специальности, совпадающей со
 специальностью студенческой группы */
SELECT DISTINCT `teacher_teaches_subjects_in_groups`.`teacher_id`
FROM
    `teachers` INNER JOIN `teacher_teaches_subjects_in_groups`
                          ON `teachers`.`id` = `teacher_teaches_subjects_in_groups`.`teacher_id`
               INNER JOIN `subjects`
                          ON `teacher_teaches_subjects_in_groups`.`subject_id` = `subjects`.`id`
               INNER JOIN `student_groups`
                          ON `teacher_teaches_subjects_in_groups`.`student_group_id` = `student_groups`.`id`
WHERE `teachers`.`department` = 'ЭВМ'
  AND `subjects`.`speciality` = `student_groups`.`speciality`;

-- 1.21 Получить специальности студенческой группы, на которых работают преподаватели кафедры АСУ
SELECT DISTINCT `student_groups`.`speciality`
FROM
    `teachers` INNER JOIN `teacher_teaches_subjects_in_groups`
                          ON `teachers`.`id` = `teacher_teaches_subjects_in_groups`.`teacher_id`
               INNER JOIN `student_groups`
                          ON `teacher_teaches_subjects_in_groups`.`student_group_id` = `student_groups`.`id`
WHERE `teachers`.`department` = 'АСУ';

-- 1.22 Получить номера предметов, изучаемых группой АС-8
SELECT DISTINCT `teacher_teaches_subjects_in_groups`.`subject_id`
FROM
    `teacher_teaches_subjects_in_groups` INNER JOIN `student_groups`
                          ON `teacher_teaches_subjects_in_groups`.`student_group_id` = `student_groups`.`id`
WHERE `student_groups`.`name` = 'АС-8';

-- 1.23 Получить номера студенческих групп, которые изучают те же предметы, что и студенческая группа АС-8
-- Прим. 1: неясно должны ли списки предметов совпадать (а точнее, включаться (список предметов группы АС-8 включается в
-- списки предметов искомых групп)) или просто нужно выбрать группы которые изучают хотя бы один предмет
-- из списка предметов группы АС-8. Реализован 1й вариант (с совпадением (включением))
-- Прим. 2: исключение самой группы АС-8 из результатов реализовано не было, т.к. её наличие там не противоречит условию.
CREATE VIEW  `temp_views_of_group` AS
SELECT DISTINCT `teacher_teaches_subjects_in_groups`.`student_group_id`
FROM
    `teacher_teaches_subjects_in_groups` INNER JOIN `student_groups`
        ON `teacher_teaches_subjects_in_groups`.`student_group_id` = `student_groups`.`id`;
WITH `Subjects` AS (
    SELECT DISTINCT `subject_id`
    FROM
        `teacher_teaches_subjects_in_groups` INNER JOIN `student_groups`
        ON `teacher_teaches_subjects_in_groups`.`student_group_id` = `student_groups`.`id`
    WHERE `student_groups`.`name` = 'АС-8'
)
SELECT `student_group_id`
FROM
    `temp_views_of_group`
WHERE
        (SELECT COUNT(DISTINCT `teacher_teaches_subjects_in_groups`.`subject_id`)
         FROM
             `teacher_teaches_subjects_in_groups` INNER JOIN `Subjects`
                        ON `teacher_teaches_subjects_in_groups`.`subject_id` = `Subjects`.`subject_id`
         WHERE `teacher_teaches_subjects_in_groups`.`student_group_id` = `temp_views_of_group`.`student_group_id`
        ) = (SELECT COUNT(*) FROM `Subjects`);
DROP VIEW `temp_views_of_group`;

-- 1.24 Получить номера студенческих групп, которые не изучают предметы, преподаваемых в студенческой группе АС-8
CREATE VIEW  `temp_views_of_group` AS
SELECT DISTINCT `teacher_teaches_subjects_in_groups`.`student_group_id`
FROM
    `teacher_teaches_subjects_in_groups` INNER JOIN `student_groups`
                                                    ON `teacher_teaches_subjects_in_groups`.`student_group_id` = `student_groups`.`id`;
WITH `Subjects` AS (
    SELECT DISTINCT `subject_id`
    FROM
        `teacher_teaches_subjects_in_groups` INNER JOIN `student_groups`
                                                        ON `teacher_teaches_subjects_in_groups`.`student_group_id` = `student_groups`.`id`
    WHERE `student_groups`.`name` = 'АС-8'
)
SELECT `student_group_id`
FROM
    `temp_views_of_group`
WHERE
        (SELECT COUNT(DISTINCT `teacher_teaches_subjects_in_groups`.`subject_id`)
         FROM
             `teacher_teaches_subjects_in_groups` INNER JOIN `Subjects`
                                                             ON `teacher_teaches_subjects_in_groups`.`subject_id` = `Subjects`.`subject_id`
         WHERE `teacher_teaches_subjects_in_groups`.`student_group_id` = `temp_views_of_group`.`student_group_id`
        ) = 0;
DROP VIEW `temp_views_of_group`;

-- 1.25 Получить номера студенческих групп, которые не изучают предметы, преподаваемых преподавателем 430Л
CREATE VIEW  `temp_views_of_group` AS
SELECT DISTINCT `teacher_teaches_subjects_in_groups`.`student_group_id`
FROM
    `teacher_teaches_subjects_in_groups` INNER JOIN `student_groups`
                                                    ON `teacher_teaches_subjects_in_groups`.`student_group_id` = `student_groups`.`id`;
WITH `Subjects` AS (
    SELECT DISTINCT `subject_id`
    FROM `teacher_teaches_subjects_in_groups`
    WHERE `teacher_teaches_subjects_in_groups`.`teacher_id` = '430Л'
)
SELECT `student_group_id`
FROM
    `temp_views_of_group`
WHERE
        (SELECT COUNT(DISTINCT `teacher_teaches_subjects_in_groups`.`subject_id`)
         FROM
             `teacher_teaches_subjects_in_groups` INNER JOIN `Subjects`
                    ON `teacher_teaches_subjects_in_groups`.`subject_id` = `Subjects`.`subject_id`
         WHERE `teacher_teaches_subjects_in_groups`.`student_group_id` = `temp_views_of_group`.`student_group_id`
        ) = 0;
DROP VIEW `temp_views_of_group`;

-- 1.26 Получить номера преподавателей, работающих с группой Э-15, но не преподающих предмет 12П
WITH `TeachersWhoTeach12P` AS (
    SELECT DISTINCT `teacher_id`
    FROM `teacher_teaches_subjects_in_groups`
    WHERE `subject_id` = '12П'
)
SELECT DISTINCT `teacher_id`
FROM
    `teacher_teaches_subjects_in_groups` INNER JOIN `student_groups`
          ON `teacher_teaches_subjects_in_groups`.`student_group_id` = `student_groups`.`id`
WHERE `student_groups`.`name` = 'Э-15'
  AND NOT `teacher_id` IN(SELECT * FROM `TeachersWhoTeach12P`);