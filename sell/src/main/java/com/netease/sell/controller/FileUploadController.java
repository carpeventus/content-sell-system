package com.netease.sell.controller;

import com.netease.sell.enums.ResultEnum;
import com.netease.sell.exception.SellException;
import com.netease.sell.util.KeyUtil;
import com.netease.sell.util.ResultVOUtil;
import com.netease.sell.vo.ResultVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Haiyu
 * @date 2019/1/27 22:08
 */
@Controller
public class FileUploadController {
    @Value("${spring.servlet.multipart.location}")
    private String location;
    @Value("${custom.static.url-path}")
    private String urlPath;

    @PostMapping("/upload")
    @ResponseBody
    public ResultVO upload(@RequestParam("file") MultipartFile file) {
        System.out.println(file);
        if (file.isEmpty()) {
            throw new SellException(ResultEnum.UPLOAD_NO_FILE_ERROR);
        }
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String randomFileName = KeyUtil.genUniqueKey() + suffix;
        File dest = new File(location + randomFileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultVOUtil.success("上传成功",urlPath + randomFileName);
    }

}
