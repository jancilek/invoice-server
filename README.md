API dokumentace k databázi faktur
API dokumentace k databázi faktur
Tato lekce poskytuje dokumentaci pro API databáze faktur včetně ukázkových požadavků a odpovědí. Bude vám užitečná hned několikrát:

Představení API - Pokud jste sem přišli ještě před programováním, získáte přehled o tom, jak bude projekt fungovat a jeho tvorba pro vás bude pak snazší.
Shrnutí API - Po dokončení projektu tato lekce shrnuje jeho funkčnost a pomáhá zapamatování principů RESTful API.
Tvorba klienta - Při programování klienta k tomuto API si můžete jednoduše ověřit, zda posíláte správně strukturovaný požadavek nebo jaký bude od API formát odpovědi.
Tvorba serveru - Při programování serveru, který poskytuje toto API, si můžete jednoduše zkontrolovat, že vaše endpointy vracejí přesně to, co mají.
Vidíme, že dokumentace je zkrátka dobrá praktika, proto i vy pro svá API vytvářejte podobnou dokumentaci, jako je tato. Můžete k tomu využít automatizovaných nástrojů jako např. Swagger nebo ji napsat ručně.

API databáze faktur
API spravuje osoby a faktury. Routování je RESTful (posíláme tedy požadavky typu GET, POST, PUT a DELETE). Komunikace probíhá ve formátu JSON. V případě chyby API vrátí chybový kód 4XX.

Osoby
API nám umožňuje spravovat fyzické a právnické osoby. Souhrnně o nich hovoří jako o osobách.

Vytvoření osoby
Pošleme požadavek POST na adresu /api/persons, který obsahuje JSON s daty nové osoby. API vrátí jako odpověď nově vytvořený objekt spolu s ID, které mu přiřadilo.

Požadavek
{
"name": "ITnetwork s.r.o.",
"identificationNumber": "05861381",
"taxNumber": "CZ05861381",
"accountNumber": "123456789",
"bankCode": "5500",
"iban": "CZ000123456789",
"telephone": "+420 123 123 123",
"mail": "redakce@itnetwork.cz",
"street": "Karlovo náměstí 290/16, Nové Město (Praha 2)",
"zip": "120 00",
"city": "Praha",
"country": "CZECHIA",
"note": "Největší IT akademie v Česku."
}
Odpověď
{
"name": "ITnetwork s.r.o.",
"identificationNumber": "05861381",
"taxNumber": "CZ05861381",
"accountNumber": "123456789",
"bankCode": "5500",
"iban": "CZ000123456789",
"telephone": "+420 123 123 123",
"mail": "redakce@itnetwork.cz",
"street": "Karlovo náměstí 290/16, Nové Město (Praha 2)",
"zip": "120 00",
"city": "Praha",
"country": "CZECHIA",
"note": "Největší IT akademie v Česku.",
"\_id": 4
}
Atribut country může nabývat pouze hodnot CZECHIA nebo SLOVAKIA.

Výpis osob
Pošleme požadavek GET na adresu /api/persons.

Odpověď
[
{
"name": "ITnetwork s.r.o.",
"identificationNumber": "05861381",
"taxNumber": "CZ05861381",
"accountNumber": "123456789",
"bankCode": "5500",
"iban": "CZ000123456789",
"telephone": "+420 123 123 123",
"mail": "redakce@itnetwork.cz",
"street": "Karlovo náměstí 290/16, Nové Město (Praha 2)",
"zip": "120 00",
"city": "Praha",
"country": "CZECHIA",
"note": "Největší IT akademie v Česku.",
"_id": 4
}
]
Detail osoby
Pošleme požadavek GET na adresu s ID osoby, např. /api/persons/4.

Odpověď
{
"name": "ITnetwork s.r.o.",
"identificationNumber": "05861381",
"taxNumber": "CZ05861381",
"accountNumber": "123456789",
"bankCode": "5500",
"iban": "CZ000123456789",
"telephone": "+420 123 123 123",
"mail": "redakce@itnetwork.cz",
"street": "Karlovo náměstí 290/16, Nové Město (Praha 2)",
"zip": "120 00",
"city": "Praha",
"country": "CZECHIA",
"note": "Největší IT akademie v Česku.",
"\_id": 4
}
Úprava osoby
Pošleme požadavek PUT na adresu s ID osoby, např. /api/persons/4.

