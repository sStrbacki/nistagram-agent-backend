package rs.ac.uns.ftn.nistagram.auth.middle;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import rs.ac.uns.ftn.nistagram.auth.identity.Identity;


@SuppressWarnings("all")
public class JwtService<T extends Identity> {

    private final Algorithm algorithm;
    private final String secret;
    private static Gson gson;
    private final Class<T> identityModelType;

    public JwtService(Class<T> identityModelType) {
        this.secret = "9zxc09ASd912ekaskd9zxc90ASAS09zxcASdklj12e90askzxkcajhgjh3r89as8909190e12kad;";
        this.algorithm = Algorithm.HMAC256(this.secret);
        this.gson = new Gson();
        this.identityModelType = identityModelType;
    }

    // JWT stores data as claims under K/V mappings. We serialize the identity object using GSON and put it under
    // the 'identity' key
    private static final String IDENTITY_CLAIM_KEY = "identity";

    public T decrypt(String jwt) throws AuthorizationException {
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            verifier.verify(jwt);
        }
        catch (JWTVerificationException e) {
            throw new AuthorizationException();
        }

        DecodedJWT decodedJWT = JWT.decode(jwt);
        return deserializeIdentityToken(decodedJWT.getClaim(IDENTITY_CLAIM_KEY).asString());
    }

    public String encrypt(T identity) {
        return JWT.create()
                .withClaim(IDENTITY_CLAIM_KEY, serializeIdentityToken(identity))
                .sign(algorithm);
    }

    private String serializeIdentityToken(T identity) {
        return gson.toJson(identity);
    }

    private T deserializeIdentityToken(String serializedToken) {
        return gson.fromJson(serializedToken, identityModelType);
    }
}
