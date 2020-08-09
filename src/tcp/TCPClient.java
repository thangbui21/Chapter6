/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Thắng Bùi
 */
public class TCPClient {

    public static void main(String[] args) {
        try {
            
            // Thay message = job
            //2: Client tạo socket
            Socket socket = new Socket("localhost", 3000);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            Scanner scan = new Scanner(System.in);
            String message = "";
            int c = 0;
            while (c != 4) {
                System.out.println("--------------MENU----------------");
                System.out.println("1. Hien thi danh sach nha.");
                System.out.println("2. Thêm nha.");
                System.out.println("3. Mua nha.");
                System.out.println("4. Thoát.");
                System.out.println("Bạn chọn: ");
                c = Integer.parseInt(scan.nextLine());
                dos.writeInt(c);
                switch (c) {
                    case 1:
                        message = "";
                        message = dis.readUTF();
                        System.out.println(message);
                        break;
                    case 2:
                        message = "";
                        System.out.print("Nhập tên nha: ");
                        String name = scan.nextLine();
                        System.out.print("Nhập gia: ");
                        String price = scan.nextLine();
                        // Gan cho no bien true luon.
                        
                        message += name+"$"+price+"$"+"chua ban";
                        dos.writeUTF(message);                            
                        break;
                    case 3:
                        message ="";
                        System.out.println("Nhập tên nha muon mua: ");
                        String tenNha = scan.nextLine();
                        dos.writeUTF(tenNha);
                        
                        String result = dis.readUTF();
                        System.out.println(result);
                        break;
                    case 4:
                        dos.writeUTF("done");
                        break;
                    default:
                        System.out.println("Bạn nhập chưa đúng.");
                }

            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}