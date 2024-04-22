package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.dto.mapper.PersonMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import cz.itnetwork.entity.repository.specification.InvoiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity entity = invoiceMapper.toEntity((invoiceDTO));
        entity.setBuyer(personRepository.getReferenceById(invoiceDTO.getBuyer().getId()));
        entity.setSeller(personRepository.getReferenceById(invoiceDTO.getSeller().getId()));
        entity = invoiceRepository.save(entity);

        return invoiceMapper.toDTO(entity);
    }

    @Override
    public List<InvoiceDTO> getInvoicesBySales(String identificationNumber) {
        return personRepository.findByIdentificationNumber(identificationNumber)
                .stream()
                .map(PersonEntity::getSales)
                .flatMap(List::stream)
                .map(item -> invoiceMapper.toDTO(item))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDTO> getInvoicesByPurchases(String identificationNumber) {
        return personRepository.findByIdentificationNumber(identificationNumber)
                .stream()
                .map(PersonEntity::getPurchases)
                .flatMap(List::stream)
                .map(item -> invoiceMapper.toDTO(item))
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO getInvoiceById(long invoiceId) {
        InvoiceEntity invoice = null;
        try {
            invoice = fetchInvoiceById(invoiceId);
            return invoiceMapper.toDTO(invoice);
        } catch (NotFoundException ignored) {
        }
        return invoiceMapper.toDTO(invoice);
    }

    @Override
    public InvoiceDTO updateInvoice(InvoiceDTO invoiceDTO, long invoiceId) {
        invoiceDTO.setId(invoiceId);
        InvoiceEntity entity = fetchInvoiceById(invoiceId);
        invoiceMapper.updateInvoiceEntity(invoiceDTO, entity);
        entity = invoiceRepository.save(entity);

        return invoiceMapper.toDTO(entity);
    }

    @Override
    public void removeInvoice(long invoiceId) {
        try {
            invoiceRepository.deleteById(invoiceId);
        } catch (NotFoundException ignored) {
        }
    }

    @Override
    public InvoiceStatisticsDTO getInvoceStatistics() {
        return invoiceRepository.getInvoiceStatistics();
    }


    @Override
    public List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter){
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(invoiceFilter);

        return invoiceRepository.findAll(invoiceSpecification, PageRequest.of(0, invoiceFilter.getLimit()))
                .stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }


    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }
}
