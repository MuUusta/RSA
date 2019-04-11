/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsa;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import javax.crypto.*;

/**
 *
 * @author Mustafa
 */
class Alpha {

    static String s = "abcdefghijklmnopqrstuvwxyz";
    static int num;

    static int getNUm(String c) {
        return s.indexOf(c) + 1;
    }
}

public class RSA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        //these are some java fuction that can be used for RSA Algo
        String msg = "This is a p text! can you decrypt it?", decryptedMsg;
        SealedObject msg_after_sealing;
        RSA_ALgo r1 = new RSA_ALgo();
        msg_after_sealing = r1.Encrypt(msg);
        decryptedMsg = r1.Decrypt(msg_after_sealing);
        System.out.println("Msg after Decryption is : " + decryptedMsg);

        //This is howw i implemented it,,, both encryption and decryption in the same Class 
        GenKeyManually gkm = new GenKeyManually();
        System.out.println("P: " + gkm.p + " - Q: " + gkm.q);
        System.out.println("N: " + gkm.n + " - fN: " + gkm.fn);
        System.out.println("E: " + gkm.e);
        GenKeyManually.isPrime(gkm.e);
        System.out.println("D: " + gkm.d);
        System.out.println("PubK.e: " + gkm.publicKey.e + "  - PubK.n: " + gkm.publicKey.n);
        System.out.println("PriK.d: " + gkm.privateKey.d + "  - PriK.n: " + gkm.privateKey.n);

        String m,output;
        Scanner input = new Scanner(System.in);
        System.out.println("\nPlease Enter a Text To Be Encrypted & Decrypted: ");
        m = input.nextLine();
        ArrayList<Integer> aloi = new ArrayList<Integer>();
        aloi = gkm.enc(m);
        output = gkm.dec(aloi);
        System.out.println("this is it after decryption > : " + output);
        
        //ecryption and decryption programs are separated
        ArrayList<Integer> arl2 = new ArrayList<Integer>();
        Owner ow1 = new Owner();
        Owner ow2 = new Owner();
        Stranger st1 = new Stranger(ow1.getPublicKey());
        System.out.println("\nPlease Enter a Text To Be Encrypted By The Owner No.1 PublicKey: ");
        m = input.nextLine();
        arl2 = st1.getEcryptedObject(m);
        output = ow1.readEncryptedMsg(arl2);
        System.out.println("The Owner No.1 Has read the msg as : "+output);
        output = ow2.readEncryptedMsg(arl2);
        System.out.println("The Owner No.2 Has read the msg as : "+output);

    }

}
