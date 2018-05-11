# Arkkitehtuurikuvaus

## Rakenne
Pakkaustasolla ohjelma noudattaa kerrosarkkitehtuuria.

<img src="https://raw.githubusercontent.com/anL1/otm-harjoitustyo/master/dokumentaatio/images/pakkaukset.png" >

Riippuvuudet on merkitty katkoviivoilla. Pakkaus _opintoapp.ui_ on riippuvainen pakkauksesta _opintoapp.domain_ sillä käyttöliittymä käyttää sovelluslogiikkaluokkaa _StudyService_. _domain_ pakkaus puolestaan on riippuvainen pitkäaikaistalletuksesta vastaavasta pakkauksesta _opintoapp.dao_.

## Käyttöliittymä
Käyttöliittymästä vastaavat luokat sijaitsevat pakkauksessa [opintoapp.ui](https://github.com/anL1/otm-harjoitustyo/tree/master/OpintoApp/src/main/java/opintoapp/ui). Käyttöliittymä on toteutettu JavaFX:llä FXML-formaattia käyttäen. FXML-tiedostot sijaitsevat kansiossa [resources/fxml](https://github.com/anL1/otm-harjoitustyo/tree/master/OpintoApp/src/main/resources/fxml). Sovelluksen alustamisesta ja käynnistämisestä vastaa pääluokka [OpintoAppMain](https://github.com/anL1/otm-harjoitustyo/blob/master/OpintoApp/src/main/java/opintoapp/ui/OpintoAppMain.java).

Käyttöliittymä muodostuu kolmesta näkymästä: 
* Kirjautumisnäkymä
* Uuden käyttäjän luominen -näkymä
* Käyttäjän henkilökohtainen näkymä (kurssilistaus)

Jokaiselle näkymälle on oma FXML-tiedostonsa, sekä tiedostoon liitetty kontrolleriluokka. Jokainen näkymä alustetaan omaksi scene-oliokseen _OpintoAppMain_-luokassa. Näkyvillä eli asetettuna sovelluksen stageen on yksi näkymä kerrallaan.

Käyttöliittymä on pyritty erottamaan täysin sovelluslogiikasta. Käyttöliittymäkontrollereille asetetaan scene-olioiden alustuksen yhteydessä sovelluslogiikka-olio _StudyService_, jonka
metodeja kutsutaan käyttöliittymästä.

Jokaiselle kontrolleriluokalle asetetaan myös _OpintoAppMain_-luokan ilmentymä oliomuuttujaksi. Näin kontrolleri pääsee vaihtamaan stageen-asetettua näkymää, kun käyttäjä navigoi käyttöliittymässä.

## Sovelluslogiikka
Sovelluslogiikan perustan muodostavat luokat [User](https://github.com/anL1/otm-harjoitustyo/blob/master/OpintoApp/src/main/java/opintoapp/domain/User.java), [Course](https://github.com/anL1/otm-harjoitustyo/blob/master/OpintoApp/src/main/java/opintoapp/domain/Course.java) 
ja [CompletedCourse](https://github.com/anL1/otm-harjoitustyo/blob/master/OpintoApp/src/main/java/opintoapp/domain/CompletedCourse.java), jotka kuvaavat käyttäjiä sekä kursseja.
 _Course_-luokka kuvaa kurssia, jolla on nimi sekä opintopistemäärä. _CompletedCourse_ laajentaa _Course_-luokkaa liittämällä siihen arvosanan. 

Tällä hetkellä sovelluslogiikka käyttää käytännössä ainoastaan _CompletedCourse_-olioita, sillä sovellus tukee toistaiseksi ainoastaan _suoritettujen kurssien_ lisäämistä käyttäjän listaukseen (eli kurssille täytyy määritellä arvosana). Tällä luokkarakenteella sovellusta olisi kuitenkin helppo laajentaa vielä _tulevien kurssien_ lisäämiseen käyttäjälle esimerkiksi erilliseen listaukseen.

Toiminnallisuudesta vastaa luokka [StudyService](https://github.com/anL1/otm-harjoitustyo/blob/master/OpintoApp/src/main/java/opintoapp/domain/StudyService.java). _StudyService_ pääsee käsiksi tietokantaan talletettuihin käyttäjien ja kurssien tietoihin pakkauksessa _opintoapp.dao_ sijaitsevien luokkien [UserDao](https://github.com/anL1/otm-harjoitustyo/blob/master/OpintoApp/src/main/java/opintoapp/dao/UserDao.java) ja [CourseDao](https://github.com/anL1/otm-harjoitustyo/blob/master/OpintoApp/src/main/java/opintoapp/dao/CourseDao.java) kautta. Riippuvuudet injektoidaan luokkaan konstruktorin yhteydessä.

Luokkien suhteita kuvaava luokka/pakkauskaavio:

<img src="https://raw.githubusercontent.com/anL1/otm-harjoitustyo/master/dokumentaatio/images/luokkapakkauskaavioLopullinen.png" >

## Tietojen talletus
Tietojen talletuksesta vastaavat luokat sijaitsevat pakkauksessa [opintoapp.dao](https://github.com/anL1/otm-harjoitustyo/tree/master/OpintoApp/src/main/java/opintoapp/dao).
 Tiedot talletetaan sqliten avulla ensimmäisen käynnistyksen yhteydessä luotavaan tietokantaan.
 Käyttäjien tiedot tallennetaan luokan [UserDao](https://github.com/anL1/otm-harjoitustyo/blob/master/OpintoApp/src/main/java/opintoapp/dao/UserDao.java) avulla ja käyttäjiin liittyvien kurssien tiedot luokan [CourseDao](https://github.com/anL1/otm-harjoitustyo/blob/master/OpintoApp/src/main/java/opintoapp/dao/CourseDao.java) avulla. UserDao ja CourseDao on toteutettu interface-luokkien _UserDaoApi_ ja _CourseDaoApi_ toteutuksina. Mikäli tiedon talletustapaa haluttaisiin muuttaa tai luokkien toteutuksia vaihtaa, se onnistuisi interfacen kautta helposti.

### Tietokanta
Tietokannan luomisen yhteydessä luodaan seuraavat tietokantataulut:

```
CREATE TABLE User (
id integer PRIMARY KEY,
username varchar(200),
name varchar(200),
password varchar(200)
);
```

```
CREATE TABLE Course (
id integer PRIMARY KEY,
name varchar(200),
credits integer,
grade integer,
semester varchar(20),
user_username varchar(200),
FOREIGN KEY (user_username) REFERENCES User(username)
);
```
Taulut luodaan _Database_-luokan konstruktorissa, joka luo tietokannan _OpintoApp.db_ sovelluksen suoritushakemistoon. Sovellus olettaa, että tietokantatiedosto löytyy jatkossakin
suoritushakemistosta. Mikäli tiedostoa ei käynnistyksen yhteydessä löydy, luo sovellus uuden tietokantatiedoston.

Käyttäjien salasanat talletetaan kryptattuina, joten niitä ei tietokannasta pääse lukemaan. Kryptaus suoritetaan käyttämällä jBcrypt-kirjastoa.

Testauksessa hyödynnetään testejä varten luotavaa tietokantaa _TestOpintoApp.db_, joka luodaan testien suorittamisen yhteydessä projektin juurihakemistoon. Testitietokanta tyhjennetään testien suorittamisen
jälkeen.

## Toiminnallisuus
//todo lisää tänne
### Sisäänkirjautuminen
Sekvenssikaavio kuvaa luokkatasolla kuinka sovelluksen sisäänkirjautuminen on toteutettu.

<img src="https://raw.githubusercontent.com/anL1/otm-harjoitustyo/master/dokumentaatio/images/LogIn.png" >

## Ohjelmaan jääneet heikkoudet
