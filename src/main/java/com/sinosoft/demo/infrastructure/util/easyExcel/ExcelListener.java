/*
package com.sinosoft.demo.infrastructure.util.easyExcel;

import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * @Author:王江超
 * @Date: 2019/11/11 17:59
 * @Description:
 *//*


@Slf4j
public class ExcelListener extends AnalysisEventListener {

    //自定义用于暂时存储data。可以通过实例获取该值
    public List<List<String>> datas = new ArrayList<>();


    @Override
    public void invoke(Object object, AnalysisContext context) {
        List<String> stringList = (List<String>) object;
        //数据存储到list，供批量处理，或后续自己业务逻辑处理。
        datas.add(stringList);
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // datas.clear();//解析结束销毁不用的资源
    }

    public List<List<String>> getDatas() {
        return datas;
    }

    public void setDatas(List<List<String>> datas) {
        this.datas = datas;
    }


}

*/
