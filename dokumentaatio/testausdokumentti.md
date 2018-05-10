# Testausdokumentti
Ohjelmaa on testattu automaattisilla JUnit-testeillä yksikkö- ja integraatiotasolla. 
Järjestelmätestaus on tapahtunut manuaalisesti.

## Yksikkö- ja integraatiotestaus
Tärkeimmät testit kohdistuvat sovelluslogiikkapakkaukseen _opintoapp.domain_. 
Integraatiotestit tapahtuvat käytännössä sovelluslogiikkaa testaavassa testiluokassa
_StudyServiceTest_. Näissä testeissä pyritään testaamaan sovellusta realistisilla käyttäjää
simuloivilla skenaarioilla.

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

