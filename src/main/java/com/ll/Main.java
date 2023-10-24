package com.ll;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("== 명언 앱 ==");
        Scanner scanner = new Scanner(System.in);
        System.out.print("명령) ");
        String str = scanner.nextLine();
        int sw_num = 0;
        List<String> authors = new ArrayList<>();
        List<String> saying_wises = new ArrayList<>();
        while (!str.equals("종료")) {
            if (str.equals("등록")) {
                sw_num++;
                System.out.print("명언 : ");
                String sayingWise = scanner.nextLine();
                saying_wises.add(sayingWise);
                System.out.print("작가 : ");
                String author = scanner.nextLine();
                authors.add(author);
                write(sw_num, author, sayingWise); // 파일에 저장
                System.out.println(sw_num + "번 명언이 등록되었습니다.");
            } else if (str.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (int i = authors.size() - 1; i >= 0; i--) {
                    if (authors.get(i) != null || saying_wises.get(i) != null) {
                        System.out.println(i + 1 + "/" + authors.get(i) + "/" + saying_wises.get(i));
                    }
                }
            } else if (str.substring(0, 2).equals("삭제")) {
                int num = Integer.parseInt(str.split("=")[1]) - 1;
                if (authors.get(num) == null || saying_wises.get(num) == null) {
                    System.out.println(num + 1 + "번 명언은 존재하지 않습니다.");
                } else {
                    authors.set(num, null);
                    saying_wises.set(num, null);
                    System.out.println(num + 1 + "번 명언이 삭제되었습니다.");
                }
            } else if (str.substring(0, 2).equals("수정")) {
                int num = Integer.parseInt(str.split("=")[1]) - 1;
                System.out.println("명언(기준) : " + saying_wises.get(num));
                System.out.print("명언 : ");
                String sayingWise = scanner.nextLine();
                saying_wises.set(num, sayingWise);
                System.out.println("작가(기준) : " + authors.get(num));
                System.out.print("작가 : ");
                String author = scanner.nextLine();
                authors.set(num, author);
            }
            System.out.print("명령) ");
            str = scanner.nextLine();
        }
    }

    static void write(Integer sw_num, String author, String sayingWise){
        try {
            PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
            writer.println(sw_num + "/" + author + "/" + sayingWise);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}

