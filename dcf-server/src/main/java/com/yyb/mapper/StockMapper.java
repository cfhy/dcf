package com.yyb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyb.entity.IncomeEntity;
import com.yyb.entity.Stock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StockMapper extends BaseMapper<Stock> {

   List<Stock> selectByKeyword(@Param("keyword") String keyword);

   List<Stock> selectByName(@Param("stockName")String stockName);

   List<Stock> selectByIndustry(@Param("industry") String industry);
}
