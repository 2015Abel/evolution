package com.evolution.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName InetAddressTest
 * @Description: 网络地址
 * @Author: Lzj
 * @Date: 2019/8/29 16:46
 */
public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getByName("baidu.com");

        System.out.println(InetAddress.getByName("220.181.38.148.baidu.com"));
    }
}