Požadavek
{
"name": "ictdemy s.r.o.",
"identificationNumber": "05861381",
"taxNumber": "CZ05861381",
"accountNumber": "123456789",
"bankCode": "5500",
"iban": "CZ000123456789",
"telephone": "+420 123 123 123",
"mail": "redakce@itnetwork.cz",
"street": "Karlovo náměstí 290/16, Nové Město (Praha 2)",
"zip": "120 00",
"city": "Praha",
"country": "CZECHIA",
"note": "Největší IT akademie."
}
Odpověď
{
"name": "ictdemy s.r.o.",
"identificationNumber": "05861381",
"taxNumber": "CZ05861381",
"accountNumber": "123456789",
"bankCode": "5500",
"iban": "CZ000123456789",
"telephone": "+420 123 123 123",
"mail": "redakce@itnetwork.cz",
"street": "Karlovo náměstí 290/16, Nové Město (Praha 2)",
"zip": "120 00",
"city": "Praha",
"country": "CZECHIA",
"note": "Největší IT akademie.",
"\_id": 5
}
Server na pozadí původní osobu s ID 4 skutečně nevymaže, ale pouze skryje. To aby zůstala k dispozici pro existující faktury, které se dle účetního zákona nesmí již změnit. Dále se vytvoří nová osoba s upravenými hodnotami, která slouží jako náhrada původní osoby. Všimněte si rozdílného ID původní a upravené společnosti.

Smazání osoby
Pošleme požadavek DELETE na adresu s ID osoby, např. /api/persons/5. Po úspěšném smazání server odpoví s kódem 204 (No Content).

Při odstranění osoby je osoba ve skutečnosti pouze skryta, jelikož může být jako odběratel na fakturách jiných osob. Více informací viz endpoint Úprava osoby výše.

Faktury
Vytvoření faktury
Pošleme požadavek POST na adresu /api/invoices.

Požadavek
{
"invoiceNumber": 2023001,
"seller": {
"\_id": 1
},
"buyer": {
"\_id": 4
},
"issued": "2023-07-23",
"dueDate": "2023-07-30",
"product": "Článek",
"price": 100,
"vat": 21,
"note": "Tvorba Spring článků"
}
Odpověď
{
"invoiceNumber": 2023001,
"seller": {
"name": "Samuel Kodytek",
"identificationNumber": "14025582",
"taxNumber": "CZ14025582",
"accountNumber": "12345678",
"bankCode": "5500",
"iban": "CZ00123456789",
"telephone": "+420 900 900 900",
"mail": "samuel.kodytek@seznam.cz",
"street": "Tichá 288, 742 74 Tichá",
"zip": "744 01",
"city": "Frenštát pod Radhoštěm",
"country": "CZECHIA",
"note": "Gilgamesh",
"\_id": 1
},
"buyer": {
"name": "ITnetwork s.r.o.",
"identificationNumber": "05861381",
"taxNumber": "CZ05861381",
"accountNumber": "123456789",
"bankCode": "5500",
"iban": "CZ000123456789",
"telephone": "+420 123 123 123",
"mail": "redakce@itnetwork.cz",
"street": "Karlovo náměstí 290/16, Nové Město (Praha 2)",
"zip": "120 00",
"city": "Praha",
"country": "CZECHIA",
"note": "Největší IT akademie v Česku.",
"\_id": 4
},
"issued": "2023-07-23",
"dueDate": "2023-07-30",
"product": "Článek",
"price": 100,
"vat": 21,
"note": "Tvorba Spring článků",
"\_id": 4
}
Výpis faktur
Pošleme požadavek GET na adresu /api/invoices. Do query parametrů můžeme vložit filtrovací pravidla, finální adresa by tedy mohla vypadat například jako /api/invoices?minPrice=100&limit=3. Zpět dostaneme seznam prvních 3 nalezených faktur, které byly vystavené s minimální částkou 100 kč. Součástí odpovědi API jsou u faktury odpovídající objekty odběratele (buyer) a dodavatele (seller).

