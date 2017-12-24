package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;


public class AlipayConfig {
	

	public static String app_id = "2016082000296879";
	

    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCEbEN/86LYalzNxhqFR8Rqe5081afpk4h6CyI0qN+qNwSwYUmtelBtXfCVyAo4ofS2chMe9eCGp0u24Qgp5Ky9CXwR8oFvgNqLzsMg6m0KM0llh2j1UeeurkmJsWBwK6JTDH+ynV8qAX9etIQzkklD/cBPcgfjuYNCzBvphKK9AmamudNOmL6dR84qO7bq81meeAbtpTv6X+zj+yoTqwf+5i8KoPjE0a/3zTDkF+DAg54aZ6oeRMAsU1iSn6jK4OBcX7Ji3DGHY8Erw70PbV9Zi/ld14ecdsSWW8q71+hFlkuqv2DfB//iFDKO1IOGj2eJkCsbO8Jasp3g06xku6vPAgMBAAECggEAUnAPqXUkGLRGZ/f/BKAEAAJKhdfAu6GT4d5SghPrgczUh3VZZ7zwmtVTc/tfmZgBfx8PFkQdbdZyRTxcV7A7dUeVTJr6x06hP04Nnc0y6pm5BJLC3Y4KvG1V7HlXPx165VavnKNp3f2mrG+WqYBVShWLhinIDZWsyIXGjp56rU0PF2qCk9bISDaIUOg2Qn8z87FGG7e45XU8GemI2j15Wr80xOimDTRqOEonm2jGhx2MdUUSr2gUXd6XBcifHxrDffPWxMlytBqldtzaqADxuc+a1fr3Mk+x8rlG5Dfhg7sjrkmX/JVA0Koqyc749SKxUu+/t3s15hjgl0oeGZbESQKBgQC5wEQ/H0PtHzs1q3iUaBojZQI4CjbmBlw30lkYDiUp8QPgb7mS6kUWFObf2/2SC1vf6AEdYZC83QLLLyFJX9PFggCy6g0jGq5u8axP2RLpOqZ/W1b2hgymDyK9nHX/H/MmP4WFnfQLR81SWHXCa4qbSUqYoYsfUqkQeiedeJO77QKBgQC2gPZ8Tr8NzFbfMGUIWpf3DUKSDKAPASJMjqIIwvcr6kcue/+hgGeL67gxLydj+SDRgu0hB44b7pJ5fuDl6TkMhGZcID2WdlAGlsQrzl2pxncp+QdaCCv191ZOYEBZZzdJ0/rTZBUyA2cWTNW+Au64gDD5gP6AxqzwtYit5jInKwKBgQCApsDd8zqprhrlRjkCmMOZxijuRWzWjosqgn8AoTNuRCbKOLeQIL/u1lU9sPGESGe+wcKb5epIVM7NiXwFhOB5CFyRjnfA24b7Alm1CAUIEn4k8U9e+WMjVJJcP1SeejN/RzmkXM0dTwLPSMNu/+sFOJL4+dIQxMChz+lGMLAp1QKBgB9SovtSyJelqoND2xND+TD9pZ9La20n1KsvD8BRNfrjirP/tDATybHKEyLd5PTTnT5YKjqxAULp/1m63P6YuKHdV3QYVHmudK2s0p5yHME2vt0FUK2zGVhtyvJIssKlZGfXagU9+CVNUDQTm9uwxSgFWGJ6MTQI5UWq4np2N8VhAoGAVglmiDw+sGyd5S1bpzu7TPGLhjYEtgMAP6oqX2gdZHmw4pABQcyqcyP0zOkNEM1Qvai7kTokoreGgVfChB84By40MtDQCxKsKTPCUuL5eSSDtN6G5whbL8c/OSv+GviBzla6cAAysDcUVb5nZ+mOhkU5ZVID+SCYJap071bvMyU=";
	

    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyKmxxmgfd4mE+HPPkfcAuxOr/uw6i9JCmMq1P9QQXiTnNmF3HzEuU0+s738RYQHlL8h44ol2QBx+WDyLeqlNBi18FBRBayL7OCttUMD2a0FoKT5Gb9498djH8H0bH6sWZMOP+DcXDoRtlSKTh4OEW67IlKRLrBzTv530dQ38ohBWLXnbYp7LUPtNhWwMR/mmtZBk7qdApaXIlNmC2E+yXqMvYoz1LyNYGCrUPROmKW9oMI15wa3EzwFNdHEPTOhUvq1m/d5osbYX1ScEOS0Ub5qDVhlYRmPfXUNrIgK5+FzUl/9NpIbGCdOb7EebmH2PCZZrwX0RUS3xHdCIFF4BCQIDAQAB";


	public static String notify_url = "https://180.171.41.183/crowdfunding/backpay/notify";


    public static String return_url = "http://180.171.41.183/crowdfunding/alipay/return";
	//public static String return_url = "http://180.171.41.183/crowdfunding/login";

	public static String sign_type = "RSA2";
	

	public static String charset = "utf-8";
	

	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	

	public static String log_path = "D:\\logs";


    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

