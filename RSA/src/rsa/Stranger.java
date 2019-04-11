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
public class Stranger {
    int e,n;
    ArrayList<Integer> listOfIntegers;
    Stranger(RSA_PublicKey key){
      listOfIntegers = new ArrayList<Integer>();
      e=key.e;
      n=key.n;
    }
    
    ArrayList<Integer> getEcryptedObject(String s) {
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
}
