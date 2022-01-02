package com.yyb.entity.dfcf;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.util.List;

@Data
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE)
public class StockWrapEntity {
   private String AllCount;
   private String AtPage;
   private String ErrMsg;
   private String PageCount;
   private String PageSize;
   private List<String> Results;
   private String TimeOut;
   private String UpdateTime;
}
