# Vaativuusmäärittely, opintojen seurantasovellus

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjät voivat pitää kirjaa kurssisuorituksistaan, arvosanoistaan ja
kertyneistä opintopisteistä. Käyttäjä voi myös suunnitella opintojaan eteenpäin lisäämällä
itselleen kiinnostavia kursseja, joita aikoo mahdollisesti käydä. Sovellukseen voi rekisteröidä
useita käyttäjiä, joilla kaikilla on omat yksilölliset opintoseurantansa.

## Käyttäjät

Alkuvaiheessa sovelluksessa on vain yksi käyttäjärooli _normaali käyttäjä_. Myöhemmin 
saatetaan lisätä erillisiä _pääkäyttäjiä_, joilla on enemmän oikeuksia kuin normaaleilla 
käyttäjillä.

## Toiminnallisuus

*Käyttäjä voi rekisteröityä järjestelmään käyttäjätunnuksella
**Käyttäjällä tulee olla käyttäjätunnus, nimi ja salasana
**Käyttäjätunnuksen tulee olla vähintään 3 merkkiä
*Kun tunnus on luotu, voi käyttäjä kirjautua sisään
**Jos sovellukseen yrittää kirjautua tunnuksella, jota ei ole olemassa, ilmoittaa sovellus
tästä.

### Kirjautumisen jälkeen

* Käyttäjä näkee suoritetut ja tulevat kurssinsa omissa listoissaan.
* Käyttäjä voi lisätä uuden kurssisuorituksen tai uuden tulevan kurssin.
** Kurssisuorituksella on nimi, arvosana sekä opintopistemäärä
** Sekä menneet, että tulevat kurssit näkyvät ainoastaan käyttäjälle itselleen.
* Tulevan kurssin voi poistaa listalta
* Käyttäjä voi tarkastella statistiikkaa esimerkiksi arvosanoistaan ja opintopisteistään.
* Järjestelmästä voi kirjautua ulos

## Ideoita jatkokehityksestä
Toiminnallisuuksia, jotka toteutetaan ajan salliessa yllä kuvattujen perustoiminnallisuuksien
lisäksi.
* Kurssisuoritusten tietojen muokkaus
* Tulevien kurssien järjestäminen ajankohdan mukaan
* Käyttäjä voi lisätä itselleen muistiinpanoja esim. opintoihin liittyen.
* Deadline-laskuri
* Statistiikka kaavioesitysmuotoon
* Pääkäyttäjä voi poistaa normaaleja käyttäjiä
