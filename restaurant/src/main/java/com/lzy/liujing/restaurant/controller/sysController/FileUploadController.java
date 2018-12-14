package com.lzy.liujing.restaurant.controller.sysController;

import com.lzy.liujing.restaurant.Enums.ResultEnum;
import com.lzy.liujing.restaurant.entity.Result;
import com.lzy.liujing.restaurant.entity.ResultUrl;
import com.lzy.liujing.restaurant.utils.ImageUtil;
import com.lzy.liujing.restaurant.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.annotation.MultipartConfig;
import java.io.File;

@Controller
@MultipartConfig
public class FileUploadController {
    private final static Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    //图片上传基地址
    @Value("${web.upload-image-path}")
    private String location;

    @PostMapping("/upload/img.do")
    @ResponseBody
    public Result upload(@RequestParam("image") MultipartFile multipartFile)throws Exception{
        if(multipartFile!=null&&!multipartFile.isEmpty()){
            //获取文件名
            String rootFileName = multipartFile.getOriginalFilename();
            String contentType = multipartFile.getContentType();
            //打印日志
            logger.info("上传图片:name={},type={}",rootFileName,contentType);
            //储存图片路径
            String filePath = location+File.separator+"image";
            logger.info("储存图片路径：={}",filePath);
            //保存图片，并返回UUID生成的图片名
            String fileName = ImageUtil.saveImage(multipartFile,filePath);
            ResultUrl url = new ResultUrl();
            //返回给前端的访问路径
            String returnPath = "/image/"+fileName;
            url.setSrc(returnPath);
            return ResultUtil.success(url);
        }else{
            return ResultUtil.error(ResultEnum.IMAGE_UPLOAD_FAIL);
        }
    }
}
