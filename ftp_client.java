package ftp_project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ftp_client {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        InetAddress ip = InetAddress.getLocalHost();
        Socket s = new Socket(ip, 9999);

        DataInputStream cin = new DataInputStream(s.getInputStream());
        DataOutputStream cout = new DataOutputStream(s.getOutputStream());

        Scanner c = new Scanner(System.in);

        String s1 = cin.readUTF();
        System.out.println("server : " + s1);
        s1 = c.nextLine();
        cout.writeUTF(s1);

        s1 = cin.readUTF();
        System.out.println("server : " + s1);
        s1 = c.nextLine();
        cout.writeUTF(s1);

        s1 = cin.readUTF();
        if (s1.equalsIgnoreCase("Login Successfully")) {
            System.out.println("server : " + s1);

            boolean bb = true;
            while (bb) {
                cout.writeUTF("New port and TCP Connection to Show my Directories");
                Socket ss = new Socket(ip, 5555);
                Scanner cc = new Scanner(System.in);
                DataInputStream ccin = new DataInputStream(ss.getInputStream());
                DataOutputStream ccout = new DataOutputStream(ss.getOutputStream());
                System.out.println("Enter show my directories to show you your directories ");
                s1 = "";
                s1 = cc.nextLine();
                ccout.writeUTF(s1);
                if (s1.equalsIgnoreCase("show my directories")) {
                    for (int w = 0; w < 3; w++) {
                        s1 = ccin.readUTF();
                        System.out.println("server : " + s1);
                    }
                    System.out.println("server: Select your Directorie");
                    System.out.println("Enter ( show movies or show music or show Docs )");
                    s1 = cc.nextLine();
                    ccout.writeUTF(s1);

                    if (s1.equalsIgnoreCase("show Movies")) {
                        for (int w = 0; w < 3; w++) {
                            s1 = ccin.readUTF();
                            System.out.println("server : " + s1);
                        }
                        System.out.println("server: Select your order");
                        s1 = cc.nextLine();
                        ccout.writeUTF(s1);
                        String s2 = ccin.readUTF();
                        System.out.println("server : " + s2);
                        byte B[] = new byte[1024];
                        FileOutputStream ff = new FileOutputStream(new File("E:\\NW\\" + s1 + ".MP4"));
                        long br;
                        do {
                            br = ccin.read(B, 0, B.length);
                            ff.write(B, 0, B.length);
                        } while (!(br < 1024));
                        System.out.println("Done");
                        ff.close();
                        System.out.println("Enter close to close the second TCP connection");
                        s1 = cc.nextLine();
                        ccout.writeUTF(s1);
                        if (s1.equalsIgnoreCase("close")) {
                            s1 = ccin.readUTF();
                            System.out.println("server : " + s1);
                            s1 = ccin.readUTF();
                            System.out.println("server : " + s1);
                            s1 = cc.nextLine();
                            ccout.writeUTF(s1);
                            if (s1.equalsIgnoreCase("no")) {
                                bb = false;
                            }
                            ss.close();
                        }
                    } else if (s1.equalsIgnoreCase("show Music")) {
                        for (int w = 0; w < 3; w++) {
                            s1 = ccin.readUTF();
                            System.out.println("server : " + s1);
                        }
                        System.out.println("server: Select your order");
                        s1 = cc.nextLine();
                        ccout.writeUTF(s1);
                        String s2 = ccin.readUTF();
                        System.out.println("server : " + s2);
                        byte B[] = new byte[1024];
                        FileOutputStream ff = new FileOutputStream(new File("E:\\NW\\" + s1 + ".MP4"));
                        long br;
                        do {
                            br = ccin.read(B, 0, B.length);
                            ff.write(B, 0, B.length);
                        } while (!(br < 1024));
                        System.out.println("Done");
                        ff.close();
                        System.out.println("Enter close to close the second TCP connection");
                        s1 = cc.nextLine();
                        ccout.writeUTF(s1);
                        if (s1.equalsIgnoreCase("close")) {
                            s1 = ccin.readUTF();
                            System.out.println("server : " + s1);
                            s1 = ccin.readUTF();
                            System.out.println("server : " + s1);
                            s1 = cc.nextLine();
                            ccout.writeUTF(s1);
                            if (s1.equalsIgnoreCase("no")) {
                                bb = false;
                            }
                            ss.close();
                        }
                    } else if (s1.equalsIgnoreCase("show Docs")) {
                        for (int w = 0; w < 3; w++) {
                            s1 = ccin.readUTF();
                            System.out.println("server : " + s1);
                        }
                        System.out.println("server: Select your order");
                        s1 = cc.nextLine();
                        ccout.writeUTF(s1);
                        String s2 = ccin.readUTF();
                        System.out.println("server : " + s2);
                        byte B[] = new byte[1024];
                        FileOutputStream ff = new FileOutputStream(new File("E:\\NW\\" + s1 + ".MP4"));
                        long br;
                        do {
                            br = ccin.read(B, 0, B.length);
                            ff.write(B, 0, B.length);
                        } while (!(br < 1024));
                        System.out.println("Done");
                        ff.close();
                        System.out.println("Enter close to close the second TCP connection");
                        s1 = cc.nextLine();
                        ccout.writeUTF(s1);
                        if (s1.equalsIgnoreCase("close")) {
                            s1 = ccin.readUTF();
                            System.out.println("server : " + s1);
                            s1 = ccin.readUTF();
                            System.out.println("server : " + s1);
                            s1 = cc.nextLine();
                            ccout.writeUTF(s1);
                            if (s1.equalsIgnoreCase("no")) {
                                bb = false;
                            }
                            ss.close();
                        }
                    } else {
                        s1 = ccin.readUTF();
                        System.out.println("server : " + s1);
                    }
                } else {
                    s1 = ccin.readUTF();
                    System.out.print("server : " + s1);
                }
            }

        } else {
            System.out.println("server : " + s1);
        }
        cin.close();
        cout.close();
        s.close();
        c.close();

    }

}
