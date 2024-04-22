# Spring Invoice Database
Ukázkový spring boot projekt vytvořen podle SpringMovieDatabase z lekce ITnetwork kurzu
Filmová databáze v Java Spring Boot

Zdroj: https://www.itnetwork.cz/java/spring-boot/filmova-databaze/filmova-databaze-v-java-spring-boot-vlastni-konfigurace

# API
Zde rychlý přehled aneb doctool stále nepracuje správně.

### PersonDTO:

    Long _id;
    String name;
    String indentificationNumber;
    String taxNumber;
    String accountNumber;
    String bankCode;
    Integer IBAN;
    String telephone;
    String mail;
    String street;
    String ZIP;
    String city;
    Countries country;
    String note;

### Endpointy pro Person entitu:

    POST /api/persons
    GET /api/persons - vrací všechny persons
    GET /api/persons/{id} - vratí záznam osoby (i hidden záznam)
    GET /api/identification/{identificationNumber}/sales - najde prodeje pro dané IČ
    GET /api/identification/{identificationNumber}/purchases - najde koupě pro dané IČ
    PUT /api/persons - vždy vytvoří nový záznam osoby a předchozí nastaví na hidden
    DELETE /api/persons - nastaví záznam osoby na hidden

### InvoiceDTO:

    Long _id;
    Integer invoiceNumber;
    Long sellerID;
    Long buyerID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    LocalDate issued;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    LocalDate dueDate;
    String product;
    BigDecimal price;
    Integer VAT;
    String note;


### Endpointy pro Invoice entitu:

    POST /api/invoices
    GET /api/invoices
    GET /api/invoices/{id}
    GET /api/invoices/stats
    PUT /api/invoices
    DELETE /api/invoices
    

