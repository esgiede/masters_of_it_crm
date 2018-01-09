TRUNCATE `clients`;
TRUNCATE `employees`;
TRUNCATE `projects`;
TRUNCATE `projects_has_employees`;

INSERT INTO `clients` (`client_id`, `name`, `address`, `contact`, `phone`) VALUES
(1, 'Drutex sp. z o.o.', 'Bytów, ul. Slaska 24/12', 'Andrzej Pawlak', 792330597),
(2, 'Test edycji nazwy', 'Lublin, ul. Morwowa', 'Jan Kowalski', 111111111),
(3, 'Test edycji adresu', 'Lublin, ul. Morwowa', 'Jan Nowak', 111111111),
(4, 'Test edycji kontaktu', 'Lublin, ul. Morwowa', 'Jan Nowak', 111111111),
(5, 'Test edycji telefonu', 'Lublin, ul. Morwowa', 'Jan Kowalski', 111111111),
(6, 'Test edycji klienta', 'Lublin, ul. Morwowa', 'Jan Kowalski', 111111111),
(7, 'Test usuwania klienta', 'Lublin, ul. Morwowa', 'Jan Kowalski', 111111111),
(8, 'Klient z projektem', 'Lublin, ul. Morwowa', 'Jan Kowalski', 111111111);

INSERT INTO `employees` (`employee_id`, `name`, `last_name`) VALUES
(1, 'Jan', 'Nowak'),
(2, 'Edycja', 'Imienia'),
(3, 'Edycja', 'Nazwiska'),
(4, 'Edycja', 'Wpisu'),
(5, 'Test', 'Usuwania');

INSERT INTO `projects` (`project_id`, `name`, `start_date`, `end_date`, `client_id`) VALUES
(1, 'Wykonanie strony internetowej', '2018-01-03', '2018-01-31', 1),
(2, 'Wykonanie aplikacji mobilnej', '2018-01-03', '2018-01-31', 1),
(3, 'Test edycji daty rozpoczecia', '2018-01-03', '2018-01-31', 8),
(4, 'Test edycji daty zakończenia', '2018-01-03', '2018-01-31', 2),
(5, 'Test usuwania', '2018-01-03', '2018-01-31', 3),
(6, 'Test dodawania klienta', '2018-01-03', '2018-01-31', null),
(7, 'Test usuwania klienta', '2018-01-03', null, 1),
(8, 'Test edycji klienta', '2018-01-03', null, 1),
(9, 'Konflikt', '2018-01-03', null, 1);

INSERT INTO `projects_has_employees` (`phe_id`, `project_id`, `employee_id`, `role`) VALUES
(1, 1, 1, 'PM'),
(2, 1, 2, 'Front-end developer');
