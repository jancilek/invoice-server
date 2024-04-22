package cz.itnetwork.entity.repository;

import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {
    @Query("SELECT NEW cz.itnetwork.dto.InvoiceStatisticsDTO(" +
                    "SUM(actual.price), " +
                    "SUM(everything.price), " +
                    "COUNT(*)) " +
                    "FROM invoice everything " +
                    "LEFT JOIN invoice actual " +
                    "ON everything.id = actual.id " +
                    "AND YEAR(actual.issued) = YEAR(CURRENT_DATE)")
    InvoiceStatisticsDTO getInvoiceStatistics();
}