import java.io.*;
import java.util.Random;
import java.math.BigInteger;
import java.util.Scanner;
import java.math.BigInteger;
import java.util.Random;
import java.io.*;
import java.util.Scanner;
import java.net.*;  
class Client{  // client 
public static void main(String args[])throws Exception{  
Socket s=new Socket("localhost",3333);  
DataInputStream din = new DataInputStream(s.getInputStream());  
DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  


//Convert message to byte array and then to a BigInteger
         Scanner sc = new Scanner(System.in);
     //    System.out.println("Enter the Message");
          
        BigInteger message;
        String ee,nn;
      // System.out.println("Enter the values of Public Key (e,n)");
        ee = din.readUTF(); 
        nn = din.readUTF(); 
        BigInteger e=new BigInteger(ee);
        BigInteger n=new BigInteger(nn);
        BigInteger cipherText;
        
     //   System.out.println("Message: " + message);
        
       // System.out.println("Public key e: " + e);
     //   System.out.println("Public key n: " + n);
      //  System.out.println("Ciphertext: " + cipherText);
        
  
String str="",str2="";  
while(!str.equals("stop")){  
System.out.print("Enter the Message :");
str=br.readLine();  
message = new BigInteger(str.getBytes());
cipherText = message.modPow(e, n);
String cipher = cipherText.toString();
  // System.out.println("Ciphertext: " + cipherText);
dout.writeUTF(cipher);  
dout.flush();  
str2=din.readUTF();
BigInteger eee=new BigInteger(str2);
BigInteger decryptedText = eee.modPow(e, n);
     //   System.out.println("Ciphertext: " + cipherText);
     //   System.out.println("Decrypted message(number form): " + decryptedText);
        //Convert BigInteger to byte array then to String
        System.out.println("Server : " + new String(decryptedText.toByteArray()));
  
}  
  
dout.close();  
s.close();  
}}  