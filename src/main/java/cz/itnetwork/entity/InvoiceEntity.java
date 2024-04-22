package cz.itnetwork.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "invoice")
@Getter
@Setter
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Integer invoiceNumber;

    @Column(nullable = false)
    private LocalDate issued;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private String product;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Integer vat;

    private String note;

    @JoinColumn (nullable = false)
    @ManyToOne
    private PersonEntity buyer;

    @ManyToOne
    @JoinColumn (nullable = false)
    private PersonEntity seller;

    public void setBuyer(PersonEntity buyer) {
        this.buyer = buyer;
    }

    public void setSeller(PersonEntity seller) {
        this.seller = seller;
    }
}
