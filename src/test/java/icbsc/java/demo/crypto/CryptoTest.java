package icbsc.java.demo.crypto;

import org.junit.Test;
import icbsc.java.eckey.ECKey;
import icbsc.java.crypto.*;

public class CryptoTest {
  @Test
  public void sm2Test() throws Exception {
    for (int i = 0; i < 100; i++) {
      ECKey key = new ECKey();
      System.out.println(key.GetPrivate());
      System.out.println(key.GetPublic());

      String msg = "hello world!!!!!!!!!!!!!!!!!!";
      String encrypted = sm2.doEncrypt(msg.getBytes(), key.GetPublic());
      System.out.println(encrypted);

      byte[] msgBytes = sm2.doDecrypt(encrypted, key.GetPrivate());
      System.out.println(new String(msgBytes));
      assert(msg.equals(new String(msgBytes)));
    }
  }
  @Test
  public void signTest() throws Exception {
    ECKey key = new ECKey();
    System.out.println(key.GetPrivate());
    System.out.println(key.GetPublic());
    String msg = "hello world!!!!!!!!!!!!!!!!!!";
    String sigHex = sm2.doSignature(msg.getBytes(), key.GetPrivate());
    System.out.println(sigHex);
    assert (sm2.doVerifySignature(msg.getBytes(), sigHex, key.GetPublic()));
  }

  @Test
  public void sm4Test() throws Exception {
    String msg = "hello world! 我是 juneandgreen.";
    String key = "0123456789abcdeffedcba9876543210"; // 128 bit
    // 默认 PKCS5Padding
    String data = sm4.encrypt(msg.getBytes(), key);
    System.out.println(data);
    System.out.println(new String(sm4.decrypt(data, key), "UTF-8"));

    // false 为 NoPadding 数据被截断加密 block size 16 bytes
    data = sm4.encrypt(msg.getBytes(), key, false);
    System.out.println(data);
    System.out.println(new String(sm4.decrypt(data, key, false), "UTF-8"));
  }

  @Test
  public void sm3Test() {
    String msg = "abc";
    sm3 digest = new sm3();
    System.out.println(bytesToHex(digest.hash(msg.getBytes())));

    String a = "a";
    String bc = "bc";
    digest.update(a.getBytes());
    digest.update(bc.getBytes(), 0, bc.getBytes().length);
    System.out.println(bytesToHex(digest.doFinal()));
  }

  public static String bytesToHex(byte[] bytes) {  
    StringBuffer sb = new StringBuffer();  
    for(int i = 0; i < bytes.length; i++) {  
        String hex = Integer.toHexString(bytes[i] & 0xFF);  
        if(hex.length() < 2){  
            sb.append(0);  
        }  
        sb.append(hex);  
    }  
    return sb.toString();  
  } 
}