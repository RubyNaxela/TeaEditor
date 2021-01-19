# Chajikan 茶時間

### Czym jest program

> Aplikacja służy do edycji bazy danych zawierającej dane dotyczące kolekcji herbat.

## Szczegóły działania

#### Charakterystyka i funkcjonalności programu

> Aplikacja działa w oknie z interfejsem graficznym, którego elementami są:
> - trzy panele z tabelami umożliwiające szybki przegląd, dodawanie, usuwanie oraz edycję:
>   - marek herbat
>   - półek z herbatami
>   - herbat z półek
> - pozycje w panelach automatycznie sortowane są alfabetycznie
> - panel z podglądem aktualnie zaznaczonego elementu
> - pasek menu z zakładkami:
>   - Plik:
>     - Nowa baza danych... (`⌘+N`/`Ctrl+N`) - tworzy nowy plik bazy danych
>     - Otwórz... (`⌘+O`/`Ctrl+O`) - otwiera bazę danych
>     - Zapisz... (`⌘+S`/`Ctrl+S`) - zapisuje aktualnie otwartą bazę danych (otwiera okno dialogowe zapisu pliku, jeśli
>       aktualnie otwarta baza została utworzona jako nowy plik podczas działania programu)
>     - Zapisz jako... (`⌘+S`/`Ctrl+S`) - zapisuje aktualnie otwartą bazę danych (wymusza okno dialogowe zapisu pliku)
>     - Zamknij... (`⌘+W`/`Ctrl+W`) - zamyka aktualnie otwartą bazę danych
>     - Wyjdź... (`⌘+Q`/`Ctrl+Q`) - zamyka program
>   - Edycja
>     - Cofnij (`⌘+Z`/`Ctrl+Z`) - do zaimplementowania
>     - Ponów (`⌘+Shift+Z`/`Ctrl+Y`) - do zaimplementowania
>
> Dane są wczytywane do programu oraz zapisywane z/do pliku w formacie JSON zgodnego ze schematem zamieszczonym poniżej.

#### Struktura bazy danych

> Program operuje na relacyjnej bazie danych o następującej strukturze:

