package com.example.lchesproject.report;

import com.example.lchesproject.report.dto.Item;
import com.example.lchesproject.report.dto.ResponseDto;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
@Slf4j
@Service
public class ReportService {



    @Value("${fileAddress.address}")
    private String address;

    @Value("${fileAddress.domain}")
    private String domain;

    @Value("${fileAddress.username}")
    private String username;
    @Value("${fileAddress.password}")
    private String password;

    public ResponseDto queryHsFileByExamNo(String ReportId) {
        ResponseDto responseDto = new ResponseDto();
        Item item = new Item();
        item.setReportId(ReportId);
        responseDto.setResultCode("-1");
        log.info("调用核酸文件接口启动,ReportId:"+ReportId);
        String temp = null;
        try {
            log.info("调用文件库地址开始:"+address);
            long startTime = new Date().getTime();
            NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(domain, username, password);
            //NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("smb://172.17.0.50","dr", "dr@123456");
            SmbFile smbFile = new SmbFile(address, auth);
            smbFile.connect();
            long endTime = new Date().getTime();
            long time = endTime-startTime;
            log.info("获取文件库访问权限,开始转换pdf为base64.创建连接用时::"+time);
            if(smbFile.isDirectory()){
                //SmbFile[] files = smbFile.listFiles();
                log.info("获取文件路径："+smbFile.getPath()+ReportId+".pdf");
                SmbFile file = new SmbFile(smbFile.getPath()+ReportId+".pdf",auth);
                try{
                    byte[] bytes = readFile(file);
                    String encode = Base64.encode(bytes);
                    responseDto.setResultCode("0");
                    item.setStatus("1");
                    item.setReportData(encode);
                }catch (NullPointerException e){
                    responseDto.setErrorMsg("读取文件路径不对");
                }catch (IOException e){
                    responseDto.setErrorMsg("读取文件路径不对");
                    log.error("读取文件路径不对"+e);
                }catch (Exception e){
                    responseDto.setErrorMsg("系统错误");
                }
                /*for (SmbFile file : files) {
                    String fileName = file.getName();
                    try{
                        String[] idInfo = fileName.split("\\.");
                        temp = idInfo[0];
                    }catch (Exception e){
                        responseDto.setErrorMsg("文件解析异常");
                        log.error("文件解析异常");
                    }
                    log.info("开始匹配：：："+temp);
                    if(ReportId.equals(temp)){

                    }

                }*/
            }
            log.info("文件处理完毕,返回处理信息");
            responseDto.setItem(item);
        }catch (Exception e){
            responseDto.setItem(item);
            responseDto.setErrorMsg("系统错误");
            log.error("解密文件夹失败:::"+e);
            return responseDto;
        }
        return responseDto;
    }


    public byte[] readFile(SmbFile file)throws Exception{
        long len = file.length();
        byte[] bytes = new byte[(int)len];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new SmbFileInputStream(file));
        int r = bufferedInputStream.read(bytes);
        if(r != len){
            throw new IOException("读取文件不正确");
        }
        bufferedInputStream.close();
        return bytes;
    }

    public ResponseDto test(String reportId) {
        ResponseDto responseDto = new ResponseDto();
        Item item = new Item();
        item.setReportId(reportId);
        item.setJYDate("111");
        responseDto.setItem(item);
        return responseDto;
    }
}
