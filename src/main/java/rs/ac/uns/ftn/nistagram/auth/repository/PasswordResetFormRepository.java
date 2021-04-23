package rs.ac.uns.ftn.nistagram.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.nistagram.auth.model.PasswordResetForm;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PasswordResetFormRepository extends JpaRepository<PasswordResetForm, Long> {

    @Query(value = "select p from PasswordResetForm p where p.userUUID = ?1 and p.resetUUID = ?2")
    Optional<PasswordResetForm> findByUUIDPair(@Param("uUUID") UUID userUUID, @Param("rUUID") UUID resetUUID);
}
