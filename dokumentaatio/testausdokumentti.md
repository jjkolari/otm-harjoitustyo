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
muutamia yksikkötestejä testaamaan luokkien toimintaa. Testitiedostot löytyvät ylle linkatusta pakkauksesta.

### Testauskattavuus
Testauksen rivikattavuus kattaa pääosin koko sovelluslogiikan. Kattavuutta laskee ainoastaan
exceptionien testaamattomuus etenkin luokassa StudyService. Käyttöliittymää ei lasketa testauskattavuuteen mukaan.
<img src = "https://raw.githubusercontent.com/anL1/otm-harjoitustyo/master/dokumentaatio/images/jacoco.png" >

Testaamatta StudyService-luokassa jäävät esimerkiksi tilanteet, jossa 
* Käyttäjän tai kurssin lisääminen tietokantaan ei onnistu, vaan syntyy SQLException
* Kurssin poisto ei onnistu ja metodi antaa virheilmoituksen
* Arvosanojen keskiarvojen ja opintopistemäärien generointi ei onnistu, jolloin metodit antaa virheilmoituksen

Lisäksi Database-luokassa testaamatta jää tilanne, jossa konstruktorissa luotavan tietokannan luominen ei onnistu vaan syntyy virheilmoitus.

Testikattavuusraportin voi generoida komennolla
```
mvn test jacoco:report
```
Raporttia voi tarkastella selaimella. Se löytyy hakemistosta _target/site/jacoco/index.html_

## Järjestelmätestaus
Järjestelmätestaus on suoritettu manuaalisesti. Käyttökelpoisen jar-tiedoston suoritusta
on testattu ainakin Linux- sekä Windows10-ympäristöissä, joihin on ollut asennettuna Java.

## Sovellukseen jääneet ongelmat
Kaikkia virheilmoituksia ei käsitellä siten, että ilmoitus vietäisiin käyttöliittymään käyttäjälle asti. Käytännössä järjestelmätestauksessa ei ole tullut vastaan ongelmatilanteita, joissa virheilmoituksia / stacktracea tulostuisi komentoriville. Virheenkäsittely voisi kuitenkin olla johdonmukaisempaa.

Vaikka käyttöliittymä ei testaukseen kuulukkaan, käyttäjien antamien syötteiden sisältöä olisi syytä rajoittaa enemmän. Tällä hetkellä ei rajoituksien puutteesta ei synny ongelmia, kuten sql-injektioiden mahdollisuutta tai ohjelman kaatumista, mutta hyvän käytännön mukaista olisi rajoittaa erikoismerkit ym. pois esim käyttäjänimistä.
