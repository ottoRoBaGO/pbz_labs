CREATE TABLE IF NOT EXISTS `teachers` (
    `id` VARCHAR(16) PRIMARY KEY,
    `last_name` VARCHAR(64) NOT NULL COLLATE `utf8_general_ci`,
    `position` VARCHAR(64) NOT NULL COLLATE `utf8_general_ci`,
    `department` VARCHAR(16) NOT NULL COLLATE `utf8_general_ci`,
    `speciality` TINYTEXT NOT NULL COLLATE `utf8_general_ci`,
    `home_phone` INT(16) NOT NULL
);

CREATE TABLE IF NOT EXISTS `subjects` (
    `id` VARCHAR(16) PRIMARY KEY COLLATE `utf8_general_ci`,
    `name` VARCHAR(64) NOT NULL COLLATE `utf8_general_ci`,
    `hours_amount` INT(11) NOT NULL,
    `speciality` TINYTEXT NOT NULL COLLATE `utf8_general_ci`,
    `year` INT(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS `student_groups` (
    `id` VARCHAR(16) PRIMARY KEY COLLATE `utf8_general_ci`,
    `name` VARCHAR(16) NOT NULL COLLATE `utf8_general_ci`,
    `people_count` INT NOT NULL,
    `speciality` TINYTEXT NOT NULL COLLATE `utf8_general_ci`,
    `headman_last_name` VARCHAR(64) NOT NULL COLLATE `utf8_general_ci`
);

CREATE TABLE IF NOT EXISTS `teacher_teaches_subjects_in_groups` (
    `student_group_id` VARCHAR(16) COLLATE `utf8_general_ci`,
    `subject_id` VARCHAR(16) COLLATE `utf8_general_ci`,
    `teacher_id` VARCHAR(16) COLLATE `utf8_general_ci`,
    `lecture_hall` INT NOT NULL,
    INDEX `student_group_id_idx` (`student_group_id`),
    INDEX `subject_id_idx` (`subject_id`),
    INDEX `teacher_id_idx` (`teacher_id`),
    FOREIGN KEY (`student_group_id`) REFERENCES `student_groups`(`id`),
    FOREIGN KEY (`subject_id`) REFERENCES `subjects`(`id`),
    FOREIGN KEY (`teacher_id`) REFERENCES `teachers`(`id`),
    CONSTRAINT `pk_teacher_subject_group` PRIMARY KEY (`student_group_id`, `subject_id`, `teacher_id`)
);

INSERT INTO `teachers` (`id`, `last_name`, `position`, `department`, `speciality`, `home_phone`)
VALUES
    ('221Л', 'Фролов', 'Доцент', 'ЭВМ', 'АСОИ, ЭВМ', '487'),
    ('222Л', 'Костин', 'Доцент', 'ЭВМ', 'ЭВМ', '543'),
    ('225Л', 'Бойко', 'Профессор', 'АСУ', 'АСОИ, ЭВМ', '112'),
    ('430Л', 'Глазов', 'Ассистент', 'ТФ', 'СД', '421'),
    ('110Л', 'Петров', 'Ассистент', 'Экономики', 'Международная экономика', '324');

INSERT INTO `subjects` (`id`, `name`, `hours_amount`, `speciality`, `year`)
VALUES
    ('12П', 'Мини ЭВМ', 36, 'ЭВМ', 1),
    ('14П', 'ПЭВМ', 72, 'ЭВМ', 2),
    ('17П', 'СУБД ПК', 48, 'АСОИ', 4),
    ('18П', 'ВКСС', 52, 'АСОИ', 6),
    ('34П', 'Физика', 30, 'СД', 6),
    ('22П', 'Аудит', 24, 'Бухучета', 3);

INSERT INTO `student_groups` (`id`, `name`, `people_count`, `speciality`, `headman_last_name`)
VALUES
    ('8Г', 'Э-12', 18, 'ЭВМ', 'Иванова'),
    ('7Г', 'Э-15', 22, 'ЭВМ', 'Сеткин'),
    ('4Г', 'АС-9', 24, 'АСОИ', 'Балабанов'),
    ('3Г', 'АС-8', 20, 'АСОИ', 'Чижов'),
    ('17Г', 'С-14', 29, 'СД', 'Амросов'),
    ('12Г', 'М-6', 16, 'Международная экономика', 'Трубин'),
    ('10Г', 'Б-4', 21, 'Бухучет', 'Зязюткин');

INSERT INTO `teacher_teaches_subjects_in_groups` (`student_group_id`, `subject_id`, `teacher_id`, `lecture_hall`)
VALUES
    ('8Г', '12П', '222Л', 112),
    ('8Г', '14П', '221Л', 220),
    ('8Г', '17П', '222Л', 112),
    ('7Г', '14П', '221Л', 220),
    ('7Г', '17П', '222Л', 241),
    ('7Г', '18П', '225Л', 210),

    ('4Г', '12П', '222Л', 112),
    ('4Г', '18П', '225Л', 210),
    ('3Г', '12П', '222Л', 112),
    ('3Г', '17П', '221Л', 241),
    ('3Г', '18П', '225Л', 210),
    ('17Г', '12П', '222Л', 112),

    ('17Г', '22П', '110Л', 220),
    ('17Г', '34П', '430Л', 118),
    ('12Г', '12П', '222Л', 112),
    ('12Г', '22П', '110Л', 210),
    ('10Г', '12П', '222Л', 210),
    ('10Г', '22П', '110Л', 210);