<table><tbody>
    <tr>
        <td><pre>brands</pre>Lista marek</td>
        <td>
            <pre>[lista]</pre>
            <table><tbody>
                <tr>
                    <td>
                        <table><tbody>
                            <tr>
                                <td><pre>id</pre>ID marki<br></td>
                                <td><pre>[tekst]</pre>(zgodne z <b>formatem ALPHANUMID</b>;<br>połączone z <b>ID marki herbaty</b>)</td>
                            </tr>
                            <tr>
                                <td><pre>name</pre>Nazwa marki</td>
                                <td><pre>[tekst]</pre></td>
                            </tr>
                            <tr>
                                <td><pre>color</pre>Kolor marki</td>
                                <td><pre>[tekst]</pre>(zgodne z <b>formatem HEXCOLOR</b>)</td>
                            </tr>
                        </tbody></table>
                    </td>
                </tr>
                <tr>
                    <td>...</td>
                </tr>
            </tbody></table>
        </td>
    </tr>
    <tr>
        <td><pre>shelves</pre>Lista półek</td>
        <td>
            <pre>[lista]</pre>
            <table><tbody>
                <tr>
                    <td>
                        <table><tbody>
                            <tr>
                                <td><pre>id</pre>ID półki</td>
                                <td><pre>[tekst]</pre>(zgodne z <b>formatem ALPHANUMID</b>)</td>
                            </tr>
                            <tr>
                                <td><pre>name</pre>Nazwa półki</td>
                                <td><pre>[tekst]</pre></td>
                            </tr>
                            <tr>
                                <td><pre>color</pre>Kolor półki<br></td>
                                <td><pre>[tekst]</pre>(zgodne z <b>formatem HEXCOLOR</b>)</td>
                            </tr>
                            <tr>
                                <td><pre>tea_boxes</pre>Lista herbat</td>
                                <td>
                                    <pre>[lista]</pre>
                                    <table><tbody>
                                        <tr>
                                            <td><pre>id</pre>ID herbaty</td>
                                            <td><pre>[tekst]</pre>(zgodne z <b>formatem ALPHANUMID</b>)</td>
                                        </tr>
                                        <tr>
                                            <td><pre>name</pre>Nazwa herbaty</td>
                                            <td><pre>[tekst]</pre></td>
                                        </tr>
                                        <tr>
                                            <td><pre>brand_id</pre>ID marki herbaty</td>
                                            <td><pre>[tekst]</pre>(zgodne z <b>formatem ALPHANUMID</b>;<br>połączone z <b>ID marki</b>)</td>
                                        </tr>
                                        <tr>
                                            <td><pre>description</pre>Opis herbaty</td>
                                            <td><pre>[tekst]</pre></td>
                                        </tr>
                                        <tr>
                                            <td><pre>amount</pre>Posiadana ilość<br>herbaty w g</td>
                                            <td><pre>[liczba]</pre></td>
                                        </tr>
                                        <tr>
                                            <td><pre>stars</pre>Ocena herbaty</td>
                                            <td><pre>[liczba]</pre>(zgodne z <b>formatem NUMRATING</b>)</td>
                                        </tr>
                                        <tr>
                                            <td><pre>brewing</pre>Instrukcje<br>parzenia<br>herbaty</td>
                                            <td>
                                                <table><tbody>
                                                    <tr>
                                                        <td><pre>temperature</pre>Temperatura<br>parzenia w °C</td>
                                                        <td><pre>[liczba]</pre></td>
                                                    </tr>
                                                    <tr>
                                                        <td><pre>time</pre>Czas parzenia<br>w min</td>
                                                        <td><pre>[liczba]</pre></td>
                                                    </tr>
                                                    <tr>
                                                        <td><pre>reuses</pre>Ilość zaparzeń</td>
                                                        <td><pre>[tekst|null]</pre>(zgodne z<br><b>formatem<br>REUSES</b>)</td>
                                                    </tr>
                                                    <tr>
                                                        <td><pre>grams</pre>Proporcje masowe<br>liści herbaty<br>do wody</td>
                                                        <td><pre>[tekst]</pre>(zgodne z<br><b>formatem<br>PROPORT</b>)</td>
                                                    </tr>
                                                </tbody></table>
                                            </td>
                                        </tr>
                                    </tbody></table>
                                </td>
                            </tr>
                        </tbody></table>
                    </td>
                </tr>
                <tr>
                    <td>...</td>
                </tr>
            </tbody></table>
        </td>
    </tr>
</tbody></table>

> Używane standardy formatu danych:
> - **format ALPHANUMID**:\
    małe litery alfabetu łacińskiego oraz znak `_`\
    wyrażenie regularne:`^([a-z]|_)*$`\
    przykłady: `teatime`,`japan_gyokuro_hiki`
> - **format HEXCOLOR**:\
    6 znaków z zakresu `0-9` lub `a-f` lub `A-F`, poprzedzone znakiem `#`\
    wyrażenie regularne:`^#[0-f0-F]{6}$`\
    przykłady: `#a8b349f`,`#F4AC99`
> - **format NUMRATING**:\
    wielokrotność `0.5` z zakresu `[1;5]`\
    wyrażenie regularne:`^[1-5](\.5)?$`\
    przykłady: `4`,`2.5`
> - **format REUSES**:\
    dowolna liczba całkowita, opcjonalnie `/1` za nią\
    wyrażenie regularne:`^\d+(\/1)?$`\
    przykłady: `3/1`,`10`
> - **format PROPORT**:\
    pary: liczba`:`liczba całkowita oddzielone znakami `/`\
    wyrażenie regularne:`^(((?!^\.$)(?!^(\d*\.\d*){2,}$)[\d.])+:\d+)?(\/((?!^\.$)(?!^(\d*\.\d*){2,}$)[\d.])+:\d+)*$`\
    przykłady: `4:250/11:600`,`40:200`,`3.5:250/10:600/15.5:1000`

#### Przykładowa baza danych

