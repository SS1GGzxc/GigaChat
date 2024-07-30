package SS1GGzxc.Constants;

import okhttp3.MediaType;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

public abstract class Constants {
    public final static String GetAccessTokenURL = "https://ngw.devices.sberbank.ru:9443/api/v2/oauth";
    public final static String CreatePromptRequest = "https://gigachat.devices.sberbank.ru/api/v1/chat/completions";
    public final static MediaType urlencodedType = MediaType.parse("application/x-www-form-urlencoded");
    public final static MediaType AppJsonType = MediaType.parse("application/json");
    public static String Access_Key;
    public final static String PromptJSONPattern = "{\n" +
            "  \"model\": \"GigaChat:latest\",\n" +
            "  \"n\": 1,\n" +
            "  \"max_tokens\": 128,\n" +
            "  \"messages\": [\n" +
            "        {\n" +
            "            \"role\": \"user\",\n" +
            "            \"content\": \"Придумай названия для хоррор игры\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    public static final TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }
            }
    };
}