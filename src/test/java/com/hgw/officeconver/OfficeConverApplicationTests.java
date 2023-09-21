package com.hgw.officeconver;

import com.alibaba.fastjson.JSONObject;
import com.hgw.officeconver.converter.PDFToPNGConverter;
import com.hgw.officeconver.converter.PPTToPNGConverter;
import com.hgw.officeconver.converter.PPTXToPNGConverter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
}
