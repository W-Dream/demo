/*
package com.sinosoft.demo.infrastructure.util.easyExcel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.*;
import java.util.List;

*/
/**
 * @Author:王江超
 * @Date: 2019/11/11 18:10
 * @Description: Excel读取工具类
 *//*

@SuppressWarnings("all")
public class ExcelReadUtil {

    private static InputStream getInputStream(String fileName) {
        try {
            return new FileInputStream(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<List<String>> readExcel(String path) {
        InputStream inputStream = getInputStream(path);
        try {
            inputStream = getInputStream(path);
            // 解析每行结果在listener中处理
            ExcelListener listener = new ExcelListener();
            ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, listener);
            excelReader.read();
            List<List<String>> datas = listener.getDatas();
            datas.remove(0);  //把表头去掉，如果需要表头的话，可以把这句话删除掉
            return datas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static List<List<String>> readExcel(InputStream inputStream) {
        try {
            // 解析每行结果在listener中处理
            ExcelListener listener = new ExcelListener();
            ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, listener);
            excelReader.read();
            List<List<String>> datas = listener.getDatas();
            datas.remove(0);  //把表头去掉，如果需要表头的话，可以把这句话删除掉
            return datas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    */
/**
     * @param inputStream
     * @desc: 校验用户上传文件（excel）是否是项目所提供模板
     *//*

    public static List<List<String>> readExcelForIn(InputStream inputStream) {
        try {
            // 解析每行结果在listener中处理
            ExcelListener listener = new ExcelListener();
            ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, listener);
            excelReader.read();
            List<List<String>> datas = listener.getDatas();
            //把表头去掉，如果需要表头的话，可以把这句话删除掉
            return datas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
*/
