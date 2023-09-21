package com.hgw.officeconver.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * Description: OSS工具类
 *
 * @author YanAn
 * @date 2023/9/20 17:48
 */
@Service
public class OSSUtil {
    @Value(value = "${oss.endpoint}")
    private String endpoint;
    @Value(value = "${oss.accessKeyId}")
    private String accessKeyId;
    @Value(value = "${oss.accessKeySecret}")
    private String accessKeySecret;
    @Value(value = "${oss.bucketName}")
    private String bucketName;

    /**
     * 文件上传
     * @param inputStream 输入
     * @param fileUrl 文件名链接
     * @return 文件链接
     */
    public String upload(InputStream inputStream, String fileUrl) {
        OSS ossClient = null;
        try {
            // 1. 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            if (!ossClient.doesBucketExist(bucketName)) {
                // 若 bucket 不存在，则动态创建一个
                ossClient.createBucket(bucketName);
                // 设置oss实例的访问权限：公共读
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            ossClient.putObject(bucketName, fileUrl, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                // 关闭 ossClient
                ossClient.shutdown();
            }
        }
        return "https://" + bucketName + "." + "oss-cn-hangzhou.aliyuncs.com/" + fileUrl;
    }
}
