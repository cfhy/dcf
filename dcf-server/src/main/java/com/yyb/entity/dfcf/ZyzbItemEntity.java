package com.yyb.entity.dfcf;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE)
public class ZyzbItemEntity {
   private String REPORT_DATE;//报告期
   private BigDecimal ROEJQ;//ROE(加权)
}
