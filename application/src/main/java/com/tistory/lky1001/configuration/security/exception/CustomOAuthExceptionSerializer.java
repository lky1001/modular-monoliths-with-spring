package com.tistory.lky1001.configuration.security.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import java.io.IOException;

public class CustomOAuthExceptionSerializer extends StdSerializer<CustomOAuthException> {

    public CustomOAuthExceptionSerializer() {
        super(CustomOAuthException.class);
    }

    @Override
    public void serialize(CustomOAuthException value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        if (OAuth2Exception.INVALID_GRANT.equals(value.getMessage())) {
            // login failure
            gen.writeNumberField("code", 401);
            //gen.writeStringField("message", Message.OAUTH_ERROR_BAD_CREDENTIALS);
        } else if (OAuth2Exception.ACCESS_DENIED.equals(value.getMessage())) {
            // permission denied
            gen.writeNumberField("code", 403);
            //gen.writeStringField("message", Message.OAUTH_ERROR_PERMISSION_DENIED);
        } else if (OAuth2Exception.UNAUTHORIZED_CLIENT.equals(value.getMessage())) {
            // unauthorized
            gen.writeNumberField("code", 401);
            //gen.writeStringField("message", Message.OAUTH_ERROR_UNAUTHORIZED);
        } else {
            gen.writeNumberField("code", value.getCode());
            //gen.writeStringField("message", value.getMessage());
        }

        gen.writeEndObject();
    }
}
