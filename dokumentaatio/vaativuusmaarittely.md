# Vaativuusmäärittely, opintojen seurantasovellus

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjät voivat pitää kirjaa kurssisuorituksistaan, arvosanoistaan ja
kertyneistä opintopisteistä. Kertyneitä suorituksia voi tarkastella lukuvuositasolla, tällöin sovelluksesta näkee esim. tiettynä lukuvuotena kertyneet opintopisteet ja vuoden keskiarvon. Sovellukseen voi rekisteröidä
useita käyttäjiä, joilla kaikilla on omat yksilölliset opintoseurantansa.

## Käyttäjät

Sovelluksessa on tällä hetkellä vain yksi käyttäjärooli _normaali käyttäjä_. Jatkokehityksessä voisi lisätä erillisiä _pääkäyttäjiä_, joilla on enemmän oikeuksia kuin normaaleilla käyttäjillä.

## Toiminnallisuus

* Käyttäjä voi rekisteröityä järjestelmään käyttäjätunnuksella
	* Käyttäjällä tulee olla käyttäjätunnus, nimi ja salasana
	* Käyttäjätunnuksen ja salasanan tulee olla vähintään 3 merkkiä
* Kun tunnus on luotu, voi käyttäjä kirjautua sisään
	* Jos sovellukseen yrittää kirjautua tunnuksella, jota ei ole olemassa, ilmoittaa sovellus
tästä.

### Kirjautumisen jälkeen

* Käyttäjä näkee henkilökohtaisen kurssilistauksensa.
* Käyttäjä voi lisätä uuden kurssisuorituksen tai poistaa vanhan suorituksen listauksesta.
	* Kurssisuorituksella on nimi, arvosana, opintopistemäärä sekä suorituslukuvuosi
	* Kurssit ja niiden statistiikka näkyvät tällä hetkellä ainoastaan käyttäjälle itselleen.
* Päänäkymässä käyttäjä näkee statistiikkaa esimerkiksi arvosanoistaan ja opintopisteistään.
* Kurssilistausta voi rajata lukuvuoden mukaan _filter_-napilla
* Järjestelmästä voi kirjautua ulos

## Ideoita jatkokehityksestä
Toiminnallisuuksia, joita jatkokehityksessä voisi toteuttaa:
* Kurssin valinta valmiista listauksesta
* Kurssisuoritusten tietojen muokkaus
* Poistamisen muokkaaminen intuitiivisemmaksi
* Lukukausien erittely (kevät, kesä, syksy...)
* Tulevien kurssien lisääminen erilliseen listaukseen (opintojen suunnittelu)
* Statistiikka kaavioesitysmuotoon (esim pylväskaavio)
* Kaikkien käyttäjien koostettu statistiikka ja/tai yksittäisen kurssin statistiikka, jota voisi tarkastella
* Pääkäyttäjä voi poistaa normaaleja käyttäjiä
