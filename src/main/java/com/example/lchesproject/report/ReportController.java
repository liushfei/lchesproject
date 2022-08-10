package com.example.lchesproject.report;

import com.example.lchesproject.report.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Component
@EnableScheduling
@RestController
public class ReportController {
    @Autowired
    ReportService reportService;

    @RequestMapping(value = "/post/physical/hsfile", method = RequestMethod.POST,produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseDto getFileHesuan(String ReportId){
        return reportService.queryHsFileByExamNo(ReportId);
    }


    @RequestMapping(value = "/post/physical/test", method = RequestMethod.POST,produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseDto test(String ReportId){
        return reportService.test(ReportId);
    }

}
