# Testausdokumentti
Ohjelmaa on testattu automaattisilla JUnit-testeillä yksikkö- ja integraatiotasolla. 
Järjestelmätestaus on tapahtunut manuaalisesti.

## Yksikkö- ja integraatiotestaus
Tärkeimmät testit kohdistuvat sovelluslogiikkapakkaukseen [opintoapp.domain](https://github.com/anL1/otm-harjoitustyo/tree/master/OpintoApp/src/test/java/domain). 
Integraatiotestit tapahtuvat käytännössä sovelluslogiikkaa testaavassa testiluokassa
[StudyServiceTest](https://github.com/anL1/otm-harjoitustyo/blob/master/OpintoApp/src/test/java/domain/StudyServiceTest.java). Näissä testeissä pyritään testaamaan sovellusta realistisilla käyttäjää
simuloivilla skenaarioilla.

Integraatiotestit käyttävät tietokantaa ja tallentavat tietoja testauksen yhteydessä luotavaan testitietokantaan _TestOpintoApp.db_. Tietokanta luodaan projektin juurihakemistoon ja tyhjennetään testauksen päätteeksi.

Integraatiotestien lisäksi muihin _opintoapp.domain_-pakkauksen luokkiin on kohdistettu
muutamia yksikkötestejä testaamaan luokkien toimintaa.

### Testauskattavuus
Testauksen rivikattavuus kattaa pääosin koko sovelluslogiikan. Kattavuutta laskee ainoastaan
exceptionien testaamattomuus etenkin luokassa StudyService. 
//kuva

## Järjestelmätestaus
Järjestelmätestaus on suoritettu manuaalisesti. Käyttökelpoisen jar-tiedoston suoritusta
on testattu ainakin Linux- sekä Windows10-ympäristöissä, joihin on ollut asennettuna Java.

## Sovellukseen jääneet ongelmat
todo

