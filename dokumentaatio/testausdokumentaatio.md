Pelin logiikka on kattavasti testattu automaattisilla testeillä. Käyttöliittymää, ja logikkan rajapintaa käyttöliittymän kanssa ei ole testattu automaattisesti lainkaan. Käyttöliittymää on testattu käsin pelaamalla peliä ja yrittämällä rikkoa peli käyttöliittymän kautta.


Tiedetyt bugit:
* Yllättävä syöte kentta.txt tiedostossa johtaa ohjelman kaatumiseen. Ratkaisu: varmista syötteen oikeellisuus, tiedostoa muutettaessa.
* Peli ei pääty, kun aallot loppuvat. Ratkaisu: Aallot eivät saa loppua eli kentta.txt on sisällettävä mahdottoman vaikea aalto.
