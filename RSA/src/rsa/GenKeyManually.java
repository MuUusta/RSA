/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsa;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Mustafa
 */
class Alpha2 {

    static String s = "abcdefghijklmnopqrstuvwxyz";
    static int num;

    static int getNUm(String c) {
        return s.indexOf(c) + 1;
    }
}

class RSA_PublicKey {

    int e, n;

}

class RSA_PrivateKey {

    int d, n;
}

public class GenKeyManually {

    int n, fn, p, q, e, d;
    int randNumbTmp;
    RSA_PublicKey publicKey = new RSA_PublicKey();
    RSA_PrivateKey privateKey = new RSA_PrivateKey();
    ArrayList<Integer> listOfIntegers;

    GenKeyManually() {
        listOfIntegers = new ArrayList<Integer>();
        generate_N_and_fN();
        generate_E();
        generate_D();
        setKeysVlaues();
    }

    ArrayList<Integer> enc(String s) {
        int a = 0;
        BigDecimal g;
        BigDecimal res = null;
        for (int i = 0; i < s.length(); i++) {
            res = null;
            a = (int) s.charAt(i);
            BigDecimal b = new BigDecimal(a);
            b = b.pow(e);
            //System.out.println("thia ia b : " + b);
            //res = new BigDecimal(b.longValue() % n);
            BigDecimal bn = new BigDecimal(n);
            res = b.remainder(bn);
            listOfIntegers.add(res.intValueExact());
        }
        return listOfIntegers;
    }

    String dec(ArrayList<Integer> listOfIntegers2) {
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
        System.out.println("msg was : "+s);
        return s;
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
