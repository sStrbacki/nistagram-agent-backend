package rs.ac.uns.ftn.nistagram.auth.model;

import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
public class PasswordResetForm {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Type(type = "uuid-char")
    private UUID userUUID;
    @Type(type = "uuid-char")
    private UUID resetUUID;
    private long validUntil;
    private boolean used;

    private static final int RESET_VALID_MINUTES = 10;

    public PasswordResetForm() {}

    public PasswordResetForm(UUID userUUID) {
        this.userUUID = userUUID;
        this.resetUUID = UUID.randomUUID();
        this.validUntil = generateValidUntil();
    }

    private long generateValidUntil() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, RESET_VALID_MINUTES);
        return calendar.getTime().getTime();
    }

    public boolean isNonExpired() {
        return new Date(validUntil).after(Calendar.getInstance().getTime());
    }

    public boolean hasBeenUsed() {
        return used;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(UUID userUUID) {
        this.userUUID = userUUID;
    }

    public UUID getResetUUID() {
        return resetUUID;
    }

    public void setResetUUID(UUID resetUUID) {
        this.resetUUID = resetUUID;
    }

    public long getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(long validUntil) {
        this.validUntil = validUntil;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
