package com.tian.spring.spel;

import com.google.common.collect.Maps;
import com.tian.spring.spel.model.DishDetail;
import com.tian.spring.spel.model.ShopInfo;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.HashMap;

public class PracticeMainTest {


    public static void main(String[] args) {


        ExpressionParser parser = new SpelExpressionParser();

//        Expression expression = parser.parseExpression("#brandId");

        StandardEvaluationContext ctx = new StandardEvaluationContext();

        ShopInfo shopInfo = new ShopInfo(42342L, "shopName", 1111L, "brandName");

        HashMap<String, String> dataMap = Maps.newHashMap();
        dataMap.put("val", "{brandid,asdad}");


        shopInfo.setMapData(dataMap);
        shopInfo.setDishDetail(new DishDetail("=====dishDetail====="));

        ctx.setVariable("shopInfo", shopInfo);
        ctx.setVariable("map", dataMap);

        Long value = parser.parseExpression("#shopInfo.brandId").getValue(ctx, Long.class);
        String value1 = parser.parseExpression("#shopInfo.mapData[val]").getValue(ctx, String.class);
        String value3 = parser.parseExpression("#shopInfo.dishDetail.dishName").getValue(ctx, String.class);
        String value2 = parser.parseExpression("#map[val]").getValue(ctx, String.class);


        System.out.println(value);
        System.out.println(value1);
        System.out.println(value2);
        System.out.println(value3);

    }


}
