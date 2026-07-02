package org.example.springboot.report_30_전략패턴;


@FunctionalInterface
interface StatementStrategy{
    void run(Database db);
}
