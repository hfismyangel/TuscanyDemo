package com.jnshu.util;

import com.jnshu.pojo.User;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CacheManager {
    private static MemCachedClient mcc = null;
    static {
        //String[] servers ={"139.199.105.238:11211"};
        String[] servers ={"139.199.105.238:11211"};
        //负载权重
        Integer[] weights = {100};
        //创建一个实例对象SockIOPool,设置mcc参数
        SockIOPool pool = SockIOPool.getInstance();
        pool.setServers( servers );
        pool.setWeights( weights );

        //设置初始连接数、最小和最大连接数以及最大处理时间
        pool.setInitConn( 100 );
        pool.setMinConn( 100 );
        pool.setMaxConn( 500 );
        pool.setMaxIdle( 1000 * 60 * 60 * 6 );  //6小时
        //设置主线程的睡眠时间
        pool.setMaintSleep( 30 );
        pool.setNagle( false );
        pool.setSocketTO( 3000 );
        pool.setSocketConnectTO( 0 );
        pool.initialize();
        //！！！注意：如果是我们自己的对象存放，最好重写序列化方法，不用使用默认。
        mcc = new MemCachedClient();

        //压缩设置，超过指定大小（单位为K）的数据都会被压缩
        // mcc.setCompressEnable(true);
        // mcc.setCompressThreshold(64*1024);

        //使用代码
//        String key="id00001";
//        boolean r = mcc.set(key,"succe2ss", new Date(10 * 60 * 1000));//10分钟
//        System.out.println("SET "+ r);
//
//        System.out.println(mcc.get(key));
//        System.out.println(mcc.get(key));
    }

    public CacheManager(){

    }

    public static MemCachedClient getMemCachedClient(){
        return mcc;
    }

    public boolean set(String key,Object obj){
        return mcc.set(key, obj);
    }

    public boolean set(String key,Object obj,Date date){
        return mcc.set(key, obj,date);
    }

    public Object get(String key){
        return mcc.get(key);
    }

    public boolean add(String key,Object obj){
        return mcc.add(key, obj);
    }

    public static void main(String[] args) {
        User user=new User();
        user.setId(1);
        user.setUserName("ss");
        user.setPassword("dsfaf");
        List list = new ArrayList<User>();
        list.add(user);
            CacheManager.getMemCachedClient().set("11", list);

        System.out.println(CacheManager.getMemCachedClient().get("11"));
    }
}
