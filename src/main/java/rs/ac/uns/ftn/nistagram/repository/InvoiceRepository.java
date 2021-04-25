package rs.ac.uns.ftn.nistagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.nistagram.domain.invoice.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
}
