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
                for(int i =authors.size()-1;i>=0;i--){
                    if(authors.get(i) != null || saying_wises.get(i) !=null){
                        System.out.println(i+1 + "/" + authors.get(i) + "/" + saying_wises.get(i));
                    }
                }
            }
            else if(str.substring(0,2).equals("삭제")){
                int num = Integer.parseInt(str.split("=")[1])-1;
                if(authors.get(num) == null || saying_wises.get(num) == null){
                    System.out.println(num+1 +"번 명언은 존재하지 않습니다.");
                }
                else{
                    authors.set(num,null);
                    saying_wises.set(num,null);
                    System.out.println(num+1 +"번 명언이 삭제되었습니다.");
                }
            }
            System.out.print("명령) ");
            str = scanner.nextLine();
        }

    }
}