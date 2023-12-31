package com.hgw.officeconver;

import com.alibaba.fastjson.JSONObject;
import com.hgw.officeconver.converter.HtmlToPNGConverter;
import com.hgw.officeconver.converter.PDFToPNGConverter;
import com.hgw.officeconver.converter.PPTToPNGConverter;
import com.hgw.officeconver.converter.PPTXToPNGConverter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("local")
@SpringBootTest
class OfficeConverApplicationTests {

    @Test
    public void pptxToPNGConverterTest() {
        PPTXToPNGConverter pptxToPNGConverter = new PPTXToPNGConverter("pptx文件链接");
        List<String> urlList = pptxToPNGConverter.convertToPNG();
        System.out.println(JSONObject.toJSONString(urlList));
    }

    @Test
    public void pptToPNGConverterTest() {
        PPTToPNGConverter pptxToPNGConverter = new PPTToPNGConverter("ppt文件链接");
        List<String> urlList = pptxToPNGConverter.convertToPNG();
        System.out.println(JSONObject.toJSONString(urlList));
    }

    @Test
    public void pdfToPNGConverterTest() {
        PDFToPNGConverter pdfToPNGConverter = new PDFToPNGConverter("pdf文件链接");
        List<String> urlList = pdfToPNGConverter.convertToPNG();
        System.out.println(JSONObject.toJSONString(urlList));
    }

    @Test
    public void htmlToPNGConverterTest() {
        String html = "<p style=\"line-height: 1;\">测试内容编辑</p><p style=\"line-height: 3;\">阿萨德和i</p><p style=\"line-height: 3;\">地址：杭州市余杭区溪西八方城11幢</p><p style=\"line-height: 3;\">来个图片</p><hr><p style=\"line-height: 3;\"><img style=\"width:100%;height:auto\" src=\"https://images.location.pub/FuvjKyKF5GRxf_PfrwEux4bP232T-pms_original\" class=\"editor--\" data-custom=\"id=wux-upload--1697682266313-1\">图片右侧文案</p><p style=\"line-height: 3;\">分割线</p><p style=\"line-height: 3;\"><br></p><hr><ul data-checked=\"false\"><li style=\"line-height: 3;\">哈哈1</li><li style=\"line-height: 3;\">哈哈2</li></ul><ul data-checked=\"true\"><li style=\"line-height: 3;\">哈哈选中（能看到吗）</li></ul><hr><p style=\"line-height: 3;\" class=\"ql-indent-1\">内容缩紧速度能达到收到</p><ol><li style=\"line-height: 3;\" class=\"ql-indent-1\">阿斯顿</li><li style=\"line-height: 3;\" class=\"ql-indent-1\">d s d</li><li style=\"line-height: 3;\" class=\"ql-indent-1\">说到底</li><li style=\"line-height: 3;\">说到底</li><li style=\"line-height: 3;\">3434 </li><li style=\"line-height: 3;\">当时的</li></ol><hr><p style=\"text-align: center; line-height: 3;\"><strong style=\"background-color: yellow; color: rgb(253, 49, 54); font-size: 18px;\"><del>背景色</del></strong></p><p style=\"line-height: 3;\"><span style=\"font-size: 14px;\"><img style=\"width:100%;height:auto\" src=\"https://images.location.pub/FrV26hFjDyKMgugwbH5uuSeyu94L-pms_original\" data-custom=\"id=wux-upload--1697634247667-1\" class=\"   \"></span></p><p style=\"line-height: 3;\"><span style=\"font-size: 14px;\">\uFEFF</span></p><p style=\"line-height: 3; text-align: center;\"><strong style=\"color: rgb(246, 140, 65); font-size: 18px;\"><em><u>无语佛啊啊</u></em></strong><strong style=\"color: rgb(246, 140, 65); font-size: 14px;\"><em><u>\uFEFF</u></em></strong></p><p style=\"text-align: left;\"><br></p><p style=\"text-align: left;\">sad</p><p style=\"text-align: left;\"><br></p><p><img style=\"width:100%;height:auto\" src=\"https://images.location.pub/FsXbCDwqZHVVl1fLXBZzkWxN4L-I-pms_original\" data-custom=\"id=wux-upload--1697633627669-1\" class=\" \"></p>";
        HtmlToPNGConverter htmlToPNGConverter = new HtmlToPNGConverter(html);
        List<String> urlList = htmlToPNGConverter.convertToPNG();
        System.out.println(JSONObject.toJSONString(urlList));
    }
}
