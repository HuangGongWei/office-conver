package com.hgw.officeconver.converter;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Description: pptx转换为图片转换器
 *
 * @author YanAn
 * @date 2023/9/21 13:35
 */
@Slf4j
public class PPTXToPNGConverter extends AbstractPPTToPNGConverter {

    public PPTXToPNGConverter(String inputPath) {
        super(inputPath);
    }

    @Override
    public List<String> convertToPNG() {
        InputStream is = null;
        XMLSlideShow ppt = null;
        try {
            is = getInputStream(inputFileUrl);
            ppt = new XMLSlideShow(is);
            Dimension pgSize = ppt.getPageSize();
            for (XSLFSlide slide : ppt.getSlides()) {
                String url = toPNG(pgSize.width, pgSize.height, slide);
                outPathUrlList.add(url);
            }
        } catch (IOException e) {
            log.error("pptx转换图片失败,{}", e.getMessage());
            throw new RuntimeException("pptx转换图片失败" + e.getMessage());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (ppt != null) {
                    ppt.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outPathUrlList;
    }
}
