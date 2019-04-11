/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsa;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Mustafa
 */

public class Owner {
    int n, fn, p, q, e, d;
    int randNumbTmp;
    RSA_PublicKey publicKey = new RSA_PublicKey();
    RSA_PrivateKey privateKey = new RSA_PrivateKey();

    Owner(){
        generate_N_and_fN();
        generate_E();
        generate_D();
        setKeysVlaues();
    }
    String readEncryptedMsg(ArrayList<Integer> listOfIntegers2) {
        //long a, b, res;
        BigDecimal res = null;
        String s="";
        for (int i = 0; i < listOfIntegers2.size(); i++) {
            res = null;
            BigDecimal b = new BigDecimal(listOfIntegers2.get(i));
            b = b.pow(d);
            //System.out.println("thia ia b2 : " + b.longValue());;
            BigDecimal bn = new BigDecimal(n);
            //BigDecimal res = new BigDecimal(b.longValue()%n);

            res = b.remainder(bn);
            s+=Character.toString ((char) res.intValueExact());
        }
        //System.out.println("msg is : "+s);
        return s;
    }
    
    RSA_PublicKey getPublicKey(){
        return publicKey;
    }
    void setKeysVlaues() {
        publicKey.e = e;
        publicKey.n = n;

        privateKey.d = d;
        privateKey.n = n;

    }

    void generate_D() {
        randNumbTmp = 2;
        while (true) {

            if (fn != 0 && randNumbTmp != e) {
                if ((randNumbTmp * e) % fn == 1) {
                    d = randNumbTmp;
                    break;
                }
            }
            randNumbTmp++;
        }
    }

    void generate_E() {
        while (true) {
            randNumbTmp = getRandIntegerInRange(4, 500);
            if (isPrime(randNumbTmp)) {
                e = randNumbTmp;
                break;
            }
        }
    }

    void generate_N_and_fN() {
        while (true) {
            randNumbTmp = getRandIntegerInRange(20, 100);
            if (isPrime(randNumbTmp)) {
                p = randNumbTmp;
                break;
            }
        }
        while (true) {
            randNumbTmp = getRandIntegerInRange(20, 1000);
            if (isPrime(randNumbTmp) == true && randNumbTmp != p) {
                q = randNumbTmp;
                break;
            }
        }
        n = p * q;
        fn = (p - 1) * (q - 1);
    }

    public static int getRandIntegerInRange(int min, int max) {
        int x = (int) (Math.random() * ((max - min) + 1)) + min;
        return x;
    }

    public static int getRandInteger() {
        int x = (int) (Math.random() * ((30 - 1) + 1)) + 1;
        return x;
    }

    public static boolean isPrime(int num) {
        if (num > 2 && num % 2 == 0) {
            //System.out.println(num + " is not prime");
            return false;
        }
        int top = (int) Math.sqrt(num) + 1;
        for (int i = 3; i < top; i += 2) {
            if (num % i == 0) {
                //System.out.println(num + " is not prime");
                return false;
            }
        }
        //System.out.println(num + " is prime");
        return true;
    }
}
