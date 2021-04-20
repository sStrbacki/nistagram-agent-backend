package rs.ac.uns.ftn.nistagram.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PasswordHandlingConfig {

    @Value("${bcrypt.log.rounds}")
    private int BCRYPT_LOG_ROUNDS;

    public int getLogRounds() {
        return BCRYPT_LOG_ROUNDS;
    }
}
