package org.example.springboot.report_26_싱글톤_심화;

interface  ConnectionMaker{
    String makeConnection();
}

class SimpleConnectionMaker implements ConnectionMaker{
    private static final SimpleConnectionMaker instance = new SimpleConnectionMaker();
    private SimpleConnectionMaker(){};
    static SimpleConnectionMaker getInstance(){return instance;}

    @Override
    public String makeConnection() {
        return "DB연결";
    }
}

public class Dao {
    private static final Dao instance = new Dao();
    private Dao(){};
    static Dao getInstance(){return instance;}

    private ConnectionMaker connectionMaker = SimpleConnectionMaker.getInstance();

    String findUser(String userId){
        return userId + " 조회 [" + connectionMaker.makeConnection() + "]";
    }
}
