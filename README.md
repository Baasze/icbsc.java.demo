# icbsc.java-demo
[icbsc.java.demo](https://github.com/Baasze/icbsc.java.demo)

## get icbsc.java.jar
[icbsc.java](http://icfs.baasze.com:5002/ipns/bafzm3jqbec7ulhfmm7s7ydt2mf32nbsjy4237mvzj5skzbkxrfxz7axghsyum/icbsc.java)

## use icbsc.java.jar

### import jar

``` json
cp icbsc.java libs
```

### config gradle

```
dependencies {
    compile fileTree(dir:'libs',includes:['*jar'])
    implementation fileTree(dir:'libs',includes:['*jar'])

    compile 'com.squareup.retrofit2:converter-gson:2.5.0'
    compile 'com.squareup.okhttp3:logging-interceptor:3.12.1'
    compile 'one.block:eosiojava:0.1.2'
    compile 'org.json:json:20141113'
    ...
}
```

## test

### eckey

``` java
  import icbsc.java.eckey.*;

  ECKey sm2key = new ECKey();
  System.out.println(sm2key.GetPrivate());
  System.out.println(sm2key.GetPublic());
  System.out.Println(ECKey.PrivateToPublic(sm2key.GetPrivate())

  String message = "Hello World!";
  // use sha256 to hash the message
  String sigStr = "";
  try {
    sigStr = sm2key.sign(message);
  } catch (Exception e){
    e.printStackTrace();
  }
  // use sha256 to hash the bytes
  // String sigStr = sm2key.sign(message.getBytes());
  System.out.println(sigStr);

  // verify the sigStr is signed by key 
  try {
      sm2key.verifyMessage(message.getBytes(), sigStr);
  } catch (SignatureException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
  }

  // recover the publicKey by sigStr and message 
  try {
      String recoverKey = ECKey.signedMessageToKey(message.getBytes(), sigStr);
      System.out.println(recoverKey);
  } catch (SignatureException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
  }
```

### crypto

1、 `doEncrypt` and `doDecrypt`:

```java
import icbsc.java.eckey.ECKey;
import icbsc.java.crypto.*;

ECKey key = new ECKey();
System.out.println(key.GetPrivate());
System.out.println(key.GetPublic());

String msg = "hello world!!!!!!!!!!!!!!!!!!";
String encrypted = sm2.doEncrypt(msg.getBytes(), key.GetPublic());
System.out.println(encrypted);

byte[] msgBytes = sm2.doDecrypt(encrypted, key.GetPrivate());
System.out.println(new String(msgBytes));
```

2、`doSignature` and `doVerifySignature`

```java
import icbsc.java.eckey.ECKey;
import icbsc.java.crypto.*;

ECKey key = new ECKey();
System.out.println(key.GetPrivate());
System.out.println(key.GetPublic());
String msg = "hello world!!!!!!!!!!!!!!!!!!";
String sigHex = sm2.doSignature(msg.getBytes(), key.GetPrivate());
System.out.println(sigHex);
System.out.println("verify: " + sm2.doVerifySignature(msg.getBytes(), sigHex, key.GetPublic()));
```

3、`SM3`

```java
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
```

4、 `SM4`

```java
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
```

### transaction

please see 'test/java/icbsc/java/demo/transactions'
