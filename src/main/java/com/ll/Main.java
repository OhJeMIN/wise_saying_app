package com.ll;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("== 명언 앱 ==");
        Scanner scanner = new Scanner(System.in);
        System.out.print("명령) ");
        String str = scanner.nextLine();
        int sw_num = 0;
        List<String> authors = new ArrayList<>();
        List<String> saying_wises = new ArrayList<>();
        while(!str.equals("종료")){
            if(str.equals("등록")) {
                sw_num ++;
                System.out.print("명언 : ");
                String saying_wise = scanner.nextLine();
                saying_wises.add(saying_wise);
                System.out.print("작가 : ");
                String author = scanner.nextLine();
                authors.add(author);
                System.out.println(sw_num+"번 명언이 등록되었습니다.");
            }
            else if(str.equals("목록")){
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for(int i =sw_num-1;i>=0;i--){
                    System.out.println(i + "/" + authors.get(i) + "/" + saying_wises.get(i));
                }
            }
            System.out.print("명령) ");
            str = scanner.nextLine();
        }

    }
}