GsonQuick
=========

Gson的辅助工具类, 方便Gson的序列化


使用
====

可以看Test里面的代码.示例Json如下:

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



1. 直接序列化为对象

        TestObj testObj = GsonQuick.toObject(objJson, TestObj.class);
        
2. 序列化为对象List

        List<DataItem> dataList = GsonQuick.toList(dataJson, DataItem.class);
        
3. 获取Json的字符串

        String dataJson = GsonQuick.getString(json, "data");
        dataJson的值为:
        [{"d":771133,"a":856813,"bigHash":"392b979d9568b2c9cc693ce577d7fadf"},{"d":123,"a":43234,"bigHash":"e67fdad9568b2c9cc693ce52017463ba"}]
         