This is an example of client's authentication  on https

To use this, we need a client keystore (containing client's private key), and server's truststore containing server's public key

Below link shows how to create a public-private key pair for client authentication.
Public key of the client will be with server, and private key will be used by the client.

http://www.akadia.com/services/ssh_test_certificate.html

Once the client keys are generated, use the following commands to generate a PKCS12 file:

openssl pkcs12 -inkey key -in cert -export -out keys.pkcs12

here, inkey is the .key file, and cert is the .crt file

Now, generate a keystore using the above file
keytool -importkeystore -srckeystore keys.pkcs12 -srcstoretype pkcs12 -destkeystore keystore.jks


Now, import the server's public certificate in a truststore

keytool -import -alias "Server Certificate" -file server.cer -keystore server.truststore

Now, see UseCase.java

References:
http://www.akadia.com/services/ssh_test_certificate.html
http://stackoverflow.com/questions/6252045/creating-a-keystore-from-private-key-and-a-public-key
