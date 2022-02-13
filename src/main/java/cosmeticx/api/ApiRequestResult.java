/*
 * Copyright (c) Jan Sohn
 * All rights reserved.
 * This plugin is under GPL license
 */

package cosmeticx.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

public class ApiRequestResult {

    private final int responseCode;
    private final String responseMethod;
    private final String responseMessage;

    public ApiRequestResult(int responseCode, String responseMethod, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMethod = responseMethod;
        this.responseMessage = responseMessage;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getResponseMethod() {
        return responseMethod;
    }

    public HashMap<String, String> messageAsMap() {
        return new Gson().fromJson(
                this.getResponseMessage(), new TypeToken<HashMap<String, Object>>() {}.getType()
        );
    }
}
