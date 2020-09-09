# icbsc.java-demo
[icbsc.java.demo](https://github.com/Baasze/icbsc.java.demo)

## get icbsc.java.jar
[icbsc.java](http://icfs.baasze.com:5002/ipns/bafzm3jqbec7ulhfmm7s7ydt2mf32nbsjy4237mvzj5skzbkxrfxz7axghsyum/icbsc.java)

## use icbsc.java.jar

### import jar

``` json
cp icbsc.java libs
```

### config gralde

```
dependencies {
    compile fileTree(dir:'libs',includes:['*jar'])
    implementation fileTree(dir:'libs',includes:['*jar'])
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
  String sigStr = sm2key.sign(message);
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

### transaction

please see 'test/java/icbsc/java/demo/transactions'