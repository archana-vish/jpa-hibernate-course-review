package com.av8242n.jpa.hibernate.demo.Decrypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BouncyCastleTest {


    private static final String datasetName = "RMSREAL";
    private static final String linkID = "ft424639836";
    private static final String keyPhrase = "!*UoG_Accommodation_Services*!";
    private static final String baseURL =  "https://ag.gre.ac.uk:4430";
    private static final String queryString = "/Run?ft=";
    private static final String salt = "F1EB9845886E4512AEC270D41FA1B1C4";
    private static final String algorithm = "PBKDF2WithHmacSHA1";

    private static String portalEncrypt(String paramString, String keyPhrase, long date) {

        try {

            Security.addProvider(new BouncyCastleProvider());

            byte[] bytesSalt = salt.getBytes("US-ASCII");
            KeySpec pbeKeySpec = new PBEKeySpec(keyPhrase.toCharArray(), bytesSalt, 1, 256);


            SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);

            SecretKey pwdb= factory.generateSecret(pbeKeySpec);

            byte[] keyBytes = new byte[16];
            byte[] vectorBytes = new byte[16];

            System.arraycopy(pwdb.getEncoded(), 0, keyBytes, 0, 16);
            System.arraycopy(pwdb.getEncoded(), 16, vectorBytes, 0, 16);

            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            //String now = "20190720154755"; //dateFormat.format(new Date()); // Check tomcat_liferay.sh for the correct timezone
            String now = Long.toString(date);

            String cipherText = now + paramString;

            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
            AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(vectorBytes);
            Cipher cipher = Cipher.getInstance("AES/CBC/ZeroBytePadding", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] result = cipher.doFinal(cipherText.getBytes("US-ASCII"));
            String finalToken =  DatatypeConverter.printBase64Binary(result);
            return finalToken;

        } catch(NoSuchAlgorithmException | InvalidKeySpecException | UnsupportedEncodingException
                | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException
                |  BadPaddingException | InvalidAlgorithmParameterException | NoSuchProviderException exception) {
            //LOG.error("portalEncrypt failed for :: " + paramString , exception);
            return "";
        }
    }

    public static void main(String[] args) {
        try {
            String paramString = String.format("DatasetName=%s&LinkID=%s&UserID=%s", datasetName, linkID, "000976054");
            //for (long l = 20190720154959l; l <= 20190720155059l; l++) {
            for (long l = 20190720154700l; l <= 20190720154759l; l++) {
                String encryptedParams = BouncyCastleTest.portalEncrypt(paramString, keyPhrase, l);
                String mercuryLink = URLEncoder.encode(encryptedParams, "UTF-8");
                System.out.println(l + ":: ft=" + mercuryLink);
            }

        } catch (UnsupportedEncodingException exception ) {
            //LOG.error("getLinkToRMSMercury failed ",  exception);
            // return "";
            exception.printStackTrace();
        }
    }
}

