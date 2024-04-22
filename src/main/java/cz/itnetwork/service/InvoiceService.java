package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;

import java.util.List;

public interface InvoiceService {
    /**
     * Creates new invoice
     *
     * @param invoiceDTO invoice to create
     * @return newly created invoice
     */
    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

    /**
     *  Fetches all invoices
     *
     * @param invoiceFilter
     * @return List of all invoices
     */

    List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter);

    /**
     * Fetches invoices by seller
     * @param identificationNumber identification number of the seller
     * @return List of invoices by seller
     */

    List<InvoiceDTO> getInvoicesBySales(String identificationNumber);

    /**
     * Fetches invoices by buyer
     * @param identificationNumber identification number of the buyer
     * @return List of invoices by buyer
     */
    List<InvoiceDTO> getInvoicesByPurchases(String identificationNumber);

    /**
     * Fetches invoices by id
     * @param invoiceId invoice id
     * @return an invoice with specific id
     */

    InvoiceDTO getInvoiceById(long invoiceId);

    /**
     * Updates invoice
     *
     * @param invoiceDTO new invoice data to update
     * @param invoiceId id of updated invoice
     * @return updated invoice
     */

    InvoiceDTO updateInvoice(InvoiceDTO invoiceDTO, long invoiceId);

    /**
     * Deletes invoice
     *
     * @param invoiceId id of invoce to delete
     */
    void removeInvoice(long invoiceId);

    /**
     * Fetches statistic
     * @return List containing invoices count, price sum for current year and price sum
     */

    InvoiceStatisticsDTO getInvoceStatistics();
}
