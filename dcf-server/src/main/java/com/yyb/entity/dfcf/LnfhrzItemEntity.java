package com.yyb.entity.dfcf;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE)
public class LnfhrzItemEntity {
   private BigDecimal ALLOTMENT_NUM;//配股(万股)
   private BigDecimal IPO_NUM;//新股发行(万股)
   private String SECURITY_CODE;
   private String SECURITY_NAME_ABBR;
   private BigDecimal SEO_NUM;//增发(万股)
   private String STATISTICS_YEAR;//时间
   private BigDecimal TOTAL_DIVIDEND;//分红总额(万元)
}
