package qmsv2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Threads extends Thread {

    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream ous;
    private Database database;
    private Message message;

    public Threads(Socket socket) {
System.out.println("igkghjghmgvhjyvyfyjfyjfvjug");
        this.socket = socket;
        init();
        System.out.println("in thread constructor");
        database = new Database();
        //     database.getAuthorization("Isra","Isra");
        this.start();
        // System.exit(1);
        //send_Available_Departmnets();


    }

    public void init() {
        try {
            message = new Message();
            ous = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void run() {

        try {
            Message message = new Message();
            message.array = (ArrayList) this.ois.readObject();
            String s1 = (String) message.array.get(0);
            String s2 = (String) message.array.get(1);
            this.send_Authorization(s1, s2);

            while (true) {

                Message message2 = (Message) this.ois.readObject();
                switch (message2.service) {

                    case 1:
                        System.out.println("service 1");
                        send_Available_Departmnets();
                        break;

                    case 11:
                        System.out.println("service 2");
                        System.out.println((String) message2.array.get(0));

                        break;
                    case 2:
                        System.out.println("service remove");
                        message = new Message();
                        message.array = database.sendAllAccounts();
                        this.ous.writeObject(message);
                        System.out.println("object is sent");
                        break;

                    case 3:
                        System.out.println("service Answers");
                        ArrayList answers = message2.array;
                        System.out.println("***************");
                        for(int i=0;i<answers.size();i++){
                            System.out.println("");
                            System.out.print(((ArrayList)(answers.get(i))).get(0));
                            System.out.print(","+((ArrayList)(answers.get(i))).get(1));
                            System.out.print(","+((ArrayList)(answers.get(i))).get(2));
                            System.out.print(","+((ArrayList)(answers.get(i))).get(3));
                            System.out.print(","+((ArrayList)(answers.get(i))).get(4));
                            if( (int) ((ArrayList)(answers.get(i))).get(4) == 1){
                                System.out.println((boolean)((ArrayList)(answers.get(i))).get(5));
                            }
                            if( (int) ((ArrayList)(answers.get(i))).get(4) == 2){
                                System.out.println((String) ((ArrayList)(answers.get(i))).get(5));
                            }
                            if( (int) ((ArrayList)(answers.get(i))).get(4) == 3){
                                System.out.println((int)((ArrayList)(answers.get(i))).get(5));
                            }
                            System.out.println("");
                        }
                        System.out.println("****************");
                        database.saveAnswers(message2.array);
                        break;
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean send_Available_Departmnets() {

        Message message = new Message();
        message.array = database.getDeparments();
        for (int i = 0; i < message.array.size(); i++) {
            System.out.println((String) message.array.get(i));
        }
        try {
            this.ous.writeObject(message);

        } catch (IOException e) {

            e.printStackTrace();
            return false;

        }

        return true;
    }

    public void send_Authorization(String userName, String password) throws IOException {
        Message m = new Message();
        m.array = database.getAuthorization(userName, password);
        System.out.println((int) m.array.get(0));
        this.ous.writeObject(m);
        System.out.println("the sent is done !! ");

    }

}














