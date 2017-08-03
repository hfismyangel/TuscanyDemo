package com.jnshu.util;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.IOException;

/**
 * Created by hfismyangel@163.com on 2017/7/13.
 */
public class FileUploadTest {
    //设置好账号的ACCESS_KEY和SECRET_KEY  
    String ACCESS_KEY;//这两个登录七牛 账号里面可以找到
    String SECRET_KEY;

    //要上传的空间
    String bucketname; //填写新建的那个存储空间对象的名称
    //上传到七牛后保存的文件名  

    //上传文件的路径
     //本地要上传文件路径,windows下的文件路径是反斜杠

    //密钥配置

    //创建上传对象


    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
//    public String getUpToken() {
//        return auth.uploadToken(bucketname);
//    }

    //普通上传
    public void upload(String Path,String key) throws IOException {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        UploadManager uploadManager = new UploadManager();
        try {
            //调用put方法上传
            Response res = uploadManager.put(Path, key, auth.uploadToken(bucketname));
            //打印返回的信息
            System.out.println(res.isOK());
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息  
            System.out.println(r.toString());
            try {
                //响应的文本信息  
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore  
            }
        }
    }

    public FileUploadTest() {
    }

    public FileUploadTest(String ACCESS_KEY, String SECRET_KEY, String bucketname){
        this.ACCESS_KEY=ACCESS_KEY;
        this.SECRET_KEY=SECRET_KEY;
        this.bucketname=bucketname;
    }

}
