package com.hgw.officeconver.converter;

import gui.ava.html.parser.HtmlParserImpl;
import gui.ava.html.renderer.ImageRenderer;
import gui.ava.html.renderer.ImageRendererImpl;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Description: HTML转换器
 *
 * @author LinHuiBa-YanAn
 * @date 2023/10/19 15:51
 */
@Slf4j
public class HtmlToPNGConverter extends BaseConverter{

    public HtmlToPNGConverter(String inputSource) {
        super(inputSource);
    }

    @Override
    public List<String> convertToPNG() {
        if (Objects.isNull(inputSource)) {
            throw new RuntimeException( "html内容不能为空");
        }
        HtmlParserImpl htmlParser = new HtmlParserImpl();
        htmlParser.loadHtml(inputSource);
        ImageRenderer imageRenderer = new ImageRendererImpl(htmlParser);
        BufferedImage img = imageRenderer.getBufferedImage();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, "png", bos);
        } catch (IOException e) {
            log.error("html转换图片失败,{}", e);
            throw new RuntimeException("html转换图片失败" + e.getMessage());
        }
        outPathUrlList.add(uploadFileToOss(bos));
        return outPathUrlList;
    }

}
