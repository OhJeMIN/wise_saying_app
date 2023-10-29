package com.ll.domain.quotation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ll.base.Rq;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class QuotationController {
    Scanner scanner;
    private int lastQuotationId;
    private List<Quotation> quotations;

    public QuotationController(Scanner scanner){
        this.scanner = scanner;
        lastQuotationId = 0;
        quotations = new ArrayList<>();
    }

    public void actionWrite() {
        System.out.print("명언 : ");
        String sayingWise = scanner.nextLine();
        System.out.print("작가 : ");
        String authorName = scanner.nextLine();

        lastQuotationId++;
        int id = lastQuotationId;

        Quotation quotation = new Quotation(id, sayingWise, authorName);
        quotations.add(quotation);
        //writeFile(quotation); // 파일에 저장
        System.out.println(quotation.getId() + "번 명언이 등록되었습니다.");
    }

    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        if (quotations.isEmpty()) System.out.println("등록된 명언이 없습니다.");
        for (int i = quotations.size() - 1; i >= 0; i--) {
            Quotation quotation = quotations.get(i);
            System.out.println(quotation.getId() + " / " + quotation.getAuthorName() + " / " + quotation.getContent());
        }
    }

    void writeFile(Quotation quotation) {
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
                Type listType = new TypeToken<List<Map<String, String>>>() {
                }.getType(); //TypeToken은 Gson이 제네릭 타입을 처리할 수 있도록 도와주는 클래스
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

    void readFile() {
        try {
            Type type = new TypeToken<HashMap<String, String>>() {
            }.getType();

            Gson gson = new Gson();

            FileReader reader = new FileReader("C:/Users/Oh/IdeaProjects/mission3/data.json");
            List<Map<String, String>> list;
            Type listType = new TypeToken<List<Map<String, String>>>() {
            }.getType(); //TypeToken은 Gson이 제네릭 타입을 처리할 수 있도록 도와주는 클래스
            list = gson.fromJson(reader, listType); //SON 문자열을 Java 객체로 쉽게 변환가능
            for (Map<String, String> arr : list) {
                System.out.println(arr);
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

    public void actionRemove(Rq rq) {
        int num = rq.getParamAsInt("id", 0);
        if (num == 0) {
            System.out.println("id를 입력해주세요");
            return;
        }
        int id = findQuotationIndex(num,-1);

        if (id == -1) {
            System.out.println(num + "번 명언은 존재하지 않습니다.");
            return;
        }
        quotations.remove(id);
        System.out.println(id + 1 + "번 명언이 삭제되었습니다.");
    }

    public void actionModify(Rq rq) {

        int num = rq.getParamAsInt("id", 0);
        if (num == 0) {
            System.out.println("id를 입력해주세요");
            return;
        }
        int id = findQuotationIndex(num,-1);

        if (id == -1) {
            System.out.println(num + "번 명언은 존재하지 않습니다.");
            return;
        }
        Quotation quotation = quotations.get(id);

        System.out.println("명언(기준) : " + quotation.getContent());
        System.out.print("명언 : ");
        String sayingWise = scanner.nextLine();

        System.out.println("작가(기준) : " + quotation.getAuthorName());
        System.out.print("작가 : ");
        String authorName = scanner.nextLine();

        quotation.setContent(sayingWise);
        quotation.setAuthorName(authorName);
        System.out.println(id + 1 + "번 명언이 수정되었습니다.");

    }

    private int findQuotationIndex(int num, int defaultvalue){
        for (int i = 0; i < quotations.size(); i++) {
            Quotation quotation = quotations.get(i);
            if (num == quotation.getId()) return i;
        }
        return defaultvalue;
    }
}
