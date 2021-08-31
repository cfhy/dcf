package com.yyb.entity.dfcf;

import lombok.Data;

import java.util.List;

@Data
public class OperateRangeEntity {
    private List<OperateRangeItemEntity> zyfw;
    private List<OperateRangeItemEntity>jyps;
}
