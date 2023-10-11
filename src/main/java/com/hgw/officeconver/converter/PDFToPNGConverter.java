package com.hgw.officeconver.converter;

import com.hgw.officeconver.thread.BizThreadPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Description: pdf转换为图片转换器
 *
 * @author YanAn
 * @date 2023/9/20 14:11
 */
@Slf4j
public class PDFToPNGConverter extends BaseConverter {

    private static final float IMAGE_SCALE = 1;

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
            List<CompletableFuture<String>> completableFutures = new ArrayList<>();
            for (int i = 0; i < pageSize; i++) {
                int index = i;
                completableFutures.add(BizThreadPool.supplyAsync(() -> toPNG(renderer, index)));
            }
            CompletableFuture<Void> completableFuture = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0]));
            completableFuture.get();
            for (CompletableFuture<String> future : completableFutures) {
                outPathUrlList.add(future.get());
            }
        } catch (Exception e) {
            log.error("pdf转换图片失败,{}", e);
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


    /**
     * 每页转换图片且上传oss
     *
     * @param renderer PDF渲染器
     * @param index    下标
     * @return 图片于oss文件链接
     */
    private String toPNG(PDFRenderer renderer, Integer index) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            BufferedImage img = renderer.renderImage(index, IMAGE_SCALE);
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
