import java.io.IOException;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.protocol.Protocol;

public class UseCase {
    public static void main(String[] args) throws HttpException, IOException {

        //keystore.jks == client's private certificate
        //server.truststore == server's public key

        Protocol authhttps = new Protocol(
                "https",
                new AuthSSLProtocolSocketFactory(new URL("file:keystore.jks"),
                        "123456", new URL("file:server.truststore"), "123456"),
                443
        );

        Protocol.registerProtocol("https", authhttps);

        System.out.println("PROTOCOL REGISTERED");

        HttpClient client = new HttpClient();
        GetMethod httpget = new GetMethod(
                "https://www.google.com");
        client.setConnectionTimeout(60000);
        client.setTimeout(60000);
        int i = client.executeMethod(httpget);
        System.out.println(i);
    }
}
