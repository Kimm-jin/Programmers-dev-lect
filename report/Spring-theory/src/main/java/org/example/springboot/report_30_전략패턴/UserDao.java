package org.example.springboot.report_30_전략패턴;

public class UserDao {
    private Database db;
    UserDao(Database db){this.db=db;}

    void context(StatementStrategy strategy){
        db.open(); // 공통 - 자원 열기
        strategy.run(db);   // 변하는 부분 전략 위임
        db.close();// 공통 - 자원 정리
    }

    void deleteAll(){
//        별도
//        익명 클래스로 리팩토링
//        StatementStrategy strategy = new StatementStrategy() {
//            @Override
//            public void run(Database db) {
//                db.getUsers().clear();
//                System.out.println("[전략-익명] 전체 삭제 ");
//            }
//        };
//        context(strategy);

//        context(db -> {
//            db.getUsers().clear();
//            System.out.println("  [전략-람다] 전체 삭제 ");
//        });

        context(db->db.getUsers().clear());
    }


    void add(User user){
//        익명 클래스로 리팩토링
//        StatementStrategy strategy = new StatementStrategy(){
//            @Override
//            public void run(Database db) {
//                db.getUsers().add(user);
//                System.out.println("[전략-익명] 추가: "+user.getName());
//            }
//        };
//        context(strategy);

          context(db -> {
              db.getUsers().add(user);
              System.out.println("  [전략-람다] 추가: "+user.getName());
          });
    }

    User get(String id){
        final User res[] = new User[1];

        context( db->{
            for(User user : db.getUsers()){
                if(user.getId().equals(id)){
                    res[0]=user;
                }
            }
                });
        return res[0];
    }
}
