# Käyttöohje

Lataa sovelluksen [suoritettava .jar-tiedosto](https://github.com/anL1/otm-harjoitustyo/releases/tag/v2.0_viikko6)

## Käynnistys

Sovellus käynnistyy komennolla
```
java -jar Opintoapp-2.0.jar
```

Ensimmäisen käynnistyksen yhteydessä OpintoApp luo käynnistyshakemistoon tiedoston _OpintoApp.db_. Kaikki suorituksen aikaiset tiedot tallennetaan tähän tietokantaan, älä siis poista tai siirrä sitä toiseen hakemistoon.

Sovellus käynnistyy kirjautumisnäkymään. Voit nyt luoda uuden käyttäjän klikkaamalla _create new user_.
Käyttäjän luomisen jälkeen kirjautuminen onnistuu palaamalla kirjautumisnäkymään ja syöttämällä käyttäjätunnuksen ja salasanan.

## Kirjautumisen jälkeen

Kirjautumisen jälkeen siirrytään näkymään, joka listaa käyttäjällesi lisäämäsi kurssit.

<img src="https://raw.githubusercontent.com/anL1/otm-harjoitustyo/master/dokumentaatio/images/welcome.png" >

Näkymässä voit lisätä käyttäjällesi lisää suoritettuja kursseja, poistaa niitä, sekä tarkastella kursseja sekä niihin liittyvää statistiikkaa lukuvuosittain. 

Näytettävän lukuvuoden valinta tapahtuu oikeassa yläkulmassa sijaitsevan tiputusvalikon sekä _filter_-napin avulla. 

Kurssin poistaminen listauksesta onnistuu kirjoittamalla kurssin nimi _delete course_ -kenttään ja painamalla nappia _delete_ (huomaa että kirjoitusasun tulee olla sama).
