/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsa;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;

/**
 *
 * @author Mustafa
 */
public class RSA_ALgo {

    String p_text;
    KeyPairGenerator kpg;
    KeyPair myPair;
    Cipher enc, dec;
    SealedObject myEncryptedMessage = null;

    RSA_ALgo() {
        try {
            // Get an instance of the RSA key generator
            kpg = KeyPairGenerator.getInstance("RSA");
            // Generate the keys — might take sometime on slow computers
            myPair = kpg.generateKeyPair();

            // Get an instance of the Cipher for RSA encryption/decryption
            enc = Cipher.getInstance("RSA");
            // Initiate the Cipher, telling it that it is going to Encrypt, giving it the public key
            enc.init(Cipher.ENCRYPT_MODE, myPair.getPublic());

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RSA_ALgo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(RSA_ALgo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(RSA_ALgo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    SealedObject Encrypt(String s) {
        try {
            // Encrypt that message using a new SealedObject and the Cipher we created before
            myEncryptedMessage = new SealedObject(s, enc);
        } catch (IOException ex) {
            Logger.getLogger(RSA_ALgo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(RSA_ALgo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myEncryptedMessage;
    }
    String Decrypt(SealedObject so){
        try {
            // Get an instance of the Cipher for RSA encryption/decryption
            dec = Cipher.getInstance("RSA");
            // Initiate the Cipher, telling it that it is going to Decrypt, giving it the private key
            dec.init(Cipher.DECRYPT_MODE, myPair.getPrivate());
            p_text = (String) myEncryptedMessage.getObject(dec);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RSA_ALgo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(RSA_ALgo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(RSA_ALgo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RSA_ALgo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RSA_ALgo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(RSA_ALgo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(RSA_ALgo.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return p_text;
    }
}
