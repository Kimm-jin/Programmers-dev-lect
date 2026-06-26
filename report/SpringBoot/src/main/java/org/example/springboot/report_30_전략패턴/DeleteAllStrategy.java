package org.example.springboot.report_30_전략패턴;

class DeleteAllStrategy implements StatementStrategy{

    @Override
    public void run(Database db) {
        db.getUsers().clear();
        System.out.println("[전략-별도클래스] 전체 삭제 ");
    }
}
