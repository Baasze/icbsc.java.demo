/*
 * @Description: 
 * @Author: kay
 * @Date: 2020-09-08 10:42:00
 * @LastEditTime: 2020-09-09 16:33:25
 * @LastEditors: sandman
 */

package icbsc.java.demo.transactions;

import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import one.block.eosiojava.implementations.ABIProviderImpl;
import one.block.eosiojava.interfaces.IABIProvider;
import one.block.eosiojava.interfaces.IRPCProvider;
import one.block.eosiojava.interfaces.ISerializationProvider;
import one.block.eosiojava.models.rpcProvider.Action;
import one.block.eosiojava.models.rpcProvider.Authorization;
import one.block.eosiojava.models.rpcProvider.response.PushTransactionResponse;
import one.block.eosiojava.session.TransactionProcessor;
import one.block.eosiojava.session.TransactionSession;
import com.google.gson.Gson;

import icbsc.java.javarpcprovider.implementations.*;
import icbsc.java.keysignatureprovider.*;
import icbsc.java.abiserializationprovider.*;
import icbsc.java.eckey.*;

public class CreateAccountTest {
  @Test
  public void testApp(){
    try{
      String privateKey = "PVT_K1_hqrWiRUQ2eHRCrp7998fjQujwgiTFUjbxgBifB7tEZ1Dhbsc8";

      IRPCProvider rpcProvider = new JavaRpcProviderImpl("http://xxx.xxx.xxx.xxx:8888");
      ISerializationProvider serializationProvider = new AbiSerializationProviderImpl();
      IABIProvider abiProvider = new ABIProviderImpl(rpcProvider, serializationProvider);
      SignatureProviderImpl signatureProvider = new SignatureProviderImpl();
      
      signatureProvider.importKey(privateKey);
      List<String> keys = signatureProvider.getAvailableKeys();
      System.out.println(keys.get(0));
      
      TransactionSession session = new TransactionSession(
        serializationProvider,
        rpcProvider,
        abiProvider,
        signatureProvider
      );
      
      TransactionProcessor processor = session.getTransactionProcessor();
      String creator = "cloudadmin";
      String permission = "active";
      String newAccount = createAccount();

      System.out.println("新账户名: " + newAccount);

      ECKey sm2key = new ECKey(ECKey.KeyType.SM2);
      System.out.println("公私钥对: ");
      System.out.println("Public key: " + sm2key.GetPrivate());
      System.out.println("Private key: " + sm2key.GetPublic());
      
      String jsonData = "{\n" +
              "\"creator\": \"" + creator + "\",\n" +
              "\"name\": \"" + newAccount + "\",\n" +
              "\"owner\": {\n" +
              "\"threshold\": 1,\n" +
              "\"keys\": [{\n" +
              "\"key\": \"" + sm2key.GetPublic() + "\",\n" +
              "\"weight\": 1\n" +
              "}],\n" +
              "\"accounts\": [],\n" +
              "\"waits\": []\n" +
              "},\n" +
              "\"active\": {\n" +
              "\"threshold\": 1,\n" +
              "\"keys\": [{\n" +
              "\"key\": \"" + sm2key.GetPublic() + "\",\n" +
              "\"weight\": 1\n" +
              "}],\n" +
              "\"accounts\": [],\n" +
              "\"waits\": []\n" +
              "}\n" +
              "}";

      System.out.println("jsonData: " + jsonData);

      List<Authorization> authorizations = new ArrayList<>();
      authorizations.add(new Authorization(creator, permission));
      List<Action> actions = new ArrayList<>();
      actions.add(new Action("icbs", "newaccount", authorizations, jsonData));
      processor.prepare(actions);
      PushTransactionResponse pushTransactionResponse = processor.signAndBroadcast();
      System.out.println(new Gson().toJson(pushTransactionResponse));
    } catch (Exception e){
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public static String createAccount() {
    String str = "abcdefghijklmnopqrstuvwxyz12345";
    int len = str.length();
    Random random = new Random();
    StringBuffer sb = new StringBuffer();
    for(int i = 0; i < 12; ++i) {
      int number = random.nextInt(len);
      sb.append(str.charAt(number));
    }
    return sb.toString();
  }
}