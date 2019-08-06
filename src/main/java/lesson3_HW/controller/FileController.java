package lesson3_HW.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lesson3_HW.AppException.BadRequestException;
import lesson3_HW.beans.File;
import lesson3_HW.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by oleg on 01.08.2019.
 */
@Controller
public class FileController {
    FileService fileService;


    @Autowired
    FileController(FileService fileService) {
        this.fileService = fileService;
    }

    /*
    {"name":"File-1,
    "format":"JPG",
    "size":"20"
    "storage":"260"}
     */
    @RequestMapping(method = RequestMethod.POST, value = "/saveFile", produces = "text/plain")
    @ResponseBody
    public String save(HttpServletRequest request) {
        File currentFile = null;
        File file = null;
        try {
            currentFile = fromJson(request);
            file = fileService.save(currentFile);
        } catch (IOException e) {
            return "File not saved. Can not convert from JSON";
        } catch (BadRequestException e) {
            return "File not saved. " + e.toString();
        }
        return "File " + file.toString() + " saved in DB";
    }

    /*
    {"id":"260",
     "name":"File-1,
    "format":"JPG",
    "size":"20"
    "storage":"260"}
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/updateFile", produces = "text/plain")
    @ResponseBody
    public String update(HttpServletRequest request) {
        File currentFile = null;
        File file = null;
        try {
            currentFile = fromJson(request);
            file = fileService.update(currentFile);
        } catch (IOException e) {
            return "File not updated. Can not convert from JSON";
        } catch (BadRequestException e) {
            return "File not updated. " + e.toString();
        }
        return "File " + currentFile.toString() + " updated in DB";
    }


    //  http://localhost:8080/deleteFile?id=258
    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteFile", produces = "text/plain")
    @ResponseBody
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        Long id;
        try {
            id = Long.parseLong(request.getParameter("id"));
            fileService.delete(id);
        } catch (BadRequestException e) {
            return "File not deleted. " + e.toString();
        }
        return "File with id " + id + " delete from DB";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllFiles", produces = "text/plain")
    @ResponseBody
    public String getAll(HttpServletRequest request) {
        return fileService.gerAllFiles();
    }

    private File fromJson(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        ObjectMapper objectMapper = new ObjectMapper();
        String input;

        BufferedReader reader = request.getReader();
        while ((input = reader.readLine()) != null) {
            sb.append(input);
        }
        return objectMapper.readValue(sb.toString(), File.class);
    }

}
