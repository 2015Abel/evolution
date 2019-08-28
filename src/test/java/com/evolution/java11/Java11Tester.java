package com.evolution.java11;

import com.alibaba.fastjson.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import sun.misc.Unsafe;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @ClassName Java11Tester
 * @Description: 11的新特新
 * @Author: Lzj
 * @Date: 2019/6/21 15:49
 */
@RunWith(JUnit4.class)
public class Java11Tester {

    @Test
    public void testHttpClient(){
        Collection<String> collection = new ArrayList<>();
        collection.add("1");
        collection.add("2");
        collection.add("3");
        String[] res = collection.toArray(new String[]{});
        System.out.println(JSONArray.toJSONString(res));

        VarHandle varHandle = null;
    }

    @Test
    public void testUnsafe() throws Exception {
        Unsafe unsafe = Unsafe.getUnsafe();
        unsafe.putAddress(0L,99);
        long val = unsafe.getAddress(0L);
        System.out.println(val);

        VarHandle varHandle = MethodHandles.privateLookupIn(Unsafe.class,MethodHandles.lookup())
                .findStaticVarHandle(Unsafe.class,"theUnsafe",Unsafe.class);
    }

}
