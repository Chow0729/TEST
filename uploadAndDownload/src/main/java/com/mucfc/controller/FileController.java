package com.mucfc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 测试文件上传下载
 * @author ChaoQun Zhou
 * @date 2018年2月1日
 */
@Controller  
@RequestMapping("/file")
public class FileController {  
    
    @javax.annotation.Resource(name = "configProperties")
    private Properties properties;
  
    @RequestMapping("/toFile")  
    public String toFileUpload() {  
        return "/fileUpload";  
    }  
  
    @RequestMapping("/toFile2")  
    public String toFileUpload2() {  
        return "fileUpload2";  
    }  
  
    /** 
     * 方法一上传文件 
     */  
    @RequestMapping("/onefile")  
    public String oneFileUpload(  
            @RequestParam("file") CommonsMultipartFile file,
            HttpServletRequest request, ModelMap model) {  
  
        // 获得原始文件名  
        String fileName = file.getOriginalFilename();  
        System.out.println("原始文件名:" + fileName);  
  
        // 新文件名  
        String newFileName = UUID.randomUUID() + fileName;  
  
        // 获得项目的路径  (是获取的的tamcat的路径，部署项目后相当于项目的路径)。
        /*ServletContext sc = request.getSession().getServletContext();
        System.out.println(sc + "-----sc");
        String path2 = sc.getRealPath("/img"+"/");
        System.out.println(path2+"path2");*/
        
        // 上传位置  
        String path = properties.getProperty("file_url");
        
        File f = new File(path);  
        if (!f.exists())  
            f.mkdirs();  
        if (!file.isEmpty()) {  
            try {  
                FileOutputStream fos = new FileOutputStream(path + newFileName);  
                InputStream in = file.getInputStream();  
                int b = 0;  
                while ((b = in.read()) != -1) {  
                    fos.write(b);  
                }
                fos.close();  
                in.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
  
        System.out.println("上传图片到:" + path + newFileName);  
        String newPath = properties.getProperty("virtual_url");
        
        // 保存文件地址，用于JSP页面回显  
        model.addAttribute("fileUrl", newPath +"/"+ newFileName);
        System.out.println(newPath + "/" + newFileName);
        return "fileUpload";
    }  
  
    /** 
     * 方法二上传文件，一次一张 
     */  
    @RequestMapping("/onefile2")  
    public String oneFileUpload2(HttpServletRequest request,  
            HttpServletResponse response) throws Exception {  
        CommonsMultipartResolver cmr = new CommonsMultipartResolver(  
                ((HttpSession) request).getServletContext());  
        if (cmr.isMultipart(request)) {  
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) (request);  
            Iterator<String> files = mRequest.getFileNames();  
            while (files.hasNext()) {  
                MultipartFile mFile = mRequest.getFile(files.next());  
                if (mFile != null) {  
                    String fileName = UUID.randomUUID()  
                            + mFile.getOriginalFilename();  
                    String path = "d:/upload/" + fileName;  
  
                    File localFile = new File(path);  
                    mFile.transferTo(localFile);  
                    request.setAttribute("fileUrl", path);  
                }  
            }  
        }  
        return "fileUpload";  
    }  
}  