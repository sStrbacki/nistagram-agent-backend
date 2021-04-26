package rs.ac.uns.ftn.nistagram.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.nistagram.auth.exceptions.AuthorizationException;
import rs.ac.uns.ftn.nistagram.config.JwtConfig;

@Component
public class JwtService {

    private static final Gson gson = new Gson();
    private final Algorithm algorithm;

    public JwtService(JwtConfig config) {
        this.algorithm = Algorithm.HMAC256(config.getSecret());
    }

    // JWT stores data as claims under K/V mappings. We serialize the identity object using GSON and put it under
    // the 'identity' key
    private static final String IDENTITY_CLAIM_KEY = "identity";

    public <T> T decrypt(String jwt, Class<T> classType) throws AuthorizationException {
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            verifier.verify(jwt);
        }
        catch (JWTVerificationException e) {
            throw new AuthorizationException();
        }

        DecodedJWT decodedJWT = JWT.decode(jwt);
        return deserialize(decodedJWT.getClaim(IDENTITY_CLAIM_KEY).asString(), classType);
    }

    public String encrypt(Object object) {
        return JWT.create()
                .withClaim(IDENTITY_CLAIM_KEY, serialize(object))
                .sign(algorithm);
    }

    private String serialize(Object object) {
        return gson.toJson(object);
    }

    private <T> T deserialize(String serializedObject, Class<T> classType) {
        return gson.fromJson(serializedObject, classType);
    }
}
