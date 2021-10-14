import java.io.*;
import java.util.Random;
import java.math.BigInteger;
import java.util.Scanner;
import java.math.BigInteger;
import java.util.Random;
import java.io.*;
import java.util.Scanner;
import java.net.*;  

class Server{ //server  
public static void main(String args[])throws Exception{  
ServerSocket ss=new ServerSocket(3333);  
Socket s=ss.accept();  
DataInputStream din=new DataInputStream(s.getInputStream());  
DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 

 //Size of primes
        int BITLENGTH = 256;
        Random rand = new Random();
        //Generate primes and other necessary values
        BigInteger p = BigInteger.probablePrime(BITLENGTH / 2, rand);
        BigInteger q = BigInteger.probablePrime(BITLENGTH / 2, rand);
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.valueOf(1)).multiply(q.subtract(BigInteger.valueOf(1)));
        BigInteger e;
        BigInteger d;
        do {
            e = new BigInteger(phi.bitLength(), rand);
        } while (e.compareTo(BigInteger.valueOf(1)) <= 0 || e.compareTo(phi) >= 0 || !e.gcd(phi).equals(BigInteger.valueOf(1)));
        d = e.modInverse(phi);
//System.out.println("Public key e: " + e);
//System.out.println("Public key n: " + n);
        String ee = e.toString();
        String nn = n.toString();
dout.writeUTF(ee);
dout.writeUTF(nn);
String str="",str2="";  
while(!str.equals("stop")){  
str=din.readUTF();
BigInteger eee=new BigInteger(str);
BigInteger decryptedText = eee.modPow(d, n);
    //   System.out.println("Ciphertext: " + eee);
        //System.out.println("Decrypted message(number form): " + decryptedText);
        //Convert BigInteger to byte array then to String
        System.out.println("Client : " + new String(decryptedText.toByteArray()));
System.out.print("Enter the Message : ");
str2=br.readLine();

BigInteger message = new BigInteger(str2.getBytes());
BigInteger cipherText = message.modPow(d, n);
String cipher = cipherText.toString();
dout.writeUTF(cipher);  
dout.flush();  
}  
din.close();  
s.close();  
ss.close();  
}}  