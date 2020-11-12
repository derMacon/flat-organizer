package com.dermacon.securewebapp.security.conf;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyPasswordEncoder extends BCryptPasswordEncoder {

    @Override
    public boolean matches(CharSequence var1, String var2) {
        boolean out = super.matches(var1, var2);
        System.out.println("var1: " + var1);
        System.out.println("var2: " + var2);
        System.out.println("out: " + out + "\n");
        return out;
    }


}
