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

INSERT INTO `employees` (`employee_id`, `name`, `last_name`, `pesel`, `address`, `phone`, `type_of_contract`, `employed_since`) VALUES
(1, 'Felicja', 'Olszewska', '98010284567', 'ul. Daleka 113 52-224 Wrocław', '60 497 46 10', 'Umowa o pracę', '2017-06-09'),
(2, 'Wiktoria', 'Szczepańska', '96070307268', 'ul. Na Dołku 39 61-313 Poznań', '67 722 96 03', 'Umowa o pracę', '2017-01-16'),
(3, 'Janusz', 'Nowak', '46122738250', 'ul. Kościuszki 135 46-320 Praszka', '66 850 77 62', 'Umowa o dzieło', '2010-10-01'),
(4, 'Patryk', 'Kowalczyk', '72091593239', 'ul. Krasickiego Ignacego 18 81-867 Sopot', '69 474 06 80', 'Umowa o pracę', '2016-01-01'),
(5, 'Paweł', 'Adamski', '86031569350', 'ul. Słowackiego 62 58-300 Wałbrzych', '51 359 29 52', 'Umowa zlecenie', '2017-11-01'),
(6, 'Aleksander', 'Borkowski', '92100608299', 'ul. Tęczowa 36 60-287 Poznań', '51 341 99 58', 'B2B', '2017-01-01'),
(7, 'Franciszek', 'Sawicki', '47060732094', 'ul. Zapłotek 18 85-357 Bydgoszcz', '72 660 66 71', 'Umowa o pracę', '2012-11-01'),
(8, 'Kazimierz', 'Jabłoński', '50082989611', 'ul. Zalipie 142 04-625 Warszawa', '66 808 46 96', 'Umowa o pracę', '2014-05-10'),
(9, 'Franciszek', 'Adamski', '79110511979', 'ul. Konstytucyjna 40 90-150 Łódź', '60 386 16 74', 'Umowa o dzieło', '2017-03-20'),
(10, 'Anna', 'Wojciechowska', '89121400694', 'ul. Słupska 75 40-713 Katowice', '69 832 25 02', 'B2B', '2017-07-13'),
(11, 'Arkadiusz', 'Grabowski', '95081794553', 'ul. Grabinów 62 25-721 Kielce', '66 800 52 76', 'Umowa zlecenie', '2018-01-01'),
(12, 'Arkadiusz', 'Walczak', '73123143213', 'ul. Lotnicza 133 20-322 Lublin', '51 480 66 65', 'B2B', '2015-09-07'),
(13, 'Ireneusz', 'Sokołowski', '85111875699', 'ul. Macierzankowa 24 60-175 Poznań', '88 784 76 15', 'B2B', '2017-01-10'),
(14, 'Sylwia', 'Piotrowska', '72032681500', 'ul. Medweckiego 26 31-850 Kraków', '53 951 10 68', 'Umowa o pracę', '2017-12-10'),
(15, 'Barbara', 'Michalska', '86072216262', 'ul. Solna 35 70-711 Szczecin', '72 989 03 18', 'B2B', '2016-02-08'),
(16, 'Sylwester', 'Ostrowski', '79033064831', 'ul. Niedurnego 39 41-709 Ruda Śląska', '51 948 73 38', 'Umowa o pracę', '2016-05-20'),
(17, 'Aleksander', 'Zieliński', '98092904650', 'ul. Kresowa 33 30-394 Kraków', '69 808 65 22', 'Umowa zlecenie', '2018-01-01'),
(18, 'Juliana', 'Szymańska', '79082344184', 'ul. Żółkiewskiego Stanisława 133 85-042 Bydgoszcz', ' 78 307 26 15', 'B2B', '2016-01-01'),
(19, 'Justyna', 'Duda', '84051512080', 'ul. Rogowska 115 03-521 Warszawa', '67 410 69 93', 'Umowa o pracę', '2017-11-10'),
(20, 'Danuta', 'Wysocka', '94101013427', 'ul. Gołkowska 123 02-905 Warszawa', '69 682 92 26', 'Umowa o pracę', '2016-02-16'),
(21, 'Antoni', 'Maciejewski', '96122261030', 'ul. Policka 5 71-837 Szczecin', '60 420 55 35', 'B2B', '2018-01-10'),
(22, 'Oliwia', 'Zielińska', '85041359746', 'ul. Wiączyńska 101 92-760 Łódź', '72 765 24 18', 'Umowa o pracę', '2015-01-02'),
(23, 'Miłosz', 'Adamczyk', '74062880296', 'ul. Podchorążych 107 81-103 Gdynia', '60 409 54 30', 'Umowa o dzieło', '2014-08-08'),
(24, 'Ryszard', 'Kucharski', '98042015236', 'ul. 1 Maja 53 11-507 Giżycko', ' 78 459 88 84', 'Umowa o pracę', '2017-04-21'),
(25, 'Malwina', 'Kamińska', '84090241105', 'ul. Profesorska 35 91-304 Łódź', '60 439 67 27', 'B2B', '2012-11-10');

INSERT INTO `projects` (`project_id`, `name`, `start_date`, `end_date`, `client_id`) VALUES
(1, 'Wykonanie strony internetowej', '2018-01-03', '2018-01-31', 1),
(2, 'Wykonanie aplikacji mobilnej', '2018-01-03', '2018-01-31', 1),
(3, 'Test edycji daty rozpoczecia', '2018-01-03', '2018-01-31', 8),
(4, 'Test edycji daty zakończenia', '2018-01-03', '2018-01-31', 2),
(5, 'Test usuwania', '2018-01-03', '2018-01-31', 3),
(6, 'Test dodawania klienta', '2018-01-03', '2018-01-31', null),
(7, 'Test usuwania klienta', '2018-01-03', null, 1),
(8, 'Test edycji klienta', '2018-01-03', null, 1),
(9, 'Konflikt', '2018-01-03', null, 1),
(10, 'Test PHE', '2018-01-03', null, 1);

INSERT INTO `projects_has_employees` (`phe_id`, `project_id`, `employee_id`, `role`) VALUES
(1, 1, 1, 'PM'),
(2, 1, 2, 'Front-end developer'),
(3, 1, 7, 'Front-end developer');
