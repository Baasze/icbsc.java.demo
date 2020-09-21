/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package icbsc.java.demo.eckey;

import icbsc.java.eckey.*;

import org.junit.Test;
import static org.junit.Assert.*;

import java.security.SignatureException;

public class ECKeyTest {
    @Test
    public void testApp() {
        String priv = "PVT_SM2_Yxo3RKaoUW2hHQt3BJyU2wmQjjJ1ADatxCo3LxwBK9LSyH3sX";
        String pub = "PUB_SM2_7VZMGC13BBjvN8PJx7CdQK3fFg9ZWtjMBrTx3h8hL3faaiPfmV";
        String signedStr = "SIG_SM2_HDqVAQzQjYbKsgE8s64adP8V9Vfn9NSvvzSzpvsNNyobLy28CPaeuXpKWGxWsFiv4LvmmRQWa6rmjjNMJ4f83t9yTAoXNG";
        String msg = "Hello World!";
        ECKey key = ECKey.ECKeyFromPrivate(priv);
        assert (key.GetPublic().equals(pub));
        assert (key.GetPublic().equals(ECKey.PrivateToPublic(key.GetPrivate())));

        try {
            key.verifyMessage(msg.getBytes(),signedStr);
        } catch (SignatureException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            String recoverKey = ECKey.signedMessageToKey(msg.getBytes(), signedStr);
            assert (recoverKey.equals(pub));
        } catch (SignatureException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 默认SM2公私密钥转换
        for (int i = 0; i < 1; i++) {
            ECKey sm2key = new ECKey();
            System.out.println(sm2key.GetPrivate());
            System.out.println(sm2key.GetPublic());
            assertTrue(ECKey.PrivateToPublic(sm2key.GetPrivate()).equals(sm2key.GetPublic()));

            String message = "Hello World!";
            // 内部对String做sha256 hash后签名
            String sigStr = "";
            try {
                sigStr = sm2key.sign(message);
            } catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(sigStr);

            // 内部对 byte[] 做sha256 hash后签名
            String sigStr1 = "";
            try {
                sigStr1 = sm2key.sign(message.getBytes());
            } catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(sigStr1);

            try {
                sm2key.verifyMessage(message.getBytes(), sigStr);
            } catch (SignatureException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            String recoverKey = "";
            try {
                recoverKey = ECKey.signedMessageToKey(message.getBytes(), sigStr);
            } catch (SignatureException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            assert (recoverKey.equals(sm2key.GetPublic()));
            System.out.println("recoverKey: " + recoverKey);

            // K1密钥
            // ECKey k1key = new ECKey(ECKey.KeyType.K1);
            // System.out.println(k1key.GetPrivate());
            // System.out.println(k1key.GetPublic());
            // assert (ECKey.PrivateToPublic(k1key.GetPrivate()).equals(k1key.GetPublic()));
        }
        System.out.println("complete!");
    }
}
