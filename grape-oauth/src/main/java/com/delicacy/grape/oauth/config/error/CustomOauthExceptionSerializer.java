package com.delicacy.grape.oauth.config.error;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {
    private static final long serialVersionUID = 2652127645704345563L;

    public CustomOauthExceptionSerializer() {
        super(CustomOauthException.class);
    }

    @Override
    public void serialize(CustomOauthException value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        gen.writeObjectField("success",false);
        gen.writeObjectField("error",value.getOAuth2ErrorCode());
        gen.writeObjectField("status", value.getHttpErrorCode());
        gen.writeObjectField("message", value.getMessage());
        gen.writeObjectField("path", request.getServletPath());
        gen.writeObjectField("timestamp", (new Date()).getTime());
        if (value.getAdditionalInformation()!=null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                gen.writeObjectField(key, add);
            }
        }
        gen.writeEndObject();
    }
}
