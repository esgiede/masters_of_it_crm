-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 04 Gru 2017, 07:55
-- Wersja serwera: 10.1.28-MariaDB
-- Wersja PHP: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `masters_of_it`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `clients`
--

CREATE TABLE `clients` (
  `client_id` int(10) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `contact` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `phone` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `clients`
--

INSERT INTO `clients` (`client_id`, `name`, `address`, `contact`, `phone`) VALUES
(1, 'Drutex sp. z o.o.', 'Bytow, ul. Slaska 123', 'Jan Kowalczyk', 794355428),
(2, 'Test edycji nazwy', 'Lublin, ul. Morwowa', 'Jan kowalski', 111111111),
(3, 'Test edycji adresu', 'Lublin, ul. Morwowa', 'Jan kowalski', 111111111),
(4, 'Test edycji kontaktu', 'Lublin, ul. Morwowa', 'Jan kowalski', 111111111),
(5, 'Test edycji numeru telefonu', 'Lublin, ul. Morwowa', 'Jan kowalski', 111111111),
(6, 'Test edycji wszystkich parametrów', 'Lublin, ul. Morwowa', 'Jan kowalski', 111111111),
(7, 'Test usuwania', 'Lublin, ul. Morwowa', 'Jan kowalski', 111111111);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `employees`
--

CREATE TABLE `employees` (
  `employee_id` int(10) NOT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `role` enum('Front-end developer','Back-end developer','Software tester','Analyst','Scrum master','Project manager') COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `employees`
--

INSERT INTO `employees` (`employee_id`, `name`, `last_name`, `role`) VALUES
(1, 'Jan', 'Kowalski', 'Front-end developer'),
(2, 'Marcin', 'Nowak', 'Back-end developer'),
(3, 'Joanna', 'Brzoza', 'Software tester'),
(4, 'Jakub', 'Wolny', 'Analyst'),
(5, 'Miroslaw', 'Kamionka', 'Project manager'),
(6, 'Edycja', 'Imienia', 'Project manager'),
(7, 'Edycja', 'Nazwiska', 'Project manager'),
(8, 'Edycja', 'Stanowiska', 'Project manager'),
(9, 'Edycja', 'Wpisu', 'Project manager'),
(10, 'Usuniecie', 'Wpisu', 'Project manager');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `projects`
--

CREATE TABLE `projects` (
  `project_id` int(10) NOT NULL,
  `name` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `client_id` int(4)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `projects`
--

INSERT INTO `projects` (`project_id`, `name`, `start_date`, `end_date`, `client_id`) VALUES
(1, 'Wykonanie aplikacji webowej', '2017-12-09', NULL, NULL),
(2, 'Edycja nazwy projektu', '2017-12-10', NULL, NULL),
(3, 'Test edycji daty rozpoczecia', '2017-12-09', NULL, NULL),
(4, 'Test edycji daty zakonczenia', '2017-12-09', NULL, NULL),
(5, 'Test edycji klienta', '2017-12-09', NULL, NULL),
(6, 'Test edycji pracownikow', '2017-12-09', NULL, NULL),
(7, 'Test dodawania pracownikow', '2017-12-09', NULL, NULL),
(8, 'Test usuwania pracownikow', '2017-12-09', NULL, NULL),
(9, 'Test usuwania', '2017-12-09', NULL, NULL),
(10, 'Test dodawania klienta', '2017-12-09', NULL, NULL),
(11, 'Test usuwania Klienta', '2017-12-09', NULL, 2),
(12, 'Test update klienta', '2017-12-09', NULL, 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `projects_has_employees`
--

CREATE TABLE `projects_has_employees` (
  `phe_id` int(10) NOT NULL,
  `project_id` int(4) NOT NULL,
  `employee_id` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `projects_has_employees`
--

INSERT INTO `projects_has_employees` (`phe_id`, `project_id`, `employee_id`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 6, 1),
(5, 6, 4),
(6, 8, 2);

-- --------------------------------------------------------

-- Indeksy dla zrzutĂłw tabel
--

--
-- Indexes for table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`client_id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`employee_id`);

--
-- Indexes for table `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`project_id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `client_id` (`client_id`);

--
-- Indexes for table `projects_has_employees`
--
ALTER TABLE `projects_has_employees`
  ADD PRIMARY KEY (`phe_id`),
  ADD KEY `project_id` (`project_id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- Indexes for table `schema_version`
--
--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `clients`
--
ALTER TABLE `clients`
  MODIFY `client_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT dla tabeli `employees`
--
ALTER TABLE `employees`
  MODIFY `employee_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT dla tabeli `projects`
--
ALTER TABLE `projects`
  MODIFY `project_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT dla tabeli `projects_has_employees`
--
ALTER TABLE `projects_has_employees`
  MODIFY `phe_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Ograniczenia dla zrzutĂłw tabel
--

--
-- Ograniczenia dla tabeli `projects`
--
ALTER TABLE `projects`
  ADD CONSTRAINT `projects_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`) ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `projects_has_employees`
--
ALTER TABLE `projects_has_employees`
  ADD CONSTRAINT `projects_has_employees_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `projects` (`project_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `projects_has_employees_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
