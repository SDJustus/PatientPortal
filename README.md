PatientPortal Installationsanleitung
==============

Das ist eine kurze Anleitung, wie die Web Applikation zum Laufen
gebracht wird


1 MariaDB installieren 
----------------------------------------------------------
HeidiSQL (SequelPro als Mac Alternative) starten und im Verbindungsmanager folgende Werte eintragen: <br> 
Benutzername: root <br>
Passwort: root <br>
Port: 3306 <br>
Nach dem Herstellen der Verbindung eine neue Datenbank mit dem Namen *testdb* erstellen.


Link zur MariaDB Installationsanleitung für Mac(via Homebrew): 
https://mariadb.com/kb/en/library/installing-mariadb-on-macos-using-homebrew/

2 Vaadin Lizenzschlüssel einfügen
--------------------------------------

Um das Projekt starten zu können, muss eine Datei mit dem Lizenzschlüssel für 
Vaadin Charts im Homeverzeichnis (Users/&lt;username&gt;) abgelegt werden. <br>
Dazu muss sich auf <a href=" vaadin.com/pro/licenses">vaadin.com</a> eingeloggt
und unter Account -> My Services -> Licences die Datei für Vaadin Charts 
heruntergeladen werden.

3 Projekt von GitHub laden und starten
------------------------------
Maven und Git installieren und danach dieses Projekt in einer IDE klonen. Nun in der Komandozeile den Maven Befehl *mvn install* ausführen. <br>
Danach in der Konsole *jetty:run* ausführen und im Browser *localhost:8080* eingeben. 



