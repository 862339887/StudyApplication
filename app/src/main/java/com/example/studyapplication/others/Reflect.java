package com.example.studyapplication.others;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by zhouxin on 2022/10/1
 *
 * @author zhouxin.3012143@bytedance.com
 */
public class Reflect {
    public void initView() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        try {


            final Class<?> c = Thread.currentThread().getContextClassLoader().loadClass("com.example.studyapplication.others.AcessCheck");
            Object obj =c.newInstance();
            c.cast(obj);
            Log.e("zhouxin-success",obj.toString());

//            final Method m = c.getDeclaredMethod("printStuff", String.class, ArrayList.class);
//            m.setAccessible(true);
//            ArrayList<String> list =new ArrayList<String>();
//            list.add("11");
//            list.add("22");
//            m.invoke(c.newInstance(), "233",list);
        }catch (Exception e){
            Log.e("zhouxin-failure",e.toString());
        }


    }
}
class  Test {

}