Filtrovací parametry
Pravidlo Popis
buyerID vybere faktury s daným dodavatelem
sellerID vybere faktury s daným odběratelem
product získá faktury, které obsahují tento produkt
minPrice vybere faktury, které mají částku vyšší nebo rovnou tomuto parametru
maxPrice vybere faktury, které mají částku nižší nebo rovnou tomuto parametru
limit získá pouze omezený počet faktur
Odpověď
[
{
"invoiceNumber": 2023001,
"seller": {
"name": "Samuel Kodytek",
"identificationNumber": "14025582",
"taxNumber": "CZ14025582",
"accountNumber": "12345678",
"bankCode": "5500",
"iban": "CZ00123456789",
"telephone": "+420 900 900 900",
"mail": "samuel.kodytek@seznam.cz",
"street": "Tichá 288, 742 74 Tichá",
"zip": "744 01",
"city": "Frenštát pod Radhoštěm",
"country": "CZECHIA",
"note": "Gilgamesh",
"_id": 1
},
"buyer": {
"name": "ITnetwork s.r.o.",
"identificationNumber": "05861381",
"taxNumber": "CZ05861381",
"accountNumber": "123456789",
"bankCode": "5500",
"iban": "CZ000123456789",
"telephone": "+420 123 123 123",
"mail": "redakce@itnetwork.cz",
"street": "Karlovo náměstí 290/16, Nové Město (Praha 2)",
"zip": "120 00",
"city": "Praha",
"country": "CZECHIA",
"note": "Největší IT akademie v Česku.",
"_id": 4
},
"issued": "2023-07-23",
"dueDate": "2023-07-30",
"product": "Článek",
"price": 100,
"vat": 21,
"note": "Tvorba Spring článků",
"_id": 4
}
]
Detail faktury
Pošleme požadavek GET na adresu s ID faktury, např. /api/invoices/4. Součástí odpovědi API jsou u faktury odpovídající objekty odběratele (buyer) a dodavatele (seller).

Odpověď
{
"invoiceNumber": 2023001,
"seller": {
"name": "Samuel Kodytek",
"identificationNumber": "14025582",
"taxNumber": "CZ14025582",
"accountNumber": "12345678",
"bankCode": "5500",
"iban": "CZ00123456789",
"telephone": "+420 900 900 900",
"mail": "samuel.kodytek@seznam.cz",
"street": "Tichá 288, 742 74 Tichá",
"zip": "744 01",
"city": "Frenštát pod Radhoštěm",
"country": "CZECHIA",
"note": "Gilgamesh",
"\_id": 1
},
"buyer": {
"name": "ITnetwork s.r.o.",
"identificationNumber": "05861381",
"taxNumber": "CZ05861381",
"accountNumber": "123456789",
"bankCode": "5500",
"iban": "CZ000123456789",
"telephone": "+420 123 123 123",
"mail": "redakce@itnetwork.cz",
"street": "Karlovo náměstí 290/16, Nové Město (Praha 2)",
"zip": "120 00",
"city": "Praha",
"country": "CZECHIA",
"note": "Největší IT akademie v Česku.",
"\_id": 4
},
"issued": "2023-07-23",
"dueDate": "2023-07-30",
"product": "Článek",
"price": 100,
"vat": 21,
"note": "Tvorba Spring článků",
"\_id": 4
}
Úprava faktury
Pošleme požadavek PUT na adresu s ID faktury, např. /api/invoices/4.

Požadavek
{
"invoiceNumber": 2023005,
"seller": {
"\_id": 1
},
"buyer": {
"\_id": 4
},
"issued": "2023-07-10",
"dueDate": "2023-08-12",
"product": "Článek",
"price": 50,
"vat": 13,
"note": "Tvorba Spring a React článků"
}
Odpověď
{
"invoiceNumber": 2023005,
"seller": {
"name": "Samuel Kodytek",
"identificationNumber": "14025582",
"taxNumber": "CZ14025582",
"accountNumber": "12345678",
"bankCode": "5500",
"iban": "CZ00123456789",
"telephone": "+420 900 900 900",
"mail": "samuel.kodytek@seznam.cz",
"street": "Tichá 288, 742 74 Tichá",
"zip": "744 01",
"city": "Frenštát pod Radhoštěm",
"country": "CZECHIA",
"note": "Gilgamesh",
"\_id": 1
},
"buyer": {
"name": "ITnetwork s.r.o.",
"identificationNumber": "05861381",
"taxNumber": "CZ05861381",
"accountNumber": "123456789",
"bankCode": "5500",
"iban": "CZ000123456789",
"telephone": "+420 123 123 123",
"mail": "redakce@itnetwork.cz",
"street": "Karlovo náměstí 290/16, Nové Město (Praha 2)",
"zip": "120 00",
"city": "Praha",
"country": "CZECHIA",
"note": "Největší IT akademie v Česku.",
"\_id": 4
},
"issued": "2023-07-10",
"dueDate": "2023-08-12",
"product": "Článek",
"price": 50,
"vat": 13,
"note": "Tvorba Spring a React článků",
"\_id": 4
}
Smazání faktury
Pošleme požadavek DELETE na adresu s ID faktury, /api/invoices/4. Po úspěšném smazání server odpoví s kódem 204 (No Content).

Výpis vystavených faktur konkrétní osobou
Pošleme požadavek GET na adresu s identifikační číslem osoby (IČ), např. /api/identification/14025582/sales. Zpět dostaneme seznam všech faktur, které byly vystavené osobou s předaným identifikačním číslem osoby (IČ). Součástí odpovědi API jsou u faktury odpovídající objekty odběratele (buyer) a dodavatele (seller).

