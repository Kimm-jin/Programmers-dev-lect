import java.sql.*;

public class A_jdbc {
    // 연결 담당
    public Connection connection(){
        // 기본포트
        String url = "jdbc:mysql://localhost:3306/java_basic";
        String user = "root";
        String password = "gksmfdmf!2#";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.printf("Conn Success");

            return connection;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertData(String name, int age, String phone){
        // sql indection 조사
        String query = "INSERT INTO member (name, age, phone) VALUES (?, ?, ?)";

        try(
                Connection conn = connection();
                PreparedStatement pstmt = conn.prepareStatement(query)
        ){
            pstmt.setString(1,name);
            pstmt.setInt(2,age);
            pstmt.setString(3,phone);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //  '*'<< 성능상으로도 좋지않고 남이 봤을때 또 까서 봐야하기 떄문에 명시하여 가독성을 높이는게 좋다.
    public void selectAll(){
        String query = "SELECT id, name, age, phone FROM member";
        try(
                Connection conn = connection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ){

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()){ // next가 null일때 까지
                int id = resultSet.getInt("id");
                String name = resultSet.getNString("name");
                int age = resultSet.getInt("age");
                String phone = resultSet.getNString("phone");

                System.out.println(id + " "+name+" "+age+" "+phone);
                System.out.println("==========");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectOne(int id){
        String query = "SELECT id, name, age, phone FROM member WHERE id = ?";
        try(
                Connection conn = connection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ){
            pstmt.setInt(1,id); // primary_key 는 유일한 값
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                int id2 = resultSet.getInt("id");
                String name = resultSet.getNString("name");
                int age = resultSet.getInt("age");
                String phone = resultSet.getNString("phone");

                System.out.println( id2 + " : " + name + " : " + age + " : " + phone );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateData(int id, String name, int age, String phone){
        // PK에 맞는 정보면 수정
        String query = "UPDATE member SET name = ?, age = ?, phone = ? WHERE id = ?";
        try(
                Connection conn = connection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ){

            pstmt.setString(1,name);
            pstmt.setInt(2,age);
            pstmt.setString(3,phone);
            pstmt.setInt(4,id);

            int result = pstmt.executeUpdate();
            if(result>0){
                System.out.println("update success!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteData(int id){
        String query = "DELETE FROM member WHERE id = ?";
        // 아래 주석을 쓰면 SQL로 인식한다.
        // language=SQL


        try(
                Connection conn = connection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ){

            pstmt.setInt(1,id);
            int result = pstmt.executeUpdate(); // 삭제도 업데이트
            if(result>0){
                System.out.println("delete success!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        A_jdbc ajdbc = new A_jdbc();
//        ajdbc.connection();
        //ajdbc.insertData("홍길순",21,"010-3333-4444");
        ajdbc.selectAll();
        //ajdbc.selectOne(7);
        //ajdbc.updateData(7, "홍홍홍",30,"010-1111-2121");
        ajdbc.selectAll();
        //ajdbc.deleteData(7);
    }
}
