Bygga system
============

Steg för steg
-------------
 1. Registreringsprogrammet använder sig av hårdkodade strängar för att avgöra vilka filer som skall användas. Om konfigurationsfilen önskas placeras någon annanstans än den fördefinerade sökvägen måste konstanten i SorterConfig.java i sorter-paketet ändras. Om start- och sluttidsfilerna i registreringsprogrammet behöver placeras i andra kataloger så måste konstanter i registration/FileNames.java ändras.
 2. Skapa JAR-filer i Eclipse.
    1.  Öppna export
	2.  Välj Runnable JAR
	3.  Välj SorterProgram för att skapa sorteringsprogram, RegisterProgram för registreringsprogram
	4.  Välj sökväg där filerna ska sparas
	5.  Välj "Package required..."
 3. JAR-filerna ska nu gå att starta genom att öppna dem med Java Runtime. På Unix-liknande system kan man först behöva högerklicka på dem och ställa in så att användaren får köra dem.

Katalogstruktur
===============
Kort beskrivning om vad de olika mapparna i projektet innehåller.

Manual (doc)
------------
Manualen finns i mappen doc som HTML-fil. Dubbelklicka på ikonen så öppnas den med förvald webbläsare.

Tester (test)
-------------
I testmappen finns det filer som behövs för några av enhetstesterna. Se kapitlet under källkod.

Källkod (src)
-------------
I src-mappen har vi två undermappar, main och test. Main innehåller all källkod för programmet uppdelad i paket, för mer om paket se kapitlet om paket. Test innehåller alla testfiler, detta inkluderar både enhetstester (java/unit) och acceptanstester (java/acceptance). Även nödvändiga filer för acceptanstesterna ligger i testmappen under resources/acceptance.

Övriga
------
Mapparna bin och target innehåller kompilerade programfiler som används vid exekvering och exportering av projektet. Mappen uml innehåller genererade UML-diagram för Green UML programmet. Mappen libs innehåller externa bibliotek som behövs för att programmet ska fungera.

Paketuppdelning
===============
I mainmappen finns det fem olika paket

enduro
------
Ingångsvägen för programmet, startar upp komponenter i GUI. De två klasser som ligger i detta paket innehåller main-metoderna för registrerings- och sorteringsprogrammen.

[gui][1]
--------
Detta paket innehållet de olika komponenterna som tillsammans bildar användargränssnittet. Ingenting modelleras här. De viktigaste klasserna är:

 * SetupFrame, som låter användare välja vilken om den skall registrera start- eller måltider. 
 * RegisterFrame, som används för att registrera tiderna.

parser
------
Paketet innehåller de klasser som används för att översätta indata till startnummer, exempelvis "1,3-5" ger startnummerna 1, 3, 4 och 5.

[racer][2]
----------
Pakektet innehåller klasser som lagrar data om de tävlande. Det används för att kunna representera, jämföra, skriva ut önskad information om en tävlande. Den viktigaste klassen är Racer.

registration
------------
Paketet innehåller en klass Register.java vilken i sig har metoder för att registrera tider till förare.

[sorting][3]
------------
Paketet innehåller klasser som gör det möjligt att läsa in filerna som registreringsprogrammet genererar och sorterar all data som fås från inläsningen beroende på tävlingstyp. Klassen ResultWriter.java skriver det sorterade resultatet till fil. Klassen Competition.java representerar en tävling på sådant sätt att sortering kan ske. Huvudklassen är Sorter. En annan viktig klass är SorterConfig som används för att konfigurera sorteringsprogrammet.

Distribuering på olika datorer
==============================
Varje dator kör ett eget registreringsprogramm som genererar filer med start och sluttider. Dessa filer läses sedan in av sorteringsprogrammet som körs på 1 dator.

Filer
=====
 * Konfigurationsfilen sorter.cfg används vid sorteringen i programmet. Filen är formaterad som JSON.
 * Filen pom.xml genereras av plugin-programmet maven och innehåller kod för att bygga en release.
 * Resterande .txt-filer är semikolon-separerade och deras namn beskriver vad dom används till.

[1]: gui.png "GUI UML"
[2]: racer.png "Racer UML"
[3]: sorting.png "Sorting UML"
