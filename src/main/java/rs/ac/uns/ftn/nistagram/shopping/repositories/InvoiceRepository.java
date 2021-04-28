package rs.ac.uns.ftn.nistagram.shopping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.nistagram.shopping.domain.invoice.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
}