> Przykład bazy danych zgodny z powyższym schematem:
> <pre>
> {
>   "brands": [
>     {
>       "id": "teatime",
>       "name": "Tea Time",
>       "color": "#00382b"
>     },
>     {
>       "id": "happy_kettle",
>       "name": "Happy Kettle",
>       "color": "#442218"
>     }
>   ],
>   "shelves": [
>     {
>       "id": "green_tea",
>       "name": "Zielone herbaty",
>       "color": "#475a24",
>       "tea_boxes": [
>         {
>           "id": "bengalska_aleja",
>           "name": "Bengalska Aleja",
>           "brand_id": "teatime",
>           "description": "Składniki: zielona herbata sencha, melon, imbir, granat, kiwi, jabłko, goździki, nagietek, pomarańcza, papaja.",
>           "amount": 31.0,
>           "stars": 3.5,
>           "brewing": {
>             "temperature": 80,
>             "time": 3,
>             "reuses": "3/1",
>             "grams": "3.5:250/10:600"
>           }
>         }
>       ]
>     },
>     {
>       "id": "black_tea",
>       "name": "Czarne herbaty",
>       "color": "#100000",
>       "tea_boxes": [
>         {
>           "id": "assam_miod_cytryna",
>           "name": "Assam miód & cytryna",
>           "brand_id": "teatime",
>           "description": "Składniki: herbata czarna liściasta, trawa cytrynowa, skórka cytryny, jeżyny, mieszanka pyłków kwiatowych (krokosz, nagietek, róża), aromaty.",
>           "amount": 97.0,
>           "stars": 0.0,
>           "brewing": {
>             "temperature": 95,
>             "time": 4,
>             "reuses": null,
>             "grams": "2.5:250/8:600"
>           }
>         },
>         {
>           "id": "ceylon_porzeczka_ze_sliwka",
>           "name": "Ceylon porzeczka ze śliwką",
>           "brand_id": "happy_kettle",
>           "description": "Składniki: herbata czarna liścista (84,5%), czarna porzeczka (7%), cząstki śliwek (5%), aromaty.",
>           "amount": 62.0,
>           "stars": 4.5,
>           "brewing": {
>             "temperature": 95,
>             "time": 4,
>             "reuses": null,
>             "grams": "2.5:250/8:600"
>           }
>         }
>       ]
>     }
>   ]
> }
> </pre>

## Instalacja

#### Instalacja standardowa

> Aby zainstalować program, należy:
> ##### 1. Upewnić się, że w systemie zainstalowane jest środowisko uruchomieniowe Java (JRE) w wersji 8+
> ##### 2. Otworzyć sekcję "Releases" znajdującą się na [stronie projektu](https://github.com/RubyNaxela/TeaEditor) obok przeglądarki kodu źródłowego
> ##### 3. Wybrać kompilację (macOS lub Universal)
> ##### 4. Kliknąć pozycję "Chajikan" z karty "Aseets"
> ##### 5. W przypadku wybrania kompilacji na system macOS, uruchomić pobrany plik instalacyjny; w przypadku wybrania kompilacji Universal, pobrany plik .jar jest gotowy do uruchomienia

#### Kompilacja ze źródła

> Istnieje możliwość samodzielnej kompilacji programu. Aby tego dokonać, należy:
> ##### 1. Sklonować repozytorium (pobrać bezpośrednio ze [strony projektu](https://github.com/RubyNaxela/TeaEditor) lub za pośrednictwem narzędzia Git z poziomu powłoki systemu)
> ##### 2. Zainstalować oprogramowanie Java Development Kit (JDK) w wersji 1.8+ [link do strony instalacji](https://www.oracle.com/pl/java/technologies/javase/javase-jdk8-downloads.html)
> ##### 3. Zainstalować narzędzie Gradle dla powłoki systemu [link do strony instalacji](https://gradle.org/install/)
> ##### 4. otworzyć katalog główny pobranej pakietu kodu źródłowego za pomocą powłoki systemu
> ##### 5. wywołać polecenie `gradlew`
> ##### 6. wywołać polecenie `gradle build`
> Skompilowany plik .jar znajduje się w katalogu `./build/libs`

## Założenia projektowe

