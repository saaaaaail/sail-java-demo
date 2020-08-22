package com.sail.leetcode.interview2020.字符串;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
 *
 *  
 *
 * 示例:
 *
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO93_复原IP地址 {

    /**
     * 2ms 94.29%
     * 普普通通的dfs 选与不选
     * 判断一层ip地址 的大小
     * 如果大于等于1小于等于255，就是合适的ip地址，可以选
     * 如果等于0 要单独判断，因为如果0出现在首位，就只能是一个数字0，不能是01这种，或者0可以跟在其他数字后比如10
     *
     *
     */
    List<String> res = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        doRestoreIpAddress(s.toCharArray(),0,4,new ArrayList<>());
        return res;
    }

    public void doRestoreIpAddress(char[] c,int index,int level,List<String> path){
        if(index==c.length&&level==0){
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<path.size();i++){
                sb.append(path.get(i));
                if(i!=path.size()-1){
                    sb.append(".");
                }
            }
            res.add(sb.toString());
            return ;
        }
        if(c.length-index>3*level||level<=0||index>=c.length){
            return ;
        }
        int len = 1;
        while(len<=3&&index+len<=c.length){
            //从index开始长度为1的数
            String s = String.valueOf(c,index,len);
            int num = Integer.parseInt(s);
            //如果这个数等于0，那0占了数字首位，后面就不能跟其他东西了，所以break直接结束后续循环
            if(num==0){
                path.add(s);
                doRestoreIpAddress(c,index+len,level-1,path);
                path.remove(path.size()-1);
                break;
            }else if(num>=1&&num<=255){
                //如果num不等于0，那就判断是不是满足1~255的，是的话可以拆分
                path.add(s);
                doRestoreIpAddress(c,index+len,level-1,path);
                path.remove(path.size()-1);
            }

            len++;
        }
    }
}
