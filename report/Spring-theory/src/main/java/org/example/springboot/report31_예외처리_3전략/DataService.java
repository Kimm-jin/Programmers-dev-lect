package org.example.springboot.report31_예외처리_3전략;

import java.sql.SQLException;

public class DataService {
    private final FileLogger fileLogger;

    public DataService(FileLogger fileLogger) {
        this.fileLogger = fileLogger;
    }

    String fetchWithRetry(FlakyService flaky) {
        int maxRetry = 3;
        for (int i = 1; i <=maxRetry; i++) {
            try {
                String res = flaky.fetch();
                fileLogger.log("INFO", i + "번째 시도 성공 " + res);
                return res;
            } catch (SQLException e) {
                fileLogger.log("WARN", i + "번째 시도 실패 " + e.getMessage());
            }
        }
        fileLogger.log("ERROR", "재시도 " + maxRetry + "회 모두 실패");
        throw new RuntimeException("재시도 " + maxRetry + "회 모두 실패했습니다.");
    }

    void avoidByThrows(FlakyService f) throws SQLException {
        f.fetch();
    }

    void avoidByRethrow(FlakyService f) throws SQLException {
        try {
            f.fetch();
        } catch (SQLException e) {
            fileLogger.log("WARN", "회피 : 여기서 처리하지 않고 호출자에게 넘김 - " + e.getMessage());
            throw e;
        }
    }

    static class DuplicateUserIdException extends RuntimeException {
        DuplicateUserIdException(String id, Throwable cause) {
            super("이미 존재하는 아이디 : " + id, cause);
        }
    }

    void registerUser(String id) {
        try {
            insertUser(id);
        } catch (SQLException e) {
            if ("23000".equals(e.getSQLState())) {
                fileLogger.log("ERROR", "아이디 중복 : " + id);
                throw new DuplicateUserIdException(id, e);
            }
            fileLogger.log("ERROR", "회원 저장 중 DB 오류 " + id);
            throw new RuntimeException("회원 저장 중 DB 오류", e);
        }
    }

    void insertUser(String id) throws SQLException {
        throw new SQLException("Duplicate entry", "23000");
    }
}
