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

2 Projekt von GitHub laden und starten
------------------------------
Dieses Projekt in einer IDE klonen und in der Komandozeile den Maven Befehl *mvn package* ausführen. <br>
Danach in der Konsole *jetty:run* ausführen und im Browser *localhost:8080* eingeben. 



