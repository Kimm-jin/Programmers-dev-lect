package org.example.springboot.report_30_전략패턴;

public class Main {
    static void main() {
       Database db= new Database();
       UserDao dao = new UserDao(db);

        dao.deleteAll();
        dao.add(new User("user1","김"));
        dao.add(new User("user2","이"));

        for(User u : db.getUsers()){
            System.out.println("id : "+u.getId() + " | name : "+u.getName());
        }

        System.out.println(dao.get("user1").toString());

        // 별도, 익명, 람다
        // delete는 캡처할 값이 없어 람다가 깔끔하다.
    }
}
