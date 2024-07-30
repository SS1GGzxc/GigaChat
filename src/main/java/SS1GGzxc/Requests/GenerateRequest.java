package SS1GGzxc.Requests;

import SS1GGzxc.Constants.Constants;
import SS1GGzxc.Settings.Settings;
import okhttp3.*;
import org.json.JSONObject;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static SS1GGzxc.Constants.Constants.*;

public class GenerateRequest {
    private OkHttpClient client;
    public static String Access_Token;
    public int ServerStatus;

    public GenerateRequest() {
        CreateCertificate();

    }

    private void CreateCertificate() {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());

            OkHttpClient.Builder newBuildr = new OkHttpClient.Builder();
            newBuildr.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0]);
            newBuildr.hostnameVerifier((hostname, session) -> true);

            client = newBuildr.build();
        } catch (NoSuchAlgorithmException | KeyManagementException e) { e.printStackTrace();}
    }

    public int UpdateKey() {
        String contentLenght;
        RequestBody body = RequestBody.create(urlencodedType, "scope=GIGACHAT_API_PERS");
        try {
            contentLenght = String.valueOf(body.contentLength());
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }

        Headers UpdateHeaders = new Headers.Builder()
                .add("Authorization", "Bearer " + Access_Key.trim())
                .add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .add("Content-Length", contentLenght)
                .add("Host", "ngw.devices.sberbank.ru:9443")
                .add("RqUID", "fdb7b2db-b87b-43ee-838c-152257b318c8")
                .build();

        Request UpdateKeyReq = new Request.Builder()
                .url(GetAccessTokenURL)
                .method("POST", body)
                .headers(UpdateHeaders)
                .build();

        try (Response response = client.newCall(UpdateKeyReq).execute()) {
            assert response.body() != null;
            JSONObject jsonObject = new JSONObject(response.body().string());
            Access_Token = jsonObject.getString("access_token");
            ServerStatus = response.code();
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public String CreateRequest(String prompt) {
        JSONObject jsonObject = new JSONObject(Constants.PromptJSONPattern);
        jsonObject.put("max_tokens", Settings.getInstance().getMaxTokens());
        jsonObject.getJSONArray("messages").getJSONObject(0).put("content", prompt);

        RequestBody body = RequestBody.create(AppJsonType, jsonObject.toString());

        try {
            Headers headers = new Headers.Builder()
                    .add("Authorization", "Bearer " + Access_Token)
                    .add("Content-Length", String.valueOf(body.contentLength()))
                    .add("Host", "gigachat.devices.sberbank.ru")
                    .add("Content-Type", "application/json")
                    .build();

            Request UpdateKeyReq = new Request.Builder()
                    .url(CreatePromptRequest)
                    .method("POST", body)
                    .headers(headers)
                    .build();

            Response response = client.newCall(UpdateKeyReq).execute();
            jsonObject = new JSONObject(response.body().string());
            return jsonObject.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
