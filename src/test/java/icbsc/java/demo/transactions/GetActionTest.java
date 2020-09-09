/*
 * @Description: 
 * @Author: kay
 * @Date: 2020-09-08 17:15:23
 * @LastEditTime: 2020-09-09 16:33:37
 * @LastEditors: sandman
 */

package icbsc.java.demo.transactions;
import org.junit.Test;

import icbsc.java.javarpcprovider.implementations.*;
import okhttp3.RequestBody;

public class GetActionTest {
  @Test
  public void testApp() {
    try {
      JavaRpcProviderImpl rpcProvider = new JavaRpcProviderImpl("http://xxx.xxx.xxx.xxx:8888");

      String jsonData = "{ \"account_name\": \"cloudadmin\",\n" +
                          "\"pos\": \"\",\n" +
                          "\"offset\": 20\n" +
                        "}";
      RequestBody requestBody = RequestBody.create(null, jsonData);
      System.out.println(rpcProvider.getActions(requestBody));
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}