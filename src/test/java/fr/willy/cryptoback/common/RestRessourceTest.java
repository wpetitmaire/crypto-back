package fr.willy.cryptoback.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.willy.cryptoback.CryptobackApplication;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.regex.Pattern;

@SpringBootTest(classes = CryptobackApplication.class)
@AutoConfigureMockMvc
public class RestRessourceTest {

    private final static Pattern UUID_PATTERN = Pattern.compile("^[{]?[\\da-fA-F]{8}-([\\da-fA-F]{4}-){3}[\\da-fA-F]{12}[}]?$");

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected ObjectWriter objectWriter;

    @BeforeEach
    public void setup() {
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
    }

    protected String asString(Object object) throws JsonProcessingException {
        return objectWriter.writeValueAsString(object);
    }

    protected <T> T extractResponse(MvcResult mvcResult, Class<T> returnType) throws UnsupportedEncodingException, JsonProcessingException {
        return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), returnType);
    }

    protected <T> T extractResponse(MvcResult mvcResult, TypeReference<T> returnType) throws UnsupportedEncodingException, JsonProcessingException {
        return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), returnType);
    }

    protected String extractHeader(MvcResult mvcResult, String headerName) {
        return mvcResult.getResponse().getHeader(headerName);
    }


}
