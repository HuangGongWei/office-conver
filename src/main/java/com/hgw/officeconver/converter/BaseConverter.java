package com.hgw.officeconver.converter;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.hgw.officeconver.util.OSSUtil;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Description: 基础转换器
 *
 * @author YanAn
 * @date 2023/9/20 14:12
 */
@Slf4j
public abstract class BaseConverter {

    /**
     * 输入源
     */
    protected String inputSource;

    /**
     * 输出源
     */
    protected List<String> outPathUrlList;

    public BaseConverter(String inputSource) {
        this.inputSource = inputSource;
        outPathUrlList = new ArrayList<>();
    }

    /**
     * 转换至PNG
     * @return PNG图片集合
     */
    public abstract List<String> convertToPNG();

    /**
     * 下载文件并返回流
     *
     * @param url 文件链接
     * @return InputStream
     */
    protected InputStream getInputStream(String url) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request req = new Request.Builder().url(url).build();
            Response resp = client.newCall(req).execute();
            ResponseBody body;
            if (resp.isSuccessful() && (body = resp.body()) != null) {
                return body.byteStream();
            } else {
                return null;
            }
        } catch (IOException e) {
            log.error("下载资源失败,{}", e);
            throw new RuntimeException("加载资源失败" + e.getMessage());
        }
    }

    /**
     * 上传文件至Oss
     * @param bos ByteArrayOutputStream 文件流
     * @return 文件于七牛云的存储路径
     */
    protected String uploadFileToOss(ByteArrayOutputStream bos) {
        OSSUtil service = SpringUtil.getBean(OSSUtil.class);
        String key = LocalDateTimeUtil.format(LocalDate.now(), DatePattern.PURE_DATETIME_MS_PATTERN) +  UUID.randomUUID().toString() + ".png";
        return service.upload(new ByteArrayInputStream(bos.toByteArray()), key);
    }
}
