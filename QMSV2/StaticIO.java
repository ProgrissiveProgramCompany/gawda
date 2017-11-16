/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmsv2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Isra
 */
public class StaticIO {
    static    ObjectOutputStream os ;
    static    ObjectInputStream is ;
public static void setIO(ObjectOutputStream os,ObjectInputStream is)throws Exception{
   
        StaticIO.os = os ;
        StaticIO.is = is ;
     //   System.out.println(isNull(this.os));

    }
}
