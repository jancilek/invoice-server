package cz.itnetwork.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {
    @JsonProperty("_id")
    private Long id;
    private Integer invoiceNumber;
    private LocalDate issued;   //Date
    private LocalDate dueDate;  //Date
    private String product;
    private Long price;        //Price
    private Integer vat;
    private String note;
    private PersonDTO buyer;
    private PersonDTO seller;

}
