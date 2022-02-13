/*
 * Copyright (c) Jan Sohn
 * All rights reserved.
 * This plugin is under GPL license
 */

package cosmeticx.api.task;

import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import com.google.gson.Gson;
import cosmeticx.CosmeticX;
import cosmeticx.api.ApiRequest;
import cosmeticx.api.ApiRequestResult;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.function.Consumer;

public class SendAsyncRequestTask extends AsyncTask {

    private final ApiRequest apiRequest;
    private final Consumer<ApiRequestResult> responseConsumer;
    private final Consumer<ApiRequest> responseErrorConsumer;
    private final int timeout;

    private HashMap<String, String> data;

    public SendAsyncRequestTask(ApiRequest apiRequest, Consumer<ApiRequestResult> responseConsumer, Consumer<ApiRequest> responseErrorConsumer, int timeout) {
        this.timeout = timeout * 1000;
        this.apiRequest = apiRequest;
        this.responseConsumer = responseConsumer;
        this.responseErrorConsumer = responseErrorConsumer;
    }

    @Override
    public void onRun() {
        try {
            URL url = new URL(CosmeticX.URL_API);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestProperty("Content-Type", "application/json");
            this.apiRequest.getHeaders().forEach(http::setRequestProperty);

            http.setConnectTimeout(this.timeout);
            http.setReadTimeout(this.timeout);
            if(this.apiRequest.isPostMethod()) {
                http.setRequestMethod("POST");
                String encodedData = new Gson().toJson(this.apiRequest.getBody());
                byte[] byteOut = encodedData.getBytes(StandardCharsets.UTF_8);
                OutputStream outputStream = http.getOutputStream();
                outputStream.write(byteOut);
            }

            http.connect();
            HashMap<String, String> data = new HashMap<>();
            data.put("code", String.valueOf(http.getResponseCode()));
            data.put("method", http.getRequestMethod());
            data.put("message", http.getResponseMessage());
            http.disconnect();
            this.data = data;
            this.setResult(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCompletion(Server server) {
        Object result = this.getResult();
        if (result == null) {
            this.responseErrorConsumer.accept(this.apiRequest);
            CosmeticX.getInstance().getLogger().error("Connection to backend can't be open!");
            return;
        }
        ApiRequestResult requestResult = new ApiRequestResult(Integer.parseInt(this.data.get("code")), this.data.get("method"), this.data.get("message"));
        if (requestResult.getResponseCode() >= 400 && requestResult.getResponseCode() < 600) {
            CosmeticX.getInstance().getLogger().error("[API-ERROR | " + requestResult.getResponseCode() + "] " + "[" + this.apiRequest.getURL() + "]: " + this.apiRequest.getBody().toString());
            return;
        }
        this.responseConsumer.accept((ApiRequestResult) result);
    }
}