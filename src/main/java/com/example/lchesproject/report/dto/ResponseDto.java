package com.example.lchesproject.report.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "Response")
public class ResponseDto {

    @JacksonXmlProperty(localName = "ResultCode")
    String ResultCode;

    @JacksonXmlProperty(localName = "ErrorMsg")
    String ErrorMsg;
    
    @JacksonXmlProperty(localName = "Item")
    Item item;
}
