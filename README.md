# API Documentation for Invoice Database

This lesson provides documentation for the API of the invoice database, including sample requests and responses. This documentation will be useful to you several times:

- Introduction to API: If you came here before programming, you will get an overview of how the project will work, making it easier for you to create it later.
- API Summary: After completing the project, this lesson summarizes its functionality and helps you remember RESTful API principles.
- Client Creation: When programming a client for this API, you can easily verify whether you are sending correctly structured requests or what the response format from the API will be.
- Server Creation: When programming a server that provides this API, you can easily check that your endpoints return exactly what they should.

We see that documentation is simply good practice, so you should create similar documentation for your APIs as well. You can use automated tools like Swagger or write it manually.

## Invoice Database API

The API manages individuals and invoices. Routing is RESTful (sending requests such as GET, POST, PUT, and DELETE). Communication occurs in JSON format. In case of an error, the API returns a 4XX error code.

### Individuals

The API allows us to manage both individuals and legal entities, collectively referred to as individuals.

#### Creating an Individual

Send a POST request to the /api/persons endpoint, which contains JSON with data for the new individual. The API will respond with the newly created object along with the assigned ID.

**Request:**

```json
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
Response:

json
Copy code
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
The country attribute can only have values CZECHIA or SLOVAKIA.

Listing Individuals
Send a GET request to the /api/persons endpoint.

Response:

json
Copy code
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
Individual Details
Send a GET request to the address with the individual's ID, e.g., /api/persons/4.

Response:

json
Copy code
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
Modifying an Individual
Send a PUT request to the address with the individual's ID, e.g., /api/persons/4.

Request:

json
Copy code
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
Response:

json
Copy code
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
  "_id": 5
}
When an individual is deleted, the server actually hides the individual instead of deleting it, as it may be used as a buyer
```
