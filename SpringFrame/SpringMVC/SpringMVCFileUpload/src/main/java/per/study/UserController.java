package per.study;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/fileUploadServer")
    public String fileUploadServer(MultipartFile upload) throws IOException {
        String fileServer = "http://localhost:9090/fileUploadServer/uploads/";

        String filename = upload.getOriginalFilename();

        // 创建客户端对象
        Client client = new Client();

        // 和服务器进行连接
        WebResource resource = client.resource(fileServer + filename);

        // 上传文件
        resource.put(upload.getBytes());
        return "success";
    }


    /**
     * fileUploadSpringMVC
     * @param request
     * @param upload
     * @return
     * @throws IOException
     */
    @RequestMapping("/fileUploadSpringMVC")
    public String fileUploadSpringMVC(HttpServletRequest request, MultipartFile upload) throws IOException {
        System.out.println("fileUploadSpringMVC");

        /*使用fileupload组件完成文件上传*/
        /*上传位置*/
        String path = request.getSession().getServletContext().getRealPath("/uploads");
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        String filename = upload.getOriginalFilename();
        upload.transferTo(new File(path, filename));
        return "success";
    }

    /**
     * 文件上传
     * @return
     */
    @RequestMapping("/fileUploadServlet")
    public String fileUploadServlet(HttpServletRequest request) throws Exception {
        System.out.println("fileUploadServlet");

        /*使用fileupload组件完成文件上传*/
        /*上传位置*/
        String path = request.getSession().getServletContext().getRealPath("/uploads");
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        /*解析request对象，获取上传文件项*/
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(request);

        /*遍历*/
        for (FileItem item:items) {
            if (item.isFormField()) { /* 普通表单项 */

            } else { /* 上传文件项 */
                String filename = item.getName();
                item.write(new File(path, filename));
                item.delete(); /*删除临时文件*/
            }
        }

        return "success";
    }

}
