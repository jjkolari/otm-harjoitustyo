# Käyttöohje

Lataa sovelluksen [suoritettava .jar-tiedosto](https://github.com/anL1/otm-harjoitustyo/releases/tag/v.3.0)

## Käynnistys

Sovellus käynnistyy komennolla
```
java -jar Opintoapp-3.0.jar
```

Ensimmäisen käynnistyksen yhteydessä OpintoApp luo käynnistyshakemistoon tiedoston _OpintoApp.db_. Kaikki suorituksen aikaiset tiedot tallennetaan tähän tietokantaan, älä siis poista tai siirrä sitä toiseen hakemistoon.

Sovellus käynnistyy kirjautumisnäkymään. Voit nyt luoda uuden käyttäjän klikkaamalla _create new user_, jolloin sovellus siirtyy Luo uusi käyttäjä -näkymään. Käyttäjänimen ja salasanan tulee olla vähintään 3 merkkiä pitkät.

### Näkymä käyttäjän luomiseen
<img src="https://raw.githubusercontent.com/anL1/otm-harjoitustyo/master/dokumentaatio/images/newuser.png" width="600" >

Käyttäjän luomisen jälkeen kirjautuminen onnistuu palaamalla kirjautumisnäkymään napilla _back_ ja syöttämällä käyttäjätunnuksen ja salasanan. Sovellus ilmoittaa tuntemattomasta käyttäjästä.

### Kirjautumisnäkymä
<img src="https://raw.githubusercontent.com/anL1/otm-harjoitustyo/master/dokumentaatio/images/loginmanual.png" width="600" >

## Kirjautumisen jälkeen

Kirjautumisen jälkeen siirrytään näkymään, joka listaa käyttäjällesi lisäämäsi kurssit.

<img src="https://raw.githubusercontent.com/anL1/otm-harjoitustyo/master/dokumentaatio/images/welcome.png" width="600" >

Näkymässä voit lisätä käyttäjällesi lisää suoritettuja kursseja, poistaa niitä, sekä tarkastella kursseja sekä niihin liittyvää statistiikkaa lukuvuosittain. 

Näytettävän lukuvuoden valinta tapahtuu oikeassa yläkulmassa sijaitsevan tiputusvalikon sekä _filter_-napin avulla. 

Kurssin poistaminen listauksesta onnistuu kirjoittamalla kurssin nimi _delete course_ -kenttään ja painamalla nappia _delete_ (huomaa että kirjoitusasun tulee olla sama).
