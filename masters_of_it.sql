-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 27 Lis 2017, 13:45
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
  `client_id` int(4) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `contact` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `phone` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `clients`
--

INSERT INTO `clients` (`client_id`, `name`, `address`, `contact`, `phone`) VALUES
(3, 'Klient testowy', 'Lublin, ul Kryształowa', 'Jan Testowy', 111333444),
(4, 'Klient Test Edycji', 'Warszawa, ul. Pu?awska', 'Krzysztof Kowalski', 999777222),
(6, 'Klient Test Usuwania', 'Warszawa, ul. Pu?awska', 'Krzysztof Kowalski', 999777222);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `clients_has_projects`
--

CREATE TABLE `clients_has_projects` (
  `chp_id` int(4) NOT NULL,
  `client_id` int(4) NOT NULL,
  `project_id` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `clients_has_projects`
--

INSERT INTO `clients_has_projects` (`chp_id`, `client_id`, `project_id`) VALUES
(3, 3, 3),
(4, 3, 4),
(5, 4, 5);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `employees`
--

CREATE TABLE `employees` (
  `employee_id` int(4) NOT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `role` enum('Front-end developer','Back-end developer','Software tester','Analyst','Scrum master','Project manager') COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `employees`
--

INSERT INTO `employees` (`employee_id`, `name`, `last_name`, `role`) VALUES
(1, 'Jan', 'Kowalski', 'Project manager'),
(2, 'Jakub', 'Woźniak', 'Software tester'),
(3, 'Jakub', 'Łosicki', 'Back-end developer'),
(4, 'Krzysztof', 'Nowak', 'Back-end developer');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `projects`
--

CREATE TABLE `projects` (
  `project_id` int(4) NOT NULL,
  `name` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `projects`
--

INSERT INTO `projects` (`project_id`, `name`, `start_date`, `end_date`) VALUES
(3, 'Wykonanie aplikacji mobilnej', '2017-11-15', NULL),
(4, 'Wykonanie aplikacji webowej', '2016-11-08', NULL),
(5, 'Test REST API', '2017-11-15', NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `projects_has_employees`
--

CREATE TABLE `projects_has_employees` (
  `project_id` int(4) NOT NULL,
  `employee_id` int(4) NOT NULL,
  `phe_id` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `projects_has_employees`
--

INSERT INTO `projects_has_employees` (`project_id`, `employee_id`, `phe_id`) VALUES
(3, 2, 4),
(3, 4, 5),
(3, 1, 6),
(4, 3, 7),
(4, 4, 8),
(5, 2, 12),
(4, 2, 13);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`client_id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `clients_has_projects`
--
ALTER TABLE `clients_has_projects`
  ADD PRIMARY KEY (`chp_id`),
  ADD KEY `client_id` (`client_id`),
  ADD KEY `project_id` (`project_id`);

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
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `projects_has_employees`
--
ALTER TABLE `projects_has_employees`
  ADD PRIMARY KEY (`phe_id`),
  ADD KEY `project_id` (`project_id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `clients`
--
ALTER TABLE `clients`
  MODIFY `client_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT dla tabeli `clients_has_projects`
--
ALTER TABLE `clients_has_projects`
  MODIFY `chp_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT dla tabeli `employees`
--
ALTER TABLE `employees`
  MODIFY `employee_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT dla tabeli `projects`
--
ALTER TABLE `projects`
  MODIFY `project_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT dla tabeli `projects_has_employees`
--
ALTER TABLE `projects_has_employees`
  MODIFY `phe_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `clients_has_projects`
--
ALTER TABLE `clients_has_projects`
  ADD CONSTRAINT `clients_has_projects_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `clients_has_projects_ibfk_2` FOREIGN KEY (`project_id`) REFERENCES `projects` (`project_id`) ON UPDATE CASCADE;

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
