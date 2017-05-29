-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Czas generowania: 29 Maj 2017, 18:57
-- Wersja serwera: 8.0.1-dmr
-- Wersja PHP: 7.0.18-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Baza danych: `ipzdb`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `komentarz`
--

CREATE TABLE `komentarz` (
  `id` int(11) NOT NULL,
  `tresc` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `data_czas` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_zadanie` int(11) NOT NULL,
  `id_uzytkownik` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `komentarz`
--

INSERT INTO `komentarz` (`id`, `tresc`, `data_czas`, `id_zadanie`, `id_uzytkownik`) VALUES
(1, 'xxx', '2017-05-23 23:30:50', 8, 2),
(2, 'Good job!', '2017-05-23 23:53:31', 4, 2),
(3, 'Test', '2017-05-24 00:01:45', 4, 1),
(4, 'Test', '2017-05-24 00:03:25', 4, 1),
(5, 'Test', '2017-05-24 00:05:41', 4, 2),
(6, 'Jest dobrze.', '2017-05-24 00:06:02', 4, 2),
(7, 'xxx', '2017-05-24 00:08:25', 3, 2),
(8, 'xxxxxxxxxxxx', '2017-05-24 00:08:35', 2, 2),
(9, 'Marcin nic nie robi!', '2017-05-24 12:01:37', 4, 2),
(10, 'Marcin nic nie robi!', '2017-05-24 12:01:45', 4, 2),
(11, 'Marcin nic nie robi!', '2017-05-24 12:01:45', 4, 2),
(12, 'Marcin nic nie robi!', '2017-05-24 12:01:46', 4, 2),
(13, 'Marcin nic nie robi!', '2017-05-24 12:01:46', 4, 2),
(14, 'Marcin nic nie robi!', '2017-05-24 12:01:46', 4, 2),
(15, 'Marcin nic nie robi!', '2017-05-24 12:01:47', 4, 2),
(16, 'Marcin nic nie robi!', '2017-05-24 12:01:47', 4, 2),
(17, 'Marcin nic nie robi!', '2017-05-24 12:01:48', 4, 2),
(18, 'Marcin nic nie robi!', '2017-05-24 12:01:48', 4, 2),
(19, 'Marcin nic nie robi!', '2017-05-24 12:01:48', 4, 2),
(20, 'Marcin nic nie robi!', '2017-05-24 12:01:49', 4, 2),
(21, 'Marcin nic nie robi!', '2017-05-24 12:01:49', 4, 2),
(22, 'Marcin nic nie robi!', '2017-05-24 12:01:49', 4, 2),
(23, 'Marcin nic nie robi!', '2017-05-24 12:01:49', 4, 2),
(24, 'Marcin nic nie robi!', '2017-05-24 12:01:50', 4, 2),
(25, 'Marcin nic nie robi!', '2017-05-24 12:01:50', 4, 2),
(26, 'komentarz', '2017-05-24 12:21:09', 1, 2),
(27, 'komentarzsad sdadasdadasd\nsadasdasdasdsad\nsaddasdasdasdasdad\nsadasdasdasdasda', '2017-05-24 12:21:29', 1, 2),
(28, 'asfasdasd', '2017-05-24 15:44:08', 15, 2),
(29, 'xaxa', '2017-05-24 15:53:22', 12, 2),
(30, 'q', '2017-05-24 15:54:37', 15, 1),
(31, 'adasdsadadas', '2017-05-25 19:50:32', 16, 9);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `projekt`
--

CREATE TABLE `projekt` (
  `id` int(11) NOT NULL,
  `nazwa` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `data_rozpoczecia` date NOT NULL,
  `data_zakonczenia` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `projekt`
--

INSERT INTO `projekt` (`id`, `nazwa`, `data_rozpoczecia`, `data_zakonczenia`) VALUES
(1, 'Aplikacja', '2017-04-26', '2017-04-28'),
(2, 'Aplikacja 2', '2017-05-08', '2017-05-26'),
(5, 'App', '2017-05-11', NULL),
(6, 'APP 2', '2017-05-13', NULL),
(10, 'Aplikacja webowa', '2017-05-24', '2017-09-03');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rola`
--

CREATE TABLE `rola` (
  `id` int(11) NOT NULL,
  `nazwa` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `rola`
--

INSERT INTO `rola` (`id`, `nazwa`) VALUES
(1, 'Administrator'),
(2, 'Pracownik'),
(3, 'Kierownik');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `sprint`
--

CREATE TABLE `sprint` (
  `id` int(11) NOT NULL,
  `nazwa` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `data_rozpoczecia` date NOT NULL,
  `data_zakonczenia` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `sprint`
--

INSERT INTO `sprint` (`id`, `nazwa`, `data_rozpoczecia`, `data_zakonczenia`) VALUES
(1, 'Sprint 1', '2017-04-26', '2017-04-27'),
(2, 'Sprint 2', '2017-05-04', '2017-05-05'),
(3, 'Sprint 3', '2017-05-08', '2017-05-09'),
(4, 'Sprint 1', '2017-05-08', '2017-05-12'),
(5, 'Sprint 2', '2017-05-15', '2017-05-19'),
(6, 'Sprint 3', '2017-05-29', '2017-05-30'),
(7, 'x', '2017-05-17', '2017-06-01'),
(8, 'qweqwd', '2017-05-02', '2017-05-15'),
(9, 'Sprint 4', '2017-05-17', '2017-05-24');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `sprint_to_projekt`
--

CREATE TABLE `sprint_to_projekt` (
  `id` int(11) NOT NULL,
  `id_projekt` int(11) NOT NULL,
  `id_sprint` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `sprint_to_projekt`
--

INSERT INTO `sprint_to_projekt` (`id`, `id_projekt`, `id_sprint`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 2, 4),
(5, 2, 5),
(6, 2, 6),
(7, 5, 7),
(8, 6, 8),
(9, 1, 9);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `stan`
--

CREATE TABLE `stan` (
  `id` int(11) NOT NULL,
  `nazwa` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `stan`
--

INSERT INTO `stan` (`id`, `nazwa`) VALUES
(1, 'Do wykonania'),
(2, 'W trakcie'),
(3, 'Zakoñczone');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `stanowisko`
--

CREATE TABLE `stanowisko` (
  `id` int(11) NOT NULL,
  `nazwa` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `stanowisko`
--

INSERT INTO `stanowisko` (`id`, `nazwa`) VALUES
(1, 'Programista'),
(2, 'Tester'),
(3, 'Grafik');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownik`
--

CREATE TABLE `uzytkownik` (
  `id` int(11) NOT NULL,
  `imie` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nazwisko` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `login` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `haslo` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_rola` int(11) NOT NULL,
  `data_logowania` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `uzytkownik`
--

INSERT INTO `uzytkownik` (`id`, `imie`, `nazwisko`, `login`, `email`, `haslo`, `id_rola`, `data_logowania`) VALUES
(1, 'Kamil', 'Sikora', 'araks', 'araks1994@gmail.com', '69d773b109137f226d62f943f96414b7', 1, '2017-05-24 21:28:39'),
(2, 'A', 'A', 'a', 'a@a.a', '0cc175b9c0f1b6a831c399e269772661', 3, '2017-05-29 18:49:22'),
(6, 'b', 'b', 'b', 'b@b.b', '92eb5ffee6ae2fec3ad71c777531578f', 2, '2017-05-24 21:33:09'),
(9, 'XX', 'XX', 'x', 'x@x.x', '9dd4e461268c8034f5c8564e155c67a6', 3, '2017-05-25 23:53:43'),
(11, 'Dawid', 'Kalwasiñski', 'kalwi', 'dkalwasinski717@gmail.com', '7cc7788b4a8f12155b12097a30981ba8', 1, NULL),
(12, 'Cyc', 'Cyc', 'c', 'c@c.c', '4a8a08f09d37b73795649038408b5f33', 3, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownik_to_projekt`
--

CREATE TABLE `uzytkownik_to_projekt` (
  `id` int(11) NOT NULL,
  `id_projekt` int(11) NOT NULL,
  `id_uzytkownik` int(11) NOT NULL,
  `id_stanowisko` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `uzytkownik_to_projekt`
--

INSERT INTO `uzytkownik_to_projekt` (`id`, `id_projekt`, `id_uzytkownik`, `id_stanowisko`) VALUES
(10, 1, 2, 1),
(16, 1, 9, 1),
(19, 2, 9, 1),
(20, 5, 2, 2),
(21, 6, 6, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zadanie`
--

CREATE TABLE `zadanie` (
  `id` int(11) NOT NULL,
  `nazwa` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `czas` int(11) NOT NULL,
  `opis` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `opis_dlugi` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_stan` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `zadanie`
--

INSERT INTO `zadanie` (`id`, `nazwa`, `czas`, `opis`, `opis_dlugi`, `id_stan`) VALUES
(1, 'Programowanie', 8, 'Kod programu', '', 3),
(2, 'Testowanie', 12, 'Testowanie aplikacji', '', 3),
(3, 'Testowanie', 24, 'Testowanie aplikacji', '', 3),
(4, 'Implementacja', 32, 'Implementacja kodu', '', 2),
(6, 'Programowanie', 24, '-------', '', 2),
(7, 'Programowanie', 24, '-------', '', 1),
(8, 'Analiza', 6, 'Analiza ', '', 1),
(9, 'x', 12, 'x', 'x', 1),
(10, 'xx', 32, 'xx', 'xx', 1),
(11, 'xxx', 1, 'x', 'x', 1),
(12, 'qq', 21, 'qq', 'qqweqw', 1),
(13, 'Testowanie2', 12, 'aaaa', 'aaaaa', 1),
(14, 'a', 1, 'saca', 'acac', 2),
(15, 'Utworzenie bazy', 10, 'Stworzenie bazy danych', 'Stworzenie bazy  MySQL oraz podpiêcie jej do aplikacji.', 1),
(16, 'fggg', 12, '1222', '', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zadanie_to_projekt`
--

CREATE TABLE `zadanie_to_projekt` (
  `id` int(11) NOT NULL,
  `id_projekt` int(11) NOT NULL,
  `id_zadanie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `zadanie_to_projekt`
--

INSERT INTO `zadanie_to_projekt` (`id`, `id_projekt`, `id_zadanie`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 1, 3),
(4, 1, 4),
(6, 2, 8),
(7, 5, 9),
(8, 5, 10),
(9, 5, 11),
(10, 6, 12),
(11, 1, 13),
(12, 1, 14),
(13, 10, 15),
(14, 1, 16);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zadanie_to_sprint`
--

CREATE TABLE `zadanie_to_sprint` (
  `id` int(11) NOT NULL,
  `id_sprint` int(11) NOT NULL,
  `id_zadanie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `zadanie_to_sprint`
--

INSERT INTO `zadanie_to_sprint` (`id`, `id_sprint`, `id_zadanie`) VALUES
(3, 1, 3),
(6, 4, 8),
(8, 9, 1),
(9, 2, 4),
(10, 2, 14),
(11, 9, 16),
(12, 7, 9),
(13, 5, 2);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `komentarz`
--
ALTER TABLE `komentarz`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_zadanie` (`id_zadanie`),
  ADD KEY `id_uzytkownik` (`id_uzytkownik`);

--
-- Indexes for table `projekt`
--
ALTER TABLE `projekt`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rola`
--
ALTER TABLE `rola`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sprint`
--
ALTER TABLE `sprint`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sprint_to_projekt`
--
ALTER TABLE `sprint_to_projekt`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_projekt` (`id_projekt`),
  ADD KEY `id_sprint` (`id_sprint`);

--
-- Indexes for table `stan`
--
ALTER TABLE `stan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `stanowisko`
--
ALTER TABLE `stanowisko`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `uzytkownik`
--
ALTER TABLE `uzytkownik`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_rola` (`id_rola`);

--
-- Indexes for table `uzytkownik_to_projekt`
--
ALTER TABLE `uzytkownik_to_projekt`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_projekt` (`id_projekt`),
  ADD KEY `id_uzytkownik` (`id_uzytkownik`),
  ADD KEY `id_stanowisko` (`id_stanowisko`);

--
-- Indexes for table `zadanie`
--
ALTER TABLE `zadanie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_stan` (`id_stan`);

--
-- Indexes for table `zadanie_to_projekt`
--
ALTER TABLE `zadanie_to_projekt`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_projekt` (`id_projekt`),
  ADD KEY `id_zadanie` (`id_zadanie`);

--
-- Indexes for table `zadanie_to_sprint`
--
ALTER TABLE `zadanie_to_sprint`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_sprint` (`id_sprint`),
  ADD KEY `id_zadanie` (`id_zadanie`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `komentarz`
--
ALTER TABLE `komentarz`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT dla tabeli `projekt`
--
ALTER TABLE `projekt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT dla tabeli `rola`
--
ALTER TABLE `rola`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT dla tabeli `sprint`
--
ALTER TABLE `sprint`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT dla tabeli `sprint_to_projekt`
--
ALTER TABLE `sprint_to_projekt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT dla tabeli `stan`
--
ALTER TABLE `stan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT dla tabeli `stanowisko`
--
ALTER TABLE `stanowisko`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT dla tabeli `uzytkownik`
--
ALTER TABLE `uzytkownik`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT dla tabeli `uzytkownik_to_projekt`
--
ALTER TABLE `uzytkownik_to_projekt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT dla tabeli `zadanie`
--
ALTER TABLE `zadanie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT dla tabeli `zadanie_to_projekt`
--
ALTER TABLE `zadanie_to_projekt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT dla tabeli `zadanie_to_sprint`
--
ALTER TABLE `zadanie_to_sprint`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `komentarz`
--
ALTER TABLE `komentarz`
  ADD CONSTRAINT `komentarz_ibfk_1` FOREIGN KEY (`id_uzytkownik`) REFERENCES `uzytkownik` (`id`),
  ADD CONSTRAINT `komentarz_ibfk_2` FOREIGN KEY (`id_zadanie`) REFERENCES `zadanie` (`id`);

--
-- Ograniczenia dla tabeli `sprint_to_projekt`
--
ALTER TABLE `sprint_to_projekt`
  ADD CONSTRAINT `sprint_to_projekt_ibfk_1` FOREIGN KEY (`id_projekt`) REFERENCES `projekt` (`id`),
  ADD CONSTRAINT `sprint_to_projekt_ibfk_2` FOREIGN KEY (`id_sprint`) REFERENCES `sprint` (`id`);

--
-- Ograniczenia dla tabeli `uzytkownik`
--
ALTER TABLE `uzytkownik`
  ADD CONSTRAINT `uzytkownik_ibfk_1` FOREIGN KEY (`id_rola`) REFERENCES `rola` (`id`);

--
-- Ograniczenia dla tabeli `uzytkownik_to_projekt`
--
ALTER TABLE `uzytkownik_to_projekt`
  ADD CONSTRAINT `uzytkownik_to_projekt_ibfk_1` FOREIGN KEY (`id_uzytkownik`) REFERENCES `uzytkownik` (`id`),
  ADD CONSTRAINT `uzytkownik_to_projekt_ibfk_2` FOREIGN KEY (`id_projekt`) REFERENCES `projekt` (`id`),
  ADD CONSTRAINT `uzytkownik_to_projekt_ibfk_3` FOREIGN KEY (`id_stanowisko`) REFERENCES `stanowisko` (`id`);

--
-- Ograniczenia dla tabeli `zadanie`
--
ALTER TABLE `zadanie`
  ADD CONSTRAINT `zadanie_ibfk_1` FOREIGN KEY (`id_stan`) REFERENCES `stan` (`id`);

--
-- Ograniczenia dla tabeli `zadanie_to_projekt`
--
ALTER TABLE `zadanie_to_projekt`
  ADD CONSTRAINT `zadanie_to_projekt_ibfk_1` FOREIGN KEY (`id_projekt`) REFERENCES `projekt` (`id`),
  ADD CONSTRAINT `zadanie_to_projekt_ibfk_2` FOREIGN KEY (`id_zadanie`) REFERENCES `zadanie` (`id`);

--
-- Ograniczenia dla tabeli `zadanie_to_sprint`
--
ALTER TABLE `zadanie_to_sprint`
  ADD CONSTRAINT `zadanie_to_sprint_ibfk_1` FOREIGN KEY (`id_sprint`) REFERENCES `sprint` (`id`),
  ADD CONSTRAINT `zadanie_to_sprint_ibfk_2` FOREIGN KEY (`id_zadanie`) REFERENCES `zadanie` (`id`);