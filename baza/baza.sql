-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Czas generowania: 07 Maj 2017, 17:52
-- Wersja serwera: 8.0.1-dmr
-- Wersja PHP: 7.0.15-0ubuntu0.16.04.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Baza danych: `ipzdb`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `projekt`
--

CREATE TABLE `projekt` (
  `id` int(11) NOT NULL,
  `nazwa` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `data_rozpoczecia` date NOT NULL,
  `data_zakonczenia` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `projekt`
--

INSERT INTO `projekt` (`id`, `nazwa`, `data_rozpoczecia`, `data_zakonczenia`) VALUES
(1, 'Aplikacja', '2017-04-26', '2017-04-28'),
(2, 'Aplikacja 2', '2017-05-08', '2017-05-26');

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
(6, 'Sprint 3', '2017-05-29', '2017-05-30');

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
(6, 2, 6);

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
(3, 'Zakończone');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `stanowisko`
--

CREATE TABLE `stanowisko` (
  `id` int(11) NOT NULL,
  `nazwa` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
  `id_rola` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `uzytkownik`
--

INSERT INTO `uzytkownik` (`id`, `imie`, `nazwisko`, `login`, `email`, `haslo`, `id_rola`) VALUES
(1, 'Kamil', 'Sikora', 'araks', 'araks1994@gmail.com', '69d773b109137f226d62f943f96414b7', 1),
(2, 'A', 'A', 'a', 'a@a.a', '0cc175b9c0f1b6a831c399e269772661', 3),
(3, 'b', 'b', 'b', 'b@b.b', '92eb5ffee6ae2fec3ad71c777531578f', 2),
(4, 'C', 'C', 'c', 'c@c.c', '4a8a08f09d37b73795649038408b5f33', 2);

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

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zadanie`
--

CREATE TABLE `zadanie` (
  `id` int(11) NOT NULL,
  `nazwa` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `czas` int(11) NOT NULL,
  `opis` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_stan` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `zadanie`
--

INSERT INTO `zadanie` (`id`, `nazwa`, `czas`, `opis`, `id_stan`) VALUES
(1, 'Programowanie', 8, 'Kod programu', 1),
(2, 'Testowanie', 12, 'Testowanie aplikacji', 1),
(3, 'Testowanie', 24, 'Testowanie aplikacji', 1),
(4, 'Implementacja', 32, 'Implementacja kodu', 1);

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
(4, 1, 4);

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
-- Indeksy dla zrzutów tabel
--

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
-- AUTO_INCREMENT dla tabeli `projekt`
--
ALTER TABLE `projekt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT dla tabeli `rola`
--
ALTER TABLE `rola`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT dla tabeli `sprint`
--
ALTER TABLE `sprint`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT dla tabeli `sprint_to_projekt`
--
ALTER TABLE `sprint_to_projekt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT dla tabeli `stan`
--
ALTER TABLE `stan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT dla tabeli `stanowisko`
--
ALTER TABLE `stanowisko`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT dla tabeli `uzytkownik`
--
ALTER TABLE `uzytkownik`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT dla tabeli `uzytkownik_to_projekt`
--
ALTER TABLE `uzytkownik_to_projekt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT dla tabeli `zadanie`
--
ALTER TABLE `zadanie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT dla tabeli `zadanie_to_projekt`
--
ALTER TABLE `zadanie_to_projekt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT dla tabeli `zadanie_to_sprint`
--
ALTER TABLE `zadanie_to_sprint`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Ograniczenia dla zrzutów tabel
--

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