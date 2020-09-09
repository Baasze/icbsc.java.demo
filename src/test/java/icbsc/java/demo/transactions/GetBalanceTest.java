/*
 * @Description: 
 * @Author: kay
 * @Date: 2020-09-08 15:32:06
 * @LastEditTime: 2020-09-08 17:48:45
 * @LastEditors: kay
 */

package icbsc.java.demo.transactions;

import org.junit.Test;

import icbsc.java.javarpcprovider.implementations.*;
import okhttp3.RequestBody;

public class GetBalanceTest {
  @Test
  public void testApp() {
    try {
      JavaRpcProviderImpl rpcProvider = new JavaRpcProviderImpl("http://xxx.xxx.xxx.xxx:8888");
      String jsonData = "{ \"json\": \"true\",\n" +
                          "\"table\": \"accounts\",\n" +
                          "\"scope\": \"cloudadmin\",\n" +
                          "\"code\": \"api.store\"\n" +
                        "}";
      RequestBody requestBody = RequestBody.create(null, jsonData);
      System.out.println(rpcProvider.getTableRows(requestBody));
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}