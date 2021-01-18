# Chajikan 茶時間

### Czym jest program

> Aplikacja służy do wygodnej edycji bazy danych zawierającej dane dotyczące kolekcji herbat.

## Szczegóły działania

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
                                <td><pre>[tekst]</pre>(zgodne z <b>formatem A</b>;<br>połączone z <b>ID marki herbaty</b>)</td>
                            </tr>
                            <tr>
                                <td><pre>name</pre>Nazwa marki</td>
                                <td><pre>[tekst]</pre></td>
                            </tr>
                            <tr>
                                <td><pre>color</pre>Kolor marki</td>
                                <td><pre>[tekst]</pre>(zgodne z <b>formatem B</b>)</td>
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
                                <td><pre>[tekst]</pre>(zgodne z <b>formatem A</b>)</td>
                            </tr>
                            <tr>
                                <td><pre>name</pre>Nazwa półki</td>
                                <td><pre>[tekst]</pre></td>
                            </tr>
                            <tr>
                                <td><pre>color</pre>Kolor półki<br></td>
                                <td><pre>[tekst]</pre>(zgodne z <b>formatem B</b>)</td>
                            </tr>
                            <tr>
                                <td><pre>tea_boxes</pre>Lista herbat</td>
                                <td>
                                    <pre>[lista]</pre>
                                    <table><tbody>
                                        <tr>
                                            <td><pre>id</pre>ID herbaty</td>
                                            <td><pre>[tekst]</pre>(zgodne z <b>formatem A</b>)</td>
                                        </tr>
                                        <tr>
                                            <td><pre>name</pre>Nazwa herbaty</td>
                                            <td><pre>[tekst]</pre></td>
                                        </tr>
                                        <tr>
                                            <td><pre>brand_id</pre>ID marki herbaty</td>
                                            <td><pre>[tekst]</pre>(zgodne z <b>formatem A</b>;<br>połączone z <b>ID marki</b>)</td>
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
                                            <td><pre>[liczba]</pre>(zgodne z <b>formatem C</b>)</td>
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
                                                        <td><pre>[tekst|null]</pre>(zgodne z <b>formatem D</b>)</td>
                                                    </tr>
                                                    <tr>
                                                        <td><pre>grams</pre>Proporcje masowe<br>liści herbaty<br>do wody</td>
                                                        <td><pre>[tekst]</pre>(zgodne z <b>formatem E</b>)</td>
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
> - **format A**:\
    małe litery alfabetu łacińskiego oraz znak ``_``\
    wyrażenie regularne:``^([a-z]|_)*$``\
    przykłady: ``teatime``,``japan_gyokuro_hiki``
> - **format B**:\
    6 znaków z zakresu ``0-9`` lub ``a-f`` lub ``A-F``, poprzedzone znakiem ``#``\
    wyrażenie regularne:``^#[0-f0-F]{6}$``\
    przykłady: ``#a8b349f``,``#F4AC99``
> - **format C**:\
    wielokrotność ``0.5`` z zakresu ``[1;5]``\
    wyrażenie regularne:``^[1-5](\.5)?$``\
    przykłady: ``4``,``2.5``
> - **format D**:\
    dowolna liczba całkowita, opcjonalnie ``/1`` za nią\
    wyrażenie regularne:``^\d+(\/1)?$``\
    przykłady: ``3/1``,``10``
> - **format E**:\
    pary: liczba``:``liczba całkowita oddzielone znakami ``/``\
    wyrażenie regularne:``^(((?!^\.$)(?!^(\d*\.\d*){2,}$)[\d.])+:\d+)?(\/((?!^\.$)(?!^(\d*\.\d*){2,}$)[\d.])+:\d+)*$``\
    przykłady: ``4:250/11:600``,``40:200``,``3.5:250/10:600/15.5:1000``

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

#### Charakterystyka i funkcjonalności programu

> Aplikacja działa w oknie z interfejsem graficznym, którego elementami są:
> - trzy panele z tabelami umożliwiające szybki przegląd, dodawanie, usuwanie oraz edycję:
>   - marek herbat
>   - półek z herbatami
>   - herbat z półek
> - panel z podglądem aktualnie zaznaczonego elementu
> - pasek menu z zakładkami:
>   - Plik:
>     - Nowa baza danych... (``⌘+N``/``Ctrl+N``) - tworzy nowy plik bazy danych
>     - Otwórz... (``⌘+O``/``Ctrl+O``) - otwiera bazę danych
>     - Zapisz... (``⌘+S``/``Ctrl+S``) - zapisuje aktualnie otwartą bazę danych (otwiera okno dialogowe zapisu pliku, jeśli
>       aktualnie otwarta baza została utworzona jako nowy plik podczas działania programu)
>     - Zapisz jako... (``⌘+S``/``Ctrl+S``) - zapisuje aktualnie otwartą bazę danych (wymusza okno dialogowe zapisu pliku)
>     - Zamknij... (``⌘+W``/``Ctrl+W``) - zamyka aktualnie otwartą bazę danych
>     - Wyjdź... (``⌘+Q``/``Ctrl+Q``) - zamyka program
>   - Edycja
>     - Cofnij (``⌘+Z``/``Ctrl+Z``) - do zaimplementowania
>     - Ponów (``⌘+Shift+Z``/``Ctrl+Y``) - do zaimplementowania
> 
> Dane są wczytywane do programu oraz zapisywane z/do pliku w formacie JSON zgodnego ze schematem zamieszczonym wyżej.

## Założenia projektowe

> ##### Program
> ##### 1.
> ##### 2.
> ##### 3.
> ##### 4.
> ##### 5.

### Instalacja

> (Releases)

### Kompilacja ze źródła

> ##### 1. Sklonować repozytorium
> ##### 2. Zainstalować JDK 1.8+
> ##### 3. Zainstalować Gradle
> ##### 4. wykonać: gradlew.bat
> ##### 5. wykonać: gradle build

### Dokumentacja

#### Podstawowy schemat struktury kodu

#### Szczegółowa dokumentcja

> Pełna dokumentacja kodu znajduje się [tutaj](https://rubynaxela.github.io/TeaEditor/doc/)

### Licencja

> Projekt udostępiony jest na licencji General Public License v3.0 ("Licencja"). Licencja zawarta jest w pliku "LICENSE.txt",  
> który jest częścią pakietu kodu źródłowego. Kopię Licencji można także uzyskać pod adresem:  
> [https://www.gnu.org/licenses/gpl-3.0.txt]().