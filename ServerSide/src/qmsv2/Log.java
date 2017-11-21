package qmsv2;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Log {

    public Log(){
        try {
            r = new RandomAccessFile(file, "rw");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    RandomAccessFile r;
    File file = new File("D:\\Prgramming\\ServerSide\\src\\qmsv2\\LogFile.txt");
    public void  WriteLog(ArrayList array){
        try{




            for(int i=0;i<array.size();i++){

                ArrayList a2 = (ArrayList) array.get(i);

                for(int j=0;j<6;j++){

                    r.writeBytes((String)a2.get(j)+"\r\n");

                }

            }
        r.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}


