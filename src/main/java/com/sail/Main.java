package com.sail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

//class模版在此

/**
 *@program: ${PROJECT_NAME}
 *@description: ${description}
 *@author: sail
 *@create: ${YEAR}/${MONTH}/${DAY} ${HOUR}:${MINUTE}
 */

public class Main {

    public static void main(String[] args) {
	// write your code here
        int count=1;
        InputStream inputStream = System.in;
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(reader);

        boolean f = true;
        while(f){
            try {
                String str = br.readLine();
                System.out.println(str);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        MyThread myThread = new MyThread(count);
        Queue<Integer> q = new ArrayDeque();
        q.add(1);
        int ii = q.remove();
        Queue<Integer> queue = new PriorityQueue<>();
        Set set = new HashSet();
        set.size();
        set.contains(1);
        set.add(1);
        Map map = new HashMap();
        map.size();
        List list = new ArrayList(set);
    }

}
