//package com.qcl.utils;
//
//import com.qcloud.cos.COSClient;
//import com.qcloud.cos.ClientConfig;
//import com.qcloud.cos.auth.BasicCOSCredentials;
//import com.qcloud.cos.auth.COSCredentials;
//import com.qcloud.cos.exception.CosClientException;
//import com.qcloud.cos.exception.CosServiceException;
//import com.qcloud.cos.model.ObjectMetadata;
//import com.qcloud.cos.model.PutObjectRequest;
//import com.qcloud.cos.model.PutObjectResult;
//import com.qcloud.cos.region.Region;
//import com.tencent.cloud.CosStsClient;
//import org.json.JSONObject;
//import java.io.File;
//import java.util.TreeMap;
//
///**
// * 腾讯云cos服务器上传工具类
// * @author Mr.song
// * @date 2019/06/08 20:52
// */
//public class TencentUploadUtil {
//
//    //腾讯云的SecretId
//    private static String secretId;
//    //腾讯云的SecretKey
//    private static String secretKey;
//    //腾讯云的bucket (存储桶)
//    private static String bucket;
//    //腾讯云的region(bucket所在地区)
//    private static String region;
//    //腾讯云的allowPrefix(允许上传的路径)
//    private static String allowPrefix;
//    //腾讯云的临时密钥时长(单位秒)
//    private static String durationSeconds;
//    //腾讯云的访问基础链接:
//    private static String baseUrl;
//    //读取配置文件，初始化配置
//    static {
////        ResourceBundle bundle = ResourceBundle.getBundle("properties/application");
////        secretId = bundle.getString("tencent.SecretId");
////        secretKey = bundle.getString("tencent.SecretKey");
////        bucket = bundle.getString("tencent.bucket");
////        region = bundle.getString("tencent.region");
////        allowPrefix = bundle.getString("tencent.allowPrefix");
////        durationSeconds = bundle.getString("tencent.durationSeconds");
////        baseUrl = bundle.getString("tencent.baseUrl");
//        secretId = "AKIDrb9SYPbMn1zmOno25EGcpnW8VgnpdFsN";
//        secretKey = "TrCsPO7artiKo37wWrwmOuAE8rLchWCm";
//        bucket = "niuyabo-1257122371";
//        region = "ap-chengdu";
//        allowPrefix = "*";
//        durationSeconds = "1800";
//        baseUrl = "https://niuyabo-1257122371.cos.ap-chengdu.myqcloud.com/";
//    }
//
//    /**
//     * 上传文件
//     *
//     * @param path 文件服务器下的根路径,即key,如: doc/picture.jpg
//     * @param file
//     * @return 成功返回文件路径,失败返回null
//     */
//    public static String uploadFile(String path, File file) {
//        //获取临时密钥
//        JSONObject temp = getTempKey();
//        // 用户基本信息:解析临时密钥中的相关信息
//        String tmpSecretId = temp.getJSONObject("credentials").getString("tmpSecretId");
//        String tmpSecretKey = temp.getJSONObject("credentials").getString("tmpSecretKey");
//        String sessionToken = temp.getJSONObject("credentials").getString("sessionToken");
//
//        // 1 初始化用户身份信息(secretId, secretKey)
//        COSCredentials cred = new BasicCOSCredentials(tmpSecretId, tmpSecretKey);
//        // 2 设置 bucket 区域
//        ClientConfig clientConfig = new ClientConfig(new Region(region));
//        // 3 生成 cos 客户端
//        COSClient cosclient = new COSClient(cred, clientConfig);
//        // bucket名需包含appid
//        String bucketName = bucket;
//        // 上传 object, 建议 20M 以下的文件使用该接口
//        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, path, file);
//        // 设置 x-cos-security-token header 字段
//        ObjectMetadata objectMetadata = new ObjectMetadata();
//        objectMetadata.setSecurityToken(sessionToken);
//        putObjectRequest.setMetadata(objectMetadata);
//        String rtValue = null;
//        try {
//            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
//            // 成功：putobjectResult 会返回文件的 etag
//            String etag = putObjectResult.getETag();
//            rtValue = baseUrl + path;
//        } catch (CosServiceException e) {
//            //失败，抛出 CosServiceException
//            e.printStackTrace();
//        } catch (CosClientException e) {
//            //失败，抛出 CosClientException
//            e.printStackTrace();
//        } finally {
//            // 关闭客户端
//            cosclient.shutdown();
//            //返回文件的网络访问url
//            return rtValue;
//        }
//    }
//
//    /**
//     * 生成临时密钥
//     *
//     * @return
//     */
//    private static JSONObject getTempKey() {
//        TreeMap<String, Object> config = new TreeMap<String, Object>();
//        try {//使用永久密钥生成临时密钥
//            config.put("SecretId", secretId);
//            config.put("SecretKey", secretKey);
//            config.put("durationSeconds", Integer.parseInt(durationSeconds));
//            config.put("bucket", bucket);
//            config.put("region", region);
//            config.put("allowPrefix", allowPrefix);
//            //密钥的权限列表，其他权限列表请看
//            //https://cloud.tencent.com/document/product/436/31923
//            String[] allowActions = new String[]{
//                    // 简单上传
//                    "name/cos:PutObject",
//                    // 表单上传、小程序上传
//                    "name/cos:PostObject",
//                    // 分片上传
//                    "name/cos:InitiateMultipartUpload",
//                    "name/cos:ListMultipartUploads",
//                    "name/cos:ListParts",
//                    "name/cos:UploadPart",
//                    "name/cos:CompleteMultipartUpload"
//            };
//            config.put("allowActions", allowActions);
//            JSONObject credential = CosStsClient.getCredential(config);
//            //成功返回临时密钥信息，如下打印密钥信息
//            System.out.println(credential);
//            return credential;
//        } catch (Exception e) {
//            //失败抛出异常
//            throw new IllegalArgumentException("no valid secret !");
//        }
//    }
//}