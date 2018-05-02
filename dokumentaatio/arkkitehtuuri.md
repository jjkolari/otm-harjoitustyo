# Arkkitehtuurikuvaus

## Rakenne
Pakkaustasolla ohjelma noudattaa kerrosarkkitehtuuria.

//kuva

Riippuvuudet on merkitty katkoviivoilla. Pakkaus _opintoapp.ui_ on riippuvainen pakkauksesta _opintoapp.domain_ sillä käyttöliittymä käyttää sovelluslogiikkaluokkaa _StudyService_. _domain_ pakkaus puolestaan on riippuvainen pitkäaikaistalletuksesta vastaavasta pakkauksesta _opintoapp.dao_.

### Pakkaus/luokkarakenne

<img src="https://raw.githubusercontent.com/anL1/otm-harjoitustyo/master/dokumentaatio/images/pakkauskaavio.png" >

## Sovelluslogiikka


## Toiminnallisuus

### Sisäänkirjautuminen
Sekvenssikaavio kuvaa luokkatasolla kuinka sovelluksen sisäänkirjautuminen on toteutettu.

<img src="https://raw.githubusercontent.com/anL1/otm-harjoitustyo/master/dokumentaatio/images/LogIn.png" >
