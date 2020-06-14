package com.sail.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: JavaDemo
 * @description: 日历类使用方法
 * 获得一个月以前的当前日期的前一天
 * @author: sail
 * @create: 2019/05/29 12:19
 */

public class CalendarDemo {
    public static void main(String[] args) {
        //设置日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取现在的时间
        Date date = new Date();
        //获得日历类实例
        Calendar calendar = Calendar.getInstance();
        //将当前日期放入日历中
        calendar.setTime(date);
        //对日期做操作
        calendar.add(Calendar.MONTH,-1);
        calendar.add(Calendar.DATE,-1);
        Date d = calendar.getTime();
        System.out.println("一个月前的当前日期的前一天时间为："+sdf.format(d));
        //calendar.set(Calendar.DAY_OF_WEEK,2);//设置日期为一周的第二天
        //获得当前日期在一个周时间段的索引
        int week_index = calendar.get(Calendar.DAY_OF_WEEK);
        String[] week = {"星期零","星期一","星期二","星期三","星期四","星期五","星期六","星期天"};
        //d = calendar.getTime();
        //System.out.println("这个日期所属的这一周的第二天："+sdf.format(d));
        System.out.println("这一天为："+week[week_index]);
    }

}