K identifikaci osoby je použito IČ namísto ID z důvodu, že osoba mohla být upravována. Taková osoba je pak v databázi uložena několikrát a její faktury tedy mohou být navázány na několik osob s různými ID, podle toho, k jaké verzi osoby se váží. Více informací viz endpoint Úprava osoby.

Odpověď
[
{
"invoiceNumber": 2023001,
"seller": {
"name": "Samuel Kodytek",
"identificationNumber": "14025582",
"taxNumber": "CZ14025582",
"accountNumber": "12345678",
"bankCode": "5500",
"iban": "CZ00123456789",
"telephone": "+420 900 900 900",
"mail": "samuel.kodytek@seznam.cz",
"street": "Tichá 288, 742 74 Tichá",
"zip": "744 01",
"city": "Frenštát pod Radhoštěm",
"country": "CZECHIA",
"note": "Gilgamesh",
},
"buyer": {
"name": "ITnetwork s.r.o.",
"identificationNumber": "05861381",
"taxNumber": "CZ05861381",
"accountNumber": "123456789",
"bankCode": "5500",
"iban": "CZ000123456789",
"telephone": "+420 123 123 123",
"mail": "redakce@itnetwork.cz",
"street": "Karlovo náměstí 290/16, Nové Město (Praha 2)",
"zip": "120 00",
"city": "Praha",
"country": "CZECHIA",
"note": "Největší IT akademie v Česku.",
},
"issued": "2023-07-23",
"dueDate": "2023-07-30",
"product": "Článek",
"price": 100,
"vat": 21,
"note": "Tvorba Spring článků",
"_id": 4
}
]
Výpis přijatých faktur konkrétní osobou
Pošleme požadavek GET na adresu s identifikačním číslem osoby (IČ), např. /api/identification/05861381/purchases. Zpět dostaneme seznam všech faktur, které byly přijaté osobou s předaným identifikačním číslem osoby (IČO). Součástí odpovědi API jsou u faktury odpovídající objekty odběratele (buyer) a dodavatele (seller).

K identifikaci osoby je použito IČ namísto ID, viz informace u endpointu Výpis vystavených faktur konkrétní osobou.

Odpověď
[
{
"invoiceNumber": 2023001,
"seller": {
"name": "Samuel Kodytek",
"identificationNumber": "14025582",
"taxNumber": "CZ14025582",
"accountNumber": "12345678",
"bankCode": "5500",
"iban": "CZ00123456789",
"telephone": "+420 900 900 900",
"mail": "samuel.kodytek@seznam.cz",
"street": "Tichá 288, 742 74 Tichá",
"zip": "744 01",
"city": "Frenštát pod Radhoštěm",
"country": "CZECHIA",
"note": "Gilgamesh",
},
"buyer": {
"name": "ITnetwork s.r.o.",
"identificationNumber": "05861381",
"taxNumber": "CZ05861381",
"accountNumber": "123456789",
"bankCode": "5500",
"iban": "CZ000123456789",
"telephone": "+420 123 123 123",
"mail": "redakce@itnetwork.cz",
"street": "Karlovo náměstí 290/16, Nové Město (Praha 2)",
"zip": "120 00",
"city": "Praha",
"country": "CZECHIA",
"note": "Největší IT akademie v Česku.",
},
"issued": "2023-07-23",
"dueDate": "2023-07-30",
"product": "Článek",
"price": 100,
"vat": 21,
"note": "Tvorba Spring článků",
"_id": 4
}
]
Statistiky
REST API také poskytuje dva endpointy pro získání potřebných statistik.

Výpis obecných statistik
Pošleme požadavek GET na adresu /api/invoices/statistics. Vrátí se nám přehled statistik pro faktury, kde máme součet cen za letošní rok (currentYearSum), součet cen za všechny roky (allTimeSum) a počet faktur v databázi (invoicesCount).

Odpověď
{
"currentYearSum": 1400,
"allTimeSum": 1700,
"invoicesCount": 4
}
Výpis statistik pro jednotlivé společnosti
Pošleme požadavek GET na adresu /api/persons/statistics. Vrátí se nám přehled statistik pro společnosti, kde máme zobrazený identifikátor v databázi (personId), název či jméno (personName) a fakturované příjmy (revenue).

Odpověď
[
{
"personId": 1,
"personName": "Samuel Kodytek",
"revenue": 100
},
{
"personId": 4,
"personName": "ITnetwork s.r.o.",
"revenue": 0
}
]
