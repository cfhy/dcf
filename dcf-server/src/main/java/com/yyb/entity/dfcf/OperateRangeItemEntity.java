package com.yyb.entity.dfcf;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE)
public class OperateRangeItemEntity {
    private String BUSINESS_REVIEW;
    private String BUSINESS_SCOPE;
    private String ms;
}
