package com.ll;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("== 명언 앱 ==");
        Scanner scanner = new Scanner(System.in);
        int sw_num = 0;
        List<String> authors = new ArrayList<>();
        List<String> saying_wises = new ArrayList<>();

        Quotation quotation;

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();
            Rq rq = new Rq(cmd);
            if(rq.getAction().equals("종료")) return;
            else if (rq.getAction().equals("등록")) {
                sw_num++;
                System.out.print("명언 : ");
                String sayingWise = scanner.nextLine();
                saying_wises.add(sayingWise);
                System.out.print("작가 : ");
                String authorName = scanner.nextLine();
                authors.add(authorName);

                quotation = new Quotation(sw_num,sayingWise, authorName);
                write(quotation); // 파일에 저장
                System.out.println(sw_num + "번 명언이 등록되었습니다.");
            } else if (rq.getAction().equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                readFile();

            } else if (rq.getAction().equals("삭제")) {
                int num = rq.getParamAsInt(rq.getAction(),0);
                if (authors.get(num) == null || saying_wises.get(num) == null) {
                    System.out.println(num + 1 + "번 명언은 존재하지 않습니다.");
                } else {
                    authors.set(num, null);
                    saying_wises.set(num, null);
                    System.out.println(num + 1 + "번 명언이 삭제되었습니다.");
                }
            } else if (rq.getAction().equals("수정")) {
                int num = rq.getParamAsInt(rq.getAction(),0);
                if(num == 0){
                    System.out.println("id가 없습니다.");
                    return;
                }
                System.out.println("명언(기준) : " + saying_wises.get(num));
                System.out.print("명언 : ");
                String sayingWise = scanner.nextLine();
                saying_wises.set(num, sayingWise);
                System.out.println("작가(기준) : " + authors.get(num));
                System.out.print("작가 : ");
                String author = scanner.nextLine();
                authors.set(num, author);
            }
        }
    }

    static void write(Quotation quotation) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(quotation.getId()));
            map.put("content", quotation.getContent());
            map.put("author", quotation.getAuthorName());
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
        try {
            Type type = new TypeToken<HashMap<String, String>>(){}.getType();

            Gson gson = new Gson();

            FileReader reader = new FileReader("C:/Users/Oh/IdeaProjects/mission3/data.json");
            List<Map<String, String>> list;
            Type listType = new TypeToken<List<Map<String, String>>>(){}.getType(); //TypeToken은 Gson이 제네릭 타입을 처리할 수 있도록 도와주는 클래스
            list = gson.fromJson(reader, listType); //SON 문자열을 Java 객체로 쉽게 변환가능
            for(Map<String, String> arr : list){
                System.out.println(arr);
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}

