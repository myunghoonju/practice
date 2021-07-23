package practice.api;

import java.nio.charset.StandardCharsets;

public class APICheck {
    public static void main(String[] args) {
        APICheck apiCheck = new APICheck();
        apiCheck.useDeprecated();
    }

        public void useDeprecated() {
        String str = "myunghoonju";
        byte[] strBytes = str.getBytes(StandardCharsets.UTF_8);
        String convertedStr = new String(strBytes,0);
    }
}
