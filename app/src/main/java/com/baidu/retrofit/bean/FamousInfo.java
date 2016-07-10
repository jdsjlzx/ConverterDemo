package com.baidu.retrofit.bean;

import java.util.List;

public class FamousInfo {
    /*{
        "total": 227,
            "result": [
        {
            "famous_name": "车尔尼雪夫斯基",
                "famous_saying": "非凡的单纯，非凡的明确——这是天才的智慧的最可惊人的品质。"
        },
        {
            "famous_name": "约·德莱顿",
                "famous_saying": "天才在社会生活中往往显得迟钝而"
        },
        {
            "famous_name": "雨果",
                "famous_saying": "敢于冲撞命运才是天才"
        },
        {
            "famous_name": "卡莱尔",
                "famous_saying": "所谓天才，就是比任何人都先抵挡痛苦的经验本领。"
        },
        {
            "famous_name": "林肯",
                "famous_saying": "卓越的天才不屑走一条人家走过的路。他寻找迄今没有开拓过的地区。"
        }
        ],
        "error_code": 0,
            "reason": "Succes"
    }*/

    //下面变量的定义要与接口中的字段名字保持一致
    public int total;
    public int error_code;
    public String reason;
    public List<ResultEntity> result;


    public static class ResultEntity {
        public String famous_name;
        public String famous_saying;
    }

    @Override
    public String toString() {
        return "total =  " + total;
    }

}
