/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.*;
import java.net.*;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Thắng Bùi
 */
public class TCPServer {

    public ArrayList<Nha> getList() {
        ArrayList<Nha> list = new ArrayList<>();

        try {
            FileReader fr = new FileReader("src\\tcp\\Nha.txt");
            BufferedReader br = new BufferedReader(fr);
            String s = "";

            while ((s = br.readLine()) != null) {
                String[] data = s.split("\\$");
                boolean x = true;
                if (data[2].equalsIgnoreCase("ban")) {
                    x = false;
                }
                Nha e = new Nha(data[0], Float.parseFloat(data[1]), x);
                list.add(e);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public static void main(String[] args) {
        TCPServer server = new TCPServer();
        try {
            //1
            ServerSocket myServer = new ServerSocket(3000);
            System.out.println("Server is ready!");

            while (true) {
                Socket socket = myServer.accept();
                System.out.println("Client connected!");
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                Scanner scan = new Scanner(System.in);
                String message = "";
                int c = 0;

                while (c != 4) {
                    c = dis.readInt();
                    switch (c) {
                        case 1:
                            String result = "";
                            for (Nha emp : server.getList()) {
                                result += emp.showInfo();
                            }
                            dos.writeUTF(result);
                            break;
                        case 2:
                            message = dis.readUTF();

                            File file = new File("src\\tcp\\Nha.txt");
                            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                            BufferedWriter bw = new BufferedWriter(fw);
                            bw.write("\n" + message);

                            bw.close();
                            fw.close();
                            break;
                        case 3:
                            String result3 = "";
                            message = dis.readUTF();
                            for (int i = 0; i < server.getList().size(); i++) {
                                if (message.equalsIgnoreCase(server.getList().get(i).getSoNha())) {
                                    //server.getList().get(i).setTinhTrang(false);
                                    result3 += server.getList().get(i).showInfo();
                                }
                            }
                            dos.writeUTF(result3);
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Bạn nhập sai hãy nhập lại");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}