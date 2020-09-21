package icbsc.java.demo.crypto;

import org.junit.Test;
import icbsc.java.eckey.ECKey;
import icbsc.java.crypto.*;

public class sm2Test {
  @Test
  public void cryptoTest() throws Exception {
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
}