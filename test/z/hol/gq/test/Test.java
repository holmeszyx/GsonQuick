package z.hol.gq.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import z.hol.gq.GsonQuick;

public class Test {
    
    public static class DataItem{
        long d;
        long a;
        @SerializedName("bigHash")
        String bigHashId;
        @Override
        public String toString() {
            return "DataItem [d=" + d + ", a=" + a + ", bigHashId=" + bigHashId
                    + "]";
        }
    }
    
    public static class TestObj{
        public String k;
        @SerializedName("v")
        public int value;
        
        @Override
        public String toString() {
            return "TestObj [k=" + k + ", value=" + value + "]";
        }
        
        
    }

    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream(new File("test.json"));
        ByteArrayOutputStream content = new ByteArrayOutputStream(2048);
        byte[] buff = new byte[1024];
        int readed = -1;
        while((readed = in.read(buff)) != -1){
            content.write(buff, 0, readed);
        }
        in.close();
        String json = content.toString();
        
        /*
            {
                "data": [{
                    "d": 771133,
                    "a": 856813,
                    "bigHash": "392b979d9568b2c9cc693ce577d7fadf"
                },{
                    "d": 123,
                    "a": 43234,
                    "bigHash": "e67fdad9568b2c9cc693ce52017463ba"
                }],
                "CID": "123",
                "model": "ZTE U970",
                "int":8880,
                "obj":{"k":"final", "v":777}
            }
        */
        out(GsonQuick.getString(json, "CID"));
        out(GsonQuick.getString(json, "int"));
        out("origin json :");
        out(json);
        String dataJson = GsonQuick.getString(json, "data");
        out("key data as string(json string): ");
        out(dataJson);
        List<DataItem> dataList = GsonQuick.toList(dataJson, DataItem.class);
        //Type type = new TypeToken<ArrayList<DataItem>>(){}.getType();
        //List<DataItem> dataList = GsonUtils.getGson().fromJson(dataJson, type);
        out("paras data json to list: ");
        outList(dataList);
        String objJson = GsonQuick.getString(json, "obj");
        out("paras obj");
        TestObj testObj = GsonQuick.toObject(objJson, TestObj.class);
        out(testObj.toString());
    }
    
    private static void out(String msg){
        System.out.println(msg);
    }
    
    private static <T> void outList(List<T> list){
        for (T t : list){
            System.out.println("  --> " + t.getClass().getName());
            System.out.println(t.toString());
        }
    }
}
