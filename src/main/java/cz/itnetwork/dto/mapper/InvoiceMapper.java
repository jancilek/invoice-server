package cz.itnetwork.dto.mapper;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.yaml.snakeyaml.events.Event;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    InvoiceEntity toEntity(InvoiceDTO source);

    InvoiceDTO toDTO(InvoiceEntity source);

    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "buyer", ignore = true)
    void updateInvoiceEntity(InvoiceDTO source, @MappingTarget InvoiceEntity target);
}
