package com.yyb.entity.dfcf;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE)
public class DateItemEntity {
    private String SECURITY_CODE;
    private String REPORT_DATE;
    private String REPORT_TYPE;
    private String REPORT_DATE_NAME;
}
