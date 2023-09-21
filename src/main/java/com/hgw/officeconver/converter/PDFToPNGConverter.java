package com.hgw.officeconver.converter;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Description: pdf转换为图片转换器
 *
 * @author YanAn
 * @date 2023/9/20 14:11
 */
@Slf4j
public class PDFToPNGConverter extends BaseConverter {

    private static final float IMAGE_SCALE = 8;

    public PDFToPNGConverter(String inputPath) {
        super(inputPath);
    }

    @Override
    public List<String> convertToPNG() {
        InputStream is = null;
        try {
            is = getInputStream(inputFileUrl);
            PDDocument document = PDDocument.load(is);
            PDFRenderer renderer = new PDFRenderer(document);
            int pageSize = document.getNumberOfPages();
            for (int i = 0; i < pageSize; i++) {
                BufferedImage img = renderer.renderImage(i, IMAGE_SCALE);
                // save the output
                try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                    javax.imageio.ImageIO.write(img, "png", bos);
                    String url = uploadFileToOss(bos);
                    outPathUrlList.add(url);
                }
            }
        } catch (Exception e) {
            log.error("pdf转换图片失败,{}", e.getMessage());
            throw new RuntimeException("pdf转换图片失败" + e.getMessage());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outPathUrlList;
    }

}
