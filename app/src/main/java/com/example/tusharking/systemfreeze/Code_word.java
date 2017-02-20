package com.example.tusharking.systemfreeze;


// java.io.*;
//import java.util.*;
//import java.awt.image.*;
//import java.security.SecureRandom;

import java.security.SecureRandom;

//import javax.imageio.*;
//import javax.imageio.stream.ImageOutputStream;
class Code_word
{
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*";
    static SecureRandom rnd = new SecureRandom();

    String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

}
