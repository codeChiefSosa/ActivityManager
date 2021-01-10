# Activitymanager

# Opis
Aplikacja umożliwiająca tworzenie konta użytkownika w lokalnej bazie danych SQLite, dodawanie do niego treningów/aktywności oraz przeglądanie ich historii.

# Biblioteki
Room - ORM dla SQLite
Hilt - Biblioteka do Depedency Injection

# Applcation flow - screenshoty i opis

![Screenshot](readme_images/walidacja_danych.png)
Podstawowa walidacja danych.
![Screenshot](readme_images/hashed_password.png)
Zahashowane haslo w tabeli user
![Screenshot](readme_images/main_screen_without_training.png)
Glowny ekran jesli uzytkownik nie ma dodanego zadnego treningu.
![Screenshot](readme_images/traning_screen.png)
Ekran gdzie uzytkownik dodaje opis swojego treningu oraz gdzie uruchamia customowy timer aby dodać jego wartość do tabeli.
![Screenshot](readme_images/main_screen_with_training.png)
Glowny ekran jesli uzytkownik posiada dodany trening.
![Screenshot](readme_images/training_history.png)
Ekran wyswietlajacy historie treningów korzystający z RecyclerView
