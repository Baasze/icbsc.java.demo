/*
 * @Description: 
 * @Author: kay
 * @Date: 2020-09-08 17:30:13
 * @LastEditTime: 2020-09-08 17:43:16
 * @LastEditors: kay
 */
package icbsc.java.demo.transactions;

import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

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

public class TransferTest {
  @Test
  public void TestApp() {
    try {
      String privateKey = "PVT_K1_hqrWiRUQ2eHRCrp7998fjQujwgiTFUjbxgBifB7tEZ1Dhbsc8";
      String permission = "active";
      IRPCProvider rpcProvider = new JavaRpcProviderImpl("http://xxx.xxx.xxx.xxx:8888");
      ISerializationProvider serializationProvider = new AbiSerializationProviderImpl();
      IABIProvider abiProvider = new ABIProviderImpl(rpcProvider, serializationProvider);
      SignatureProviderImpl signatureProvider = new SignatureProviderImpl();

      signatureProvider.importKey(privateKey);
      TransactionSession session = new TransactionSession(serializationProvider, rpcProvider, abiProvider,
          signatureProvider);

      TransactionProcessor processor = session.getTransactionProcessor();
      String fromAccount = "cloudadmin";
      String toAccount = "cloudtest1";
      String contractName = "api.store";
      String actionName = "transfer";
      String quantity = "0.0001 YLZ";
      String memo = "test";

      String jsonData = "{\n" + "\"from\": \"" + fromAccount + "\",\n" + "\"to\": \"" + toAccount + "\",\n"
          + "\"quantity\": \"" + quantity + "\",\n" + "\"memo\" : \"" + memo + "\"\n" + "}";

      List<Authorization> authorizations = new ArrayList<>();
      authorizations.add(new Authorization(fromAccount, permission));
      List<Action> actions = new ArrayList<>();
      actions.add(new Action(contractName, actionName, authorizations, jsonData));
      processor.prepare(actions);
      PushTransactionResponse pushTransactionResponse = processor.signAndBroadcast();
      System.out.println(new Gson().toJson(pushTransactionResponse));
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}