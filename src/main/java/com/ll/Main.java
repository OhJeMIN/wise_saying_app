package com.ll;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("== 명언 앱 ==");
        Scanner scanner = new Scanner(System.in);
        System.out.print("명령) ");
        String str = scanner.nextLine();
        int sw_num = 0;
        while(!str.equals("종료")){
            if(str.equals("등록")) {
                sw_num ++;
                System.out.print("명언 : ");
                String saying_wise = scanner.nextLine();
                System.out.print("작가 : ");
                String author = scanner.nextLine();
                System.out.println(sw_num+"번 명언이 등록되었습니다.");
                System.out.print("명령) ");
                str = scanner.nextLine();
            }
        }

    }
}