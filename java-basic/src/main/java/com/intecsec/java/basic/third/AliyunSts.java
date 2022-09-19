package com.intecsec.java.basic.third;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.internal.OSSHeaders;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.StorageClass;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.google.gson.Gson;

import java.io.File;
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

    private static final String endpoint = "";
    private static final String ossEndpoint = "https://oss-cn-shanghai.aliyuncs.com";
    private static final String regionId = "cn-shanghai";
    private static final String accessKeyId = "";
    private static final String accessKeySecret = "";
    private static final String bucketName = "";
    private static final String  objectName = "";
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
        uploadFile();
        //getSTSURL();
    }

    public static void uploadFile() throws Exception {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        // String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        // String accessKeyId = "yourAccessKeyId";
        // String accessKeySecret = "yourAccessKeySecret";
        // 填写Bucket名称，例如examplebucket。
        //String bucketName = "examplebucket";
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        // String objectName = "exampledir/exampleobject.txt";
        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
        String filePath= "E:\\my\\identityCard.jpg";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(ossEndpoint, accessKeyId, accessKeySecret);

        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(filePath));
            // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setHeader(OSSHeaders.OSS_SERVER_SIDE_ENCRYPTION, "AES256");
            metadata.setObjectAcl(CannedAccessControlList.Private);
            putObjectRequest.setMetadata(metadata);

            // 上传文件。
            ossClient.putObject(putObjectRequest);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
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
        //String objectName = "idcard/identityCard.jpg";

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
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
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
