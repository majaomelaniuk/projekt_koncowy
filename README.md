# DOKUMENTACJA PROJEKTU

## Tytuł

Aplikacja do zarządzania statystykami meczów piłkarskich
## Opis projektu

Projekt polega na stworzeniu aplikacji okienkowej w języku Java, która umożliwia zarządzanie statystykami meczów piłkarskich. Aplikacja pozwala użytkownikowi na dodawanie drużyn, rejestrowanie meczów oraz wprowadzanie szczegółowych statystyk takich jak data meczu, zdobyte gole i kartki. Wyniki meczów są aktualizowane i wyświetlane w tabeli, która przedstawia aktualny ranking drużyn wraz ze statystykami takimi jak liczba punktów, rozegrane mecze, wygrane, remisy, przegrane oraz bilans bramek każdej drużyny. Użytkownik może również wyświetlić informacje o danej drużynie, takie jak lista zawodników, trener czy  aktualnie posiadane punkty.  

## Wymagania systemowe

- Java Development Kit (JDK) 8 lub nowszy
- System operacyjny: Windows, macOS, Linux

# KLASY

## Main

Punkt wejścia programu. Tworzy okno aplikacji i uruchamia główną logikę programu.

## Tabela

Najważniejsza klasa projektu. Odpowiada za zarządzanie listą drużyn oraz meczów. Umożliwia dodawanie drużyn i meczów oraz aktualizację tabeli wyników.

 - **dodajDruzyne** - Dodaje drużynę do tabeli.
 - **dodajMecz** - Dodaje mecz do tabeli oraz aktualizuje tabelę wyników.
 - **aktualizujTabele** - Aktualizuje statystyki wszystkich drużyn na podstawie rozegranych meczów.

## Druzyna

Klasa reprezentująca drużynę piłkarską. Przechowuje informacje o nazwie drużyny, trenerze, kapitanie oraz piłkarzach.

 - **dodajMecz** - Aktualizuje statystyki drużyny na podstawie wyniku meczu.
 - **dodajPilkarza** - Dodaje piłkarza do drużyny.
 - **resetujStatystyki** - Resetuje statystyki drużyny.

## Mecz

Klasa reprezentująca mecz piłkarski. Przechowuje informacje o drużynach, golach i kartkach.

 - **dodajGol** - Dodaje gol do meczu.
 - **dodajKartke** - Dodaje kartkę do meczu.

## Czlowiek

Klasa bazowa reprezentująca człowieka z imieniem i nazwiskiem. Dziedziczą po niej klasy Trener, Pilkarz i inne.

## Pilkarz

Klasa reprezentująca piłkarza. Dziedziczy po klasie Czlowiek. Przechowuje numer zawodnika oraz liczbę zdobytych bramek.

 - **dodajBramke** - Dodaje bramkę do statystyk piłkarza.

## Trener

Klasa reprezentująca trenera. Dziedziczy po klasie Czlowiek. Przechowuje rok objęcia drużyny.

## Kapitan

Klasa reprezentująca kapitana drużyny. Dziedziczy po klasie Pilkarz. Przechowuje rok objęcia funkcji kapitana.

## Napastnik

Klasa reprezentująca napastnika. Dziedziczy po klasie Pilkarz.

## Bramkarz

Klasa reprezentująca bramkarza. Dziedziczy po klasie Pilkarz. Przechowuje liczbę obron.

 - **dodajObrone** - Dodaje obronę do statystyk bramkarza.

## Gol

Klasa reprezentująca gol w meczu. Przechowuje informacje o strzelcu oraz minucie zdobycia gola.

## Kartka

Klasa reprezentująca kartkę w meczu. Przechowuje informacje o piłkarzu, kolorze kartki, minucie oraz drużynie.


## GUI Klasy

### Main

Główna klasa GUI, która tworzy główne okno aplikacji z różnymi panelami do zarządzania statystykami meczów piłki nożnej.

### DodajDruzyneWidok

Klasa GUI do dodawania nowej drużyny. Umożliwia użytkownikowi wprowadzenie informacji o drużynie, trenerze, kapitanie oraz piłkarzach.

### DodajMeczWidok

Klasa GUI do dodawania nowego meczu. Umożliwia użytkownikowi wprowadzenie informacji o meczu, golach oraz kartkach.

### TabelaWidok

Klasa GUI do wyświetlania aktualnego rankingu drużyn. Prezentuje dotychczas zdobyte punkty, ilość rozegranych meczy, wygranych, remisów, przegranych oraz liczbę zdobytych i straconych bramek.

### MeczeWidok

Klasa GUI do wyświetlania listy meczów. Prezentuje dotychczas rozegrane mecze między drużynami wraz z wynikami.

### MeczPodgladWidok

Klasa GUI do wyświetlania szczegółów pojedynczego meczu. Pokazuje informacje o golach i kartkach w meczu.

### DruzynaPodgladWidok

Klasa GUI do wyświetlania szczegółów pojedynczej drużyny. Pokazuje informacje o trenerze, kapitanie oraz piłkarzach drużyny.
