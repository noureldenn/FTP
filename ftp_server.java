package ftp_project;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ftp_server {

    static String user_pass[];
    static String dir[][];
    static int linesofusers_pass = 0;
    static int linesofdir = 0, k = 1;

    public static void main(String[] args) throws IOException {

        user_pass = new String[50];
        dir = new String[50][51];

        BufferedReader r = new BufferedReader(new FileReader("Names.txt"));
        while (r.readLine() != null) {
            linesofusers_pass++;
        }
        r.close();

        File f1 = new File("Names.txt");
        Scanner scan1 = new Scanner(f1);
        for (int i = 0; i < linesofusers_pass; i++) {
            if (i % 2 == 0) {
                user_pass[i] = scan1.nextLine();
            } else {
                user_pass[i] = scan1.nextLine();
            }

        }
        scan1.close();

        BufferedReader re = new BufferedReader(new FileReader("Directories.txt"));
        while (re.readLine() != null) {
            linesofdir++;
        }
        re.close();

        File f2 = new File("Directories.txt");
        Scanner scan2 = new Scanner(f2);
        for (int i = 0; i < (linesofusers_pass / 2); i++) {
            for (int j = 0; j < linesofdir / (linesofusers_pass / 2); j++) {
                dir[i][j] = scan2.nextLine();
            }
        }
        scan2.close();

        try {
            ServerSocket ser = new ServerSocket(9999); //to connect
            System.out.println("Server is booted up and is waiting for clients to connect.");
            int i = 1;
            while (true) {
                Socket co = ser.accept();
                System.out.println("New Client " + i + " is connected to the server.");
                i++;
                Thread thr = new ServerConnection(co);

                thr.start();

            }
        } catch (IOException e) {
            System.out.println("Problem with Server Socket." + e.getMessage());
        }
    }

    static class ServerConnection extends Thread {

        Socket c = null;

        public ServerConnection(Socket c1) {
            c = c1;
        }

        @Override
        public void run() {

            try {
                DataInputStream cin = new DataInputStream(c.getInputStream());
                DataOutputStream cout = new DataOutputStream(c.getOutputStream());

                cout.writeUTF("Please Enter your username");
                String s1 = cin.readUTF();
                cout.writeUTF("Please Enter your password");
                String s2 = cin.readUTF();
                int gg = -1;
                for (int i = 0; i < linesofusers_pass; i += 2) {
                    if (user_pass[i].equalsIgnoreCase(s1)) {
                        gg = i;
                        gg++;
                        break;
                    }
                }
                if (gg == -1) {
                    cout.writeUTF("Login Failed and the connection will terminate (username or password not correct)");
                    System.out.println("Client " + k + "is closed");
                    k++;
                } else if (s2.equalsIgnoreCase(user_pass[gg])) {
                    int ii = gg - 1;
                    if (ii % 2 == 0) {
                        ii = ii / 2;
                    }
                    cout.writeUTF("Login Successfully");

                    boolean bb = true;
                    while (bb) {
                        s1 = cin.readUTF();
                        System.out.println("client: " + s1);
                        ServerSocket ser1 = new ServerSocket(5555);
                        Socket co1 = ser1.accept();
                        DataInputStream ccin = new DataInputStream(co1.getInputStream());
                        DataOutputStream ccout = new DataOutputStream(co1.getOutputStream());
                        s1 = ccin.readUTF();
                        if (s1.equalsIgnoreCase("show my directories")) {
                            for (int i = 0; i < linesofdir / (linesofusers_pass / 2); i += 4) {
                                ccout.writeUTF(dir[ii][i]);
                            }
                            s1 = ccin.readUTF();
                            if (s1.equalsIgnoreCase("show Movies")) {
                                for (int i = 1; i < 4; i++) {
                                    ccout.writeUTF(dir[ii][i]);
                                }
                                s1 = ccin.readUTF();
                                int ggg = -1;
                                for (int i = 1; i < 4; i++) {
                                    if (s1.equalsIgnoreCase(dir[ii][i])) {
                                        ggg = i;
                                        break;
                                    }
                                }
                                if (ggg == -1) {
                                    ccout.writeUTF("Invlaid");
                                } else {
                                    ccout.writeUTF("I am sending you the file content");
                                    FileInputStream ff = new FileInputStream("Filesofdir/" + dir[ii][ggg] + ".MP4");

                                    byte B[] = new byte[1024];
                                    int rr;
                                    while ((rr = ff.read(B)) != -1) {
                                        ccout.write(B, 0, rr);
                                    }
                                    ff.close();
                                    s1 = ccin.readUTF();
                                    if (s1.equalsIgnoreCase("close")) {
                                        ccout.writeUTF("Connection is terminated");
                                        System.out.println("Client " + k + " close the second connection");
                                        ccout.writeUTF("Do you want Download another File (yes/no) ?");
                                        s1 = ccin.readUTF();
                                        if (s1.equalsIgnoreCase("no")) {
                                            bb = false;
                                            System.out.println("Client " + k + " is closed");
                                            k++;
                                        }
                                        ser1.close();
                                        co1.close();
                                        s1 = "";
                                    }
                                }
                            } else if (s1.equalsIgnoreCase("show Music")) {
                                for (int i = 5; i < 8; i++) {
                                    ccout.writeUTF(dir[ii][i]);
                                }
                                s1 = ccin.readUTF();
                                int ggg = -1;
                                for (int i = 5; i < 8; i++) {
                                    if (s1.equalsIgnoreCase(dir[ii][i])) {
                                        ggg = i;
                                        break;
                                    }
                                }
                                if (ggg == -1) {
                                    ccout.writeUTF("Invlaid");
                                } else {
                                    ccout.writeUTF("I am sending you the file content");
                                    FileInputStream ff = new FileInputStream("Filesofdir/" + dir[ii][ggg] + ".MP3");

                                    byte B[] = new byte[1024];
                                    int rr;
                                    while ((rr = ff.read(B)) != -1) {
                                        ccout.write(B, 0, rr);
                                    }
                                    ff.close();
                                    s1 = ccin.readUTF();
                                    if (s1.equalsIgnoreCase("close")) {
                                        ccout.writeUTF("Connection is terminated");
                                        System.out.println("Client " + k + " close the second connection");
                                        ccout.writeUTF("Do you want Download another File (yes/no) ?");
                                        s1 = ccin.readUTF();
                                        if (s1.equalsIgnoreCase("no")) {
                                            bb = false;
                                            System.out.println("Client " + k + " is closed");
                                            k++;
                                        }
                                        ser1.close();
                                        co1.close();
                                        s1 = "";
                                    }
                                }
                            } else if (s1.equalsIgnoreCase("show Docs")) {
                                for (int i = 9; i < 12; i++) {
                                    ccout.writeUTF(dir[ii][i]);
                                }
                                s1 = ccin.readUTF();
                                int ggg = -1;
                                for (int i = 9; i < 12; i++) {
                                    if (s1.equalsIgnoreCase(dir[ii][i])) {
                                        ggg = i;
                                        break;
                                    }
                                }
                                if (ggg == -1) {
                                    ccout.writeUTF("Invlaid");
                                    ser1.close();
                                    co1.close();
                                } else {
                                    ccout.writeUTF("I am sending you the file content");
                                    FileInputStream ff = new FileInputStream("Filesofdir/" + dir[ii][ggg] + ".txt");

                                    byte B[] = new byte[1024];
                                    int rr;
                                    while ((rr = ff.read(B)) != -1) {
                                        ccout.write(B, 0, rr);
                                    }
                                    ff.close();
                                    s1 = ccin.readUTF();
                                    if (s1.equalsIgnoreCase("close")) {
                                        ccout.writeUTF("Connection is terminated");
                                        System.out.println("Client " + k + " close the second connection");
                                        ccout.writeUTF("Do you want Download another File (yes/no) ?");
                                        s1 = ccin.readUTF();
                                        if (s1.equalsIgnoreCase("no")) {
                                            bb = false;
                                            System.out.println("Client " + k + " is closed");
                                            k++;
                                        }
                                        s1 = "";
                                        ser1.close();
                                        co1.close();

                                    }
                                }
                            } else {
                                ccout.writeUTF("your order not found");
                                ser1.close();
                                co1.close();
                            }
                        } else {
                            ccout.writeUTF("Invalid order..!  ");
                            ser1.close();
                            co1.close();
                        }

                    }

                } else {
                    cout.writeUTF("Login Failed and the connection will terminate ( password not correct )");
                    System.out.println("Client " + k + "is closed");
                    k++;
                }
                cin.close();
                cout.close();
                c.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
}