> Program spełnia kryteria złożonego projektu obiektowego. Zostały w nim zawarte wszystkie założenia programowania obiektowego:
> 1. **Abstrakcja** została zastosowana do powiązania ze sobą elementów bazy danych o takiej samej budowie, elementów interfejsu tego samego pokroju, a także przy tworzeniu klas anonimowych do obsługi modułów sprawdzania poprawności danych.
> 2. **Enkapsulacja** danych widoczna jest głównie w klasach przechowujących obiekty bazy danych, ale również np. w module konfiguracji, obsługi danych.
> 3. **Dziedziczenie** ma miejsce i jest wykorzystywane w wielu miejscach w całym programie. Dziedziczone są klasy abstrakcyjne. Wszystkie dostosowane elementy interfejsu są klasami podrzędnymi klas bazowych tych elementów (na przykład tabela z ustawionymi właściwościami).
> 4. **Polimorfizm** wykorzystywany jest wszędzie tam, gdzie elementy dziedziczące z jednej klasy nadrzędnej bądź interfejsu używane są przez jeden algorytm (na przykład wyświetlanie błędu przy dowolnym komponencie graficznym).
> 
> Ponadto:
> 1. Wykorzystanie **interfejsów i typów wyliczeniowych** w programowaniu obiektowym.
> 2. **Obsługa plików** będąca podstawą w programie służącym do edycji pliku.
> 3. Wykorzystanie **zewnętrznych bibliotek** do takich celów jak przetwarzanie języka JSON czy HTML.
> 4. Bardzo szeroko rozwinięta **obsługa wyjątków** w celu wyeliminowania awarii programu.
> 5. **Zmiana motywu graficznego** na inny niż domyślny, aby interfejs wyglądał przyjemniej.
> 6. Opracowanie **systemu zewnętrznych zasobów** (póki co jedynie) pod kątem internacjonalizacji programu
> 7. Szczegółowy **podział projektu** na pakiety oraz klasy w celu znacznej poprawy czytelności całego projektu.
> 8. **Wykorzystanie narzędzia JavaDoc** do tworzenia dokumentacji

## Dokumentacja kodu

#### Podstawowy schemat struktury kodu

> 1. Start programu - klasa główna `Chajikan`
>   - Ustawienie języka w klasie `util.Language`
>   - Przygotowanie modułu obsługi języka JSON z biblioteki Jackson. Moduł przechowuje klasa `util.Reference`
>   - Utworzenie słownika tesktów dla wybranego języka z pliku językowego znajdującego się w folderze `/lang` katalogu zasobów. Słownik główny i zapasowy przechowuje klasa `util.Reference`
>   - Ustawienie w klasie `util.LookAndFeel` motywu Dracula ze zmianami, z biblioteki FlatLaf
>   - Utworzenie instancji okna głównego przechowywaną przez klasę `util.Reference`
>   - Przygotowanie interfejsu (stany przycisków i ekran powitalny) przez klasę `managers.WindowUpdatesManager`
> 2. Podstawowe klasy - pakiet `datatypes`
>   - Przeznaczony jest do przechowywania klas obiektów przechowujących dane
>   - Pakiet `datatypes.database` przeznaczony jest dla klas związanych z elementami bazy danych
> 3. Interfejs graficzny - pakiet `gui`
>   - W tym pakiecie zawarte są wszystkie komponenty interfejsu graficznego
>   - Okno główne programu (klasa `gui.MainWindow`) podzielone jest na 3 panele klasy `gui.panels.AbstractTablePanel` służące do wyświetlania i edycji danych i jeden panel klasy `gui.panels.PreviewPanel`, na którym wyświetlane są informacje o aktualnie zaznaczonym objekcie
>   - Pakiet `gui.components` zawiera zaawansowane komponenty interfejsu będące implementacjami podstawowych klas pakietu Swing lub ich złożeniami
>   - W pakiecie `gui.dialogs` zawarte są elementy związane z oknami dialogowymi
>   - Pakiet `gui.html` przechowuje implementacje klas biblioteki Gagawa potrzebne do tworzenia obiektów HTML będących częściami podglądów wyświetlanych w panelu  `gui.MainWindow#previewPanel`
>   - Panele, na które podzielone jest okno główne, zawarte są w pakiecie `gui.panels`

#### Szczegółowa dokumentcja

> Pełna dokumentacja kodu znajduje się [tutaj](https://rubynaxela.github.io/TeaEditor/doc/)

## Licencja

> Projekt udostępiony jest na licencji General Public License v3.0 ("Licencja"). Licencja zawarta jest w pliku "LICENSE.txt", który jest częścią pakietu kodu źródłowego. Kopię Licencji można także uzyskać pod adresem:  
> [https://www.gnu.org/licenses/gpl-3.0.txt]().