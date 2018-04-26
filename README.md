# OpintoApp
Kurssille Ohjelmistotekniikan menetelmät toteutettava desktop-sovellus
## Dokumentaatio
[Vaativuusmäärittely](https://github.com/anL1/otm-harjoitustyo/blob/master/dokumentaatio/vaativuusmaarittely.md)<br/>

[Työaikakirjanpito](https://github.com/anL1/otm-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)<br/>

[Arkkitehtuuri](https://github.com/anL1/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Releaset
[Viikko 5](https://github.com/anL1/otm-harjoitustyo/releases)

## Komentorivitoiminnot
Ohjelman voi suorittaa komentoriviltä komennolla</br>

`mvn compile exec:java -Dexec.mainClass=opintoapp.ui.OpintoAppMain`</br>

### Testaus
Testit suoritetaan komennolla 

`mvn test`</br>

Testikattavuusraportti generoidaan komennolla 

`mvn test jacoco:report`</br>

### Checkstyle
Checkstyle ajetaan komennolla 

`mvn jxr:jxr checkstyle:checkstyle`</br>

Virheilmoitukset näkyvät tiedostossa _target/site/checkstyle.html_

### Suoritettava .jar-tiedosto
Jarin generointi suoritetaan komennolla

`mvn package`

Komento generoi suoritettavan .jar-tiedoston hakemistoon _target_
