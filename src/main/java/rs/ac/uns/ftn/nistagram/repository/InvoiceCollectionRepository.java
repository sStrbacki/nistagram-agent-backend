package rs.ac.uns.ftn.nistagram.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.ac.uns.ftn.nistagram.domain.invoice.InvoiceCollection;

public interface InvoiceCollectionRepository extends JpaRepository<InvoiceCollection,Long> {

    @Query(value = "select distinct ic from invoice_collections ic where ic.owner.username = :username ")
    InvoiceCollection findInvoicesByOwnersId(String username);
}
