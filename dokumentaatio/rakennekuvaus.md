Ohjelma on Jaettu kolmeen osaan. Käyttöliittymään, Logiikkaan ja Piirrettäviin olioihin. Käyttöliittymän muodostavat Frame-, Klikkikuuntelija- ja Piirtoalusta-luokat. Kaikki input/output menee Frame-luokan kautta. Input Klikkikuuntelijan avulla ja graafinen output Piirtoalustan avulla.


Logiikan muodostavat Pelilogiikka ja sen apuluokat (luokkakaaviossa ovat yhteydessä vain Pelilogiikkaan), sekä Pelitila. Pelilogiikalla on metodeja, joita Event Handlerit kutsuvat. Pelilogiikka kutsuu Pelitilan ja Piirtoalustan metodeja. Pelitila hallinnoi piirrettäviä olioita.


Piirrettävät oliot ovat olioita, jotka näkyvät pelimaailmassa Piirtoalustan avulla. Kaikki piirrettävät oliot ovat tallennettuna Pelitilan listoihin. Kaikki piirrettävät oliot perivät luokan Sijainnillinen, eli niillä on koordinaatit pelimaailmassa. Piirrettävät oliot ovat vuorovaikutuksessa keskenään Pelitilan määräämällä tavalla.
