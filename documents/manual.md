# Käyttöohje

Lataa sovelluksen uusin versio [releases-listasta](https://github.com/H4m5t3r/Lukuvinkkikirjasto/releases). Valitse tiedosto Lukuvinkkikirjasto.jar, ja lataa se koneellesi. 

Ohjelmaa käytetään komentoriviltä. Ohjelma on englanninkielinen.

## Ohjelman käynnistäminen
Ohjelma käynnistetään komennolla 
```
java -jar Lukuvinkkikirjasto.jar
```
Ohjelma luo käynnistämisen yhteydessä käynnistyshakemistoon tietokantatiedoston, jos sitä ei vielä ollut olemassa. Tätä tiedostoa ei tule poistaa, jos haluaa vielä käyttää käytön aikana tallentamiaan tietoja. Jos tiedot saa hävittää, niin tietokantatiedoston voi poistaa. Jos tietokantatiedoston on poistanut, niin ohjelma luo seuraavalla käynnistyskerralla uuden tietokantatiedoston.

## Aloitus
Sovellus avautuu näkymään, jossa luetellaan toiminnot, joita ohjelmassa on:

Here are the available commands:  
exit           - close the application  
add general    - add a general reading tip  
add book       - add a new book  
add podcast    - add a new podcast  
add blog       - add a new blog  
add video      - add a new video  
list           - list reading tips by choice  
search         - search from tips by header or description  
edit           - edit a tip  
mark as read   - mark a reading tip as read  
mark as unread - mark a reading tip as unread  
delete         - delete a reading tip  

## Uuden lukuvinkin lisääminen
Toimintolistauksen add ... -komennoilla pääsee lisäämään uusia lukuvinkkejä, halutun tyypin mukaan. "Add general" lisää yleisen lukuvinkin, jolle annetaan vain otsikko ja kuvaus. Komennoilla "add book", "add podcast", "add blog" ja "add video" pääsee lisäämään komennossa olevan tyypin mukaisen vinkin, joille on määritelty tyyppikohtaiset kentät. Lukuvinkille annetaan ohjelman kysymät tiedot, tai kentät voi jättää myös tyhjäksi, jos ei halua täyttää kaikkia kohtia. Lukuvinkki tallentuu automaattisesti, kun sille on annettu kaikki tiedot.  

## Lukuvinkkien selaaminen
Toimintolistauksessa komennolla "list" pääsee tulostamaan listan tallennetuista lukuvinkeistä. Lisäkomennolla "unread" voi valita tulostettavaksi vain lukemattomat vinkit, ja lisäkomennolla "read" voi valita tulostettavaksi vain luetut vinkit. Millä tahansa muulla syötteellä tai tyhjällä saa tulostettua kaikki lukuvinkit.

## Lukuvinkkien etsiminen
Toimintolistauksessa komennolla "search" pääsee hakemaan annettavalla syötteellä (merkkijono) tallennetuista lukuvinkeistä ne vinkit, joissa annettu syöte esiintyy merkkijonona. Merkkijono voi esiintyä missä tahansa kentässä, eikä se erottele isoja ja pieniä kirjaimia.  

## Lukuvinkkien muokkaaminen
Toimintolistauksessa komennolla "edit" pääsee muokkaamaan halutun lukuvinkin tietoja. Ohjelma kysyy ensin muokattavan vinkin ID-numeroa, jonka näkee vinkkilistauksessa (komento "list"). Tämän jälkeen ohjelma kysyy, mitä tietoa halutaan muuttaa, minkä jälkeen pääsee antamaan uuden tiedon. Muutos tallentuu automaattisesti, kun uuden  tiedon on antanut. Jos ID-numeron antamisen jälkeen ei haluakaan muokata kyseistä vinkkiä, pääsee palaamaan takaisin alun komentolistaukseen antamalla tyhjän syötteen tai syötteeksi jonkin muun merkkijonon kuin ohjelman antamassa kenttälistauksessa. 

Komennolla "mark as read" pääsee merkitsemään halutun lukuvinkin luetuksi. Ohjelma kysyy muokattavan vinkin ID-numeroa. Tämän jälkeen ohjelma muuttaa halutun vinkin tilan luetuksi. Muutos tallentuu automaattisesti, kun ID-numeron on antanut. Komennolla "mark as unread" voi muuttaa vastaavasti lukuvinkin tilan takaisin lukemattomaksi.

Jos muokkaamistoiminnoissa ID-numerona antaa muun kuin numeron, ohjelma pyytää korjaamaan syötteen ja antamaan vain numeroita. Jos annettua ID-numeroa ei ole millään vinkillä, ohjelma ilmoittaa että ID-numeroa ei löydy, ja palaa komentolistaukseen. 

## Lukuvinkkien poistaminen
Toimintolistauksessa komennolla "delete" pääsee poistamaan halutun lukuvinkin. Ohjelma kysyy ensin poistettavan vinkin ID-numeroa. Jos annetulla ID-numerolla löytyy lukuvinkki, ohjelma pyytää varmistamaan poiston komennolla "y". Millä tahansa muulla syötteellä poistumaan poistamistoiminnosta. Jos annettua ID-numeroa ei löydy, ohjelma palaa toimintolistaukseen. 

## Ohjelman sulkeminen
Ohjelma suljetaan toimintolistauksessa komennolla "exit". 



