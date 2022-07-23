package fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.entity.basic.PaginatedData;
import fr.willy.cryptoback.accounts.infrastructure.repository.api.exception.ConnexionError;
import fr.willy.cryptoback.helper.gsonadapter.GsonLocalDateTime;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Hex;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

@Log4j2
@Service
public class CoinbaseConnexion {
    private String secretKey = System.getProperty("cbSecretKey");
    private String apiKey = System.getProperty("cbApiKey");
    private String baseUrl = "https://api.coinbase.com/v2/";
    private long initTimeStamp;
    private final static HttpClient client = HttpClient.newHttpClient();

    protected HttpResponse<String> getRequest(String resourceUrl) {
        return getRequest(resourceUrl, null);
    }

    /**
     * Send a GET request
     * @param resourceUrl API resource path
     * @return get request response
     */
    private HttpResponse<String> getRequest(String resourceUrl, HashMap<String,String> queryParameters)  {

        final long timestamp = initTimeStamp == 0L ? Instant.now().getEpochSecond() : initTimeStamp;
        final String signature = getSignature(timestamp, HttpMethod.GET, resourceUrl, "");

        if (queryParameters != null && !queryParameters.isEmpty()) {
            Iterator<Map.Entry<String, String>> it = queryParameters.entrySet().iterator();
            StringBuilder resourceUrlBuilder = new StringBuilder(resourceUrl);
            while (it.hasNext()){
                Map.Entry<String, String> elem = it.next();
                resourceUrlBuilder.append("?").append(elem.getKey()).append("=").append(elem.getValue());
            }
            resourceUrl = resourceUrlBuilder.toString();
        }

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(this.baseUrl + resourceUrl))
            .header("CB-ACCESS-KEY", apiKey)
            .header("CB-ACCESS-SIGN", signature)
            .header("CB-ACCESS-TIMESTAMP", String.valueOf(timestamp))
            .header("CB-VERSION", "2021-06-03")
            .header("Accept-Language", "fr")
            .build();

        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            throw new ConnexionError("Error sending request to CB", e);
        }
    }

    /**
     * Create the encoded signature. Used for each request
     * @param timestamp number of seconds since Unix Epoch.
     * @param httpMethod HttpMethod
     * @param resourcePath api ressource path
     * @param payload request body
     * @return encoded signature
     */
    private String getSignature(long timestamp, HttpMethod httpMethod, String resourcePath, String payload) {

        String prehash = timestamp + httpMethod.toString() + resourcePath;

        if (httpMethod.equals(HttpMethod.POST) || httpMethod.equals(HttpMethod.PUT)) {
            prehash += payload;
        }

        Mac hmacSHA256 = null;
        try {
            hmacSHA256 = Mac.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            throw new ConnexionError("Algorithm HmacSHA256 not found in Mac.", e);
        }

        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        try {
            hmacSHA256.init(secretKeySpec);
        } catch (InvalidKeyException e) {
            throw new ConnexionError("invalid secretKey.", e);
        }
        byte[] hash = hmacSHA256.doFinal(prehash.getBytes());

        log.debug("Signature : " + Hex.encodeHexString(hash));

        return Hex.encodeHexString(hash);
    }

    protected <T> List<T> getPaginatedData(String ressourceUrl, Class<T> clazz) {
        log.info("ressourceUrl : {}", ressourceUrl);

        boolean isANextPage;
        HttpResponse<String> getRequestResponse;
        String response;
        PaginatedData<T> paginatedData;
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTime())
            .create();
        Type typeToken = TypeToken.getParameterized(PaginatedData.class, clazz).getType();
        List<T> data = new ArrayList<>(30);

        do {
            getRequestResponse = getRequest(ressourceUrl);

            if (getRequestResponse.statusCode() != HttpStatus.OK.value()) {
                return Collections.emptyList();
            }

            response = getRequestResponse.body();
            paginatedData = gson.fromJson(response, typeToken);

            data.addAll(paginatedData.data());

            isANextPage = paginatedData.pagination().getNext_uri() != null;
            ressourceUrl = paginatedData.pagination().getNext_uri();
        } while (isANextPage);

        return data;
    }

}
