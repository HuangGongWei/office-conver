package com.hgw.officeconver.converter;

import org.apache.poi.hslf.usermodel.HSLFTextParagraph;
import org.apache.poi.hslf.usermodel.HSLFTextRun;
import org.apache.poi.hslf.usermodel.HSLFTextShape;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Description: 抽象ppt转换为图片转换器
 *
 * @author YanAn
 * @date 2023/9/20 14:11
 */
public abstract class AbstractPPTToPNGConverter extends BaseConverter {

    private final static double IMAGE_SCALE = 5;

    /**
     * 默认字体
     */
    private final static String DEFAULT_FONT_FAMILY = "苹方 常规";

    public AbstractPPTToPNGConverter(String inputPath) {
        super(inputPath);
    }

    /**
     * 幻灯片转换图片且上传oss
     *
     * @param pgWidth  宽
     * @param pgHeight 高
     * @param slide    幻灯片
     * @return 图片于oss文件链接
     */
    protected String toPNG(int pgWidth, int pgHeight, Slide slide) {
        // 统一字体
        List shapes = slide.getShapes();
        for (Object shape : shapes) {
            if (shape instanceof XSLFTextShape) {
                XSLFTextShape sh = (XSLFTextShape) shape;
                java.util.List<XSLFTextParagraph> textParagraphs = sh.getTextParagraphs();
                for (XSLFTextParagraph textParagraph : textParagraphs) {
                    java.util.List<XSLFTextRun> textRuns = textParagraph.getTextRuns();
                    for (XSLFTextRun textRun : textRuns) {
                        textRun.setFontFamily(DEFAULT_FONT_FAMILY);
                    }
                }
            }
            if (shape instanceof HSLFTextShape) {
                HSLFTextShape sh = (HSLFTextShape) shape;
                java.util.List<HSLFTextParagraph> textParagraphs = sh.getTextParagraphs();
                for (HSLFTextParagraph textParagraph : textParagraphs) {
                    List<HSLFTextRun> textRuns = textParagraph.getTextRuns();
                    for (HSLFTextRun textRun : textRuns) {
                        textRun.setFontFamily(DEFAULT_FONT_FAMILY);
                    }
                }
            }
        }

        int imageWidth = (int) Math.floor(IMAGE_SCALE * pgWidth);
        int imageHeight = (int) Math.floor(IMAGE_SCALE * pgHeight);

        BufferedImage img = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = img.createGraphics();
        graphics.setPaint(Color.white);
        graphics.fill(new Rectangle2D.Float(0, 0, pgWidth, pgHeight));
        graphics.scale(IMAGE_SCALE, IMAGE_SCALE);
        slide.draw(graphics);
        // save the output
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            javax.imageio.ImageIO.write(img, "png", bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return uploadFileToOss(bos);
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
