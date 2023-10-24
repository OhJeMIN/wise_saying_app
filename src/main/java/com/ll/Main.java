package com.ll;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
                readFile();
                /*for (int i = authors.size() - 1; i >= 0; i--) {
                    if (authors.get(i) != null || saying_wises.get(i) != null) {
                        System.out.println(i + 1 + "/" + authors.get(i) + "/" + saying_wises.get(i));
                    }
                }*/
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

    static void write(Integer sw_num, String author, String sayingWise) {
        /*try {
            FileWriter writer = new FileWriter("output.txt", true);
            writer.write(sw_num + "/" + author + "/" + sayingWise + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();

        }*/
        try {
            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(sw_num));
            map.put("content", sayingWise);
            map.put("author", author);
            Gson gson = new Gson();
            List<Map<String, String>> list;

            File file = new File("data.json");
            if (file.exists() && file.length() != 0) {
                FileReader reader = new FileReader(file);
                Type listType = new TypeToken<List<Map<String, String>>>(){}.getType(); //TypeToken은 Gson이 제네릭 타입을 처리할 수 있도록 도와주는 클래스
                list = gson.fromJson(reader, listType); //SON 문자열을 Java 객체로 쉽게 변환가능
                reader.close();
            } else {
                list = new ArrayList<>();
            }
            list.add(map);

            String json = gson.toJson(list);
            FileWriter writer = new FileWriter("data.json");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }


    static void readFile() {
        /*Path file = Paths.get("C:/Users/Oh/IdeaProjects/mission3/output.txt");
        try {
            List<String> lines = Files.readAllLines(file);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }*/

        try {
            Type type = new TypeToken<HashMap<String, String>>(){}.getType();

            Gson gson = new Gson();

            FileReader reader = new FileReader("C:/Users/Oh/IdeaProjects/mission3/data.json");
            List<Map<String, String>> list;
            Type listType = new TypeToken<List<Map<String, String>>>(){}.getType(); //TypeToken은 Gson이 제네릭 타입을 처리할 수 있도록 도와주는 클래스
            list = gson.fromJson(reader, listType); //SON 문자열을 Java 객체로 쉽게 변환가능
            System.out.println(list);
            reader.close();

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}

