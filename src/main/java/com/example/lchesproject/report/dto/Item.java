package com.example.lchesproject.report.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "Item")
public class Item {
    @JacksonXmlProperty(localName = "ReportId")
    private String ReportId;


    @JacksonXmlProperty(localName = "ReportData")
    private String ReportData;

    @JacksonXmlProperty(localName = "Status")
    private String Status;


    @JacksonXmlProperty(localName = "reportType")
    private String reportType;
    @JacksonXmlProperty(localName = "Type")
    private String Type;
    @JacksonXmlProperty(localName = "JYDate")
    private String JYDate;
    @JacksonXmlProperty(localName = "Doctor")
    private String Doctor;
}
