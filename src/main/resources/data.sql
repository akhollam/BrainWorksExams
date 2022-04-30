INSERT INTO `exams`.`customer` (`id`, `name`) VALUES ('100', 'AWS');
INSERT INTO `exams`.`customer` (`id`, `name`) VALUES ('101', 'Microsoft');
INSERT INTO `exams`.`customer` (`id`, `name`) VALUES ('102', 'Oracle');

INSERT INTO `exams`.`exam` (`id`, `name`, `customer_id`) VALUES ('100', 'Core Java Practice Exam', '102');

INSERT INTO question(`id`, `text`) VALUES (100, 'Which of the following option leads to the portability and security of Java?');

INSERT INTO answer(`id`, `text`) VALUES (100, 'Bytecode is executed by JVM');
INSERT INTO answer(`id`, `text`) VALUES (101, 'The applet makes the Java code secure and portable');
INSERT INTO answer(`id`, `text`) VALUES (102, 'Use of exception handling');
INSERT INTO answer(`id`, `text`) VALUES (103, 'Dynamic binding between objects');

INSERT INTO available_options(`id`, `correct_answer`, `question_id`, `answer_id`) VALUES (100, true, 100, 100);
INSERT INTO available_options(`id`, `correct_answer`, `question_id`, `answer_id`) VALUES (101, false, 100, 101);
INSERT INTO available_options(`id`, `correct_answer`, `question_id`, `answer_id`) VALUES (102, false, 100, 102);
INSERT INTO available_options(`id`, `correct_answer`, `question_id`, `answer_id`) VALUES (103, false, 100, 103);

INSERT INTO `exams`.`user` (`id`, `name`) VALUES ('1', 'Abhijeet Khollam');
 