package report_23_SOLID_5원칙;

import java.util.*;

public class Srp {
    class Journal {
        private ArrayList<String> entries = new ArrayList<>();
        void add(String text) { entries.add(text); }

        String getText(){
            String ans="";
            for(String s : entries)ans+=s+"\n";
            return ans;
        }
    }

    // 출력, 저장
    class JournalSaver{
        void print(Journal j){
            System.out.println(j.getText());
        }
//        void saveToFile();
    }
}
