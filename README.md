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
                                <td><pre>[pole tekstowe]</pre>(zgodne z <u><b>formatem A</b></u>;<br>połączone z <u><b>ID marki herbaty</b></u>)</td>
                            </tr>
                            <tr>
                                <td><pre>name</pre>Nazwa marki</td>
                                <td><pre>[pole tekstowe]</pre></td>
                            </tr>
                            <tr>
                                <td><pre>color</pre>Kolor marki</td>
                                <td><pre>[pole tekstowe]</pre>(zgodne z <u><b>formatem B</b></u>)</td>
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
                                <td><pre>[pole tekstowe]</pre>(zgodne z <u><b>formatem A</b></u>)</td>
                            </tr>
                            <tr>
                                <td><pre>name</pre>Nazwa półki</td>
                                <td><pre>[pole tekstowe]</pre></td>
                            </tr>
                            <tr>
                                <td><pre>color</pre>Kolor półki<br></td>
                                <td><pre>[pole tekstowe]</pre>(zgodne z <u><b>formatem B</b></u>)</td>
                            </tr>
                            <tr>
                                <td><pre>tea_boxes</pre>Lista herbat</td>
                                <td>
                                    <pre>[lista]</pre>
                                    <table><tbody>
                                        <tr>
                                            <td><pre>id</pre>ID herbaty</td>
                                            <td><pre>[pole tekstowe]</pre>(zgodne z <u><b>formatem A</b></u>)</td>
                                        </tr>
                                        <tr>
                                            <td><pre>name</pre>Nazwa herbaty</td>
                                            <td><pre>[pole tekstowe]</pre></td>
                                        </tr>
                                        <tr>
                                            <td><pre>brand_id</pre>ID marki herbaty</td>
                                            <td><pre>[pole tekstowe]</pre>(zgodne z <u><b>formatem A</b></u>;<br>połączone z <u><b>ID marki</b></u>)</td>
                                        </tr>
                                        <tr>
                                            <td><pre>description</pre>Opis herbaty</td>
                                            <td><pre>[pole tekstowe]</pre></td>
                                        </tr>
                                        <tr>
                                            <td><pre>amount</pre>Posiadana ilość<br>herbaty w g</td>
                                            <td><pre>[pole liczbowe]</pre></td>
                                        </tr>
                                        <tr>
                                            <td><pre>stars</pre>Ocena herbaty</td>
                                            <td><pre>[pole liczbowe]</pre>(zgodne z <u><b>formatem C</b></u>)</td>
                                        </tr>
                                        <tr>
                                            <td><pre>brewing</pre>Instrukcje<br>parzenia<br>herbaty</td>
                                            <td>
                                                <table><tbody>
                                                    <tr>
                                                        <td><pre>temperature</pre>Temperatura<br>parzenia w °C</td>
                                                        <td><pre>[pole liczbowe]</pre></td>
                                                    </tr>
                                                    <tr>
                                                        <td><pre>time</pre>Czas parzenia<br>w min</td>
                                                        <td><pre>[pole liczbowe]</pre></td>
                                                    </tr>
                                                    <tr>
                                                        <td><pre>reuses</pre>Ilość zaparzeń</td>
                                                        <td><pre>[pole tekstowe]</pre>(zgodne z <u><b>formatem D</b></u>)</td>
                                                    </tr>
                                                    <tr>
                                                        <td><pre>grams</pre>Proporcje masowe<br>liści herbaty<br>do wody</td>
                                                        <td><pre>[pole tekstowe]</pre>(zgodne z <u><b>formatem E</b></u>)</td>
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

#### Funkcjonalności programu

> ##### 1.
> ##### 2.
> ##### 3.
> ##### 4.
> ##### 5.

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