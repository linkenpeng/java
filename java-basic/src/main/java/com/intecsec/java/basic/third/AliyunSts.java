package com.intecsec.java.basic.third;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.google.gson.Gson;

import java.net.URL;
import java.util.Date;

/**
 * Created by Peter.Peng
 * Created on 2022/9/16 14:38

 <dependency>
     <groupId>com.aliyun.oss</groupId>
     <artifactId>aliyun-sdk-oss</artifactId>
     <version>3.15.1</version>
 </dependency>
 <dependency>
     <groupId>com.aliyun</groupId>
     <artifactId>aliyun-java-sdk-sts</artifactId>
     <version>3.0.0</version>
 </dependency>
 <dependency>
     <groupId>com.aliyun</groupId>
     <artifactId>aliyun-java-sdk-core</artifactId>
     <version>4.4.6</version>
 </dependency>

 */
public class AliyunSts {

    private static final String endpoint = "oss-cn-shanghai.aliyuncs.com";
    private static final String regionId = "cn-shanghai";
    private static final String accessKeyId = "";
    private static final String secret = "";
    private static final String bucketName = "";
    private static final String roleArn = "";
    private static final String policy = "{" +
            "    \"Version\": \"1\", " +
            "    \"Statement\": [" +
            "        {" +
            "            \"Action\": [" +
            "                \"oss:PutObject\", \"oss:GetObject\"" +
            "            ], " +
            "            \"Resource\": [], " +
            "            \"Effect\": \"Allow\"" +
            "        }" +
            "    ]" +
            "}";

    public static void main(String[] args) throws Throwable {
        getSTSURL();
    }

    public static URL getSTSURL() {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        // getRole1();
        AssumeRoleResponse response = getRole();
        // 从STS服务获取的临时访问密钥（AccessKey ID和AccessKey Secret）。
        String accessKeyId = response.getCredentials().getAccessKeyId();
        String accessKeySecret = response.getCredentials().getAccessKeySecret();
        // 从STS服务获取的安全令牌（SecurityToken）。
        String securityToken = response.getCredentials().getSecurityToken();
        // 填写Object完整路径，例如exampleobject.txt。Object完整路径中不能包含Bucket名称。
        String objectName = "";

        // 从STS服务获取临时访问凭证后，您可以通过临时访问密钥和安全令牌生成OSSClient。
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, securityToken);

        URL url = null;
        try {
            // 设置签名URL过期时间，单位为毫秒。
            Date expiration = new Date(new Date().getTime() + 3600 * 1000);
            // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
            url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
            System.out.println(url);
        } catch (OSSException oe) {
           oe.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return url;
    }


    public static AssumeRoleResponse getRole() {
        //构建一个阿里云客户端，用于发起请求。
        //设置调用者（RAM用户或RAM角色）的AccessKey ID和AccessKey Secret。
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
        IAcsClient client = new DefaultAcsClient(profile);

        //构造请求，设置参数。
        AssumeRoleRequest request = new AssumeRoleRequest();
        request.setRegionId(regionId);
        request.setRoleArn(roleArn);
        request.setRoleSessionName("RoleSessionName");
        request.setPolicy(policy);

        //发起请求，并得到响应。
        AssumeRoleResponse response = null;
        try {
            response = client.getAcsResponse(request);
            System.out.println("response: " + new Gson().toJson(response));
            return  response;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return response;
    }

}
