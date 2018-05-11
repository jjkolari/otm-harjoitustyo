# OpintoApp
Kurssille Ohjelmistotekniikan menetelmät toteutettava desktop-sovellus. Sovellus on tarkoitettu opiskelijoille kurssisuoritusten seuraamiseen. Sovelluksen avulla voi mm. seurata omia kurssisuorituksiaan lukuvuosittain ja tarkastella omia keskiarvojaan sekä opintopistemäärää vuositasolla.

## Dokumentaatio
[Vaativuusmäärittely](https://github.com/anL1/otm-harjoitustyo/blob/master/dokumentaatio/vaativuusmaarittely.md)<br/>

[Työaikakirjanpito](https://github.com/anL1/otm-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)<br/>

[Arkkitehtuuri](https://github.com/anL1/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/anL1/otm-harjoitustyo/blob/master/dokumentaatio/testausdokumentti.md)

[Käyttöohje](https://github.com/anL1/otm-harjoitustyo/blob/master/dokumentaatio/Kayttoohje.md)

## Releaset
[Viikko 5](https://github.com/anL1/otm-harjoitustyo/releases)

[Viikko 6](https://github.com/anL1/otm-harjoitustyo/releases/tag/v2.0_viikko6)

[Loppupalautus](https://github.com/anL1/otm-harjoitustyo/releases/tag/v.3.0)

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

### Javadoc
Javadoc generoidaan komennolla

`mvn javadoc:javadoc`

Javadocia voi tarkastella tiedostosta _target/site/apidocs/index.html_
