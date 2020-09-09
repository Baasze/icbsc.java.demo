/*
 * @Description: 
 * @Author: kay
 * @Date: 2020-09-08 17:09:07
 * @LastEditTime: 2020-09-09 16:33:44
 * @LastEditors: sandman
 */
package icbsc.java.demo.transactions;
import org.junit.Test;

import icbsc.java.javarpcprovider.implementations.*;
import okhttp3.RequestBody;

public class GetTransactionTest {
  @Test
  public void testApp() {
    try {
      JavaRpcProviderImpl rpcProvider = new JavaRpcProviderImpl("http://xxx.xxx.xxx.xxx:8888");
      String jsonData = "{ \"id\": \"8fbab5a3c0a6ce8319346200fe8f398272a6abdebe974c39c2af49787cd71d86\"}";
      RequestBody requestBody = RequestBody.create(null, jsonData);
      // rpcProvider.getTransaction(requestBody);
      System.out.println(rpcProvider.getTransaction(requestBody));
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}