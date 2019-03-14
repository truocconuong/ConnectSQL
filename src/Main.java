import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
// tạo database URL
            String DB_URL = "jdbc:mysql://localhost:3306/th1806e";
            String DB_USER = "root";
            String DB_PASSWORD ="";
            // connect db
            Connection conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            Statement statement = conn.createStatement();
            System.out.println("1:Đăng nhập");
            System.out.println("2:Đăng ký");
            System.out.println("3:Xuất ra danh sách");
            System.out.println("4:Xoá member");
            Scanner sc = new Scanner(System.in);
            Scanner ac = new Scanner(System.in);
            int chon=sc.nextInt();
            switch(chon){
                case 1 : {
                    System.out.println("-- Đăng nhập --");
                    System.out.println("Nhập tên tài khoản");
                    String tk = ac.nextLine();
                    System.out.println("Nhập mật khẩu");
                    String mk = ac.nextLine();
                    // truy vấn lấy user
                    String sql = "SELECT * FROM user";
                  ResultSet resultSet = statement.executeQuery(sql);
                    while(resultSet.next()){
                       String username = resultSet.getString("username");
                       String password = resultSet.getString("password");
                        if (tk.equals(username)) {
                            if (mk.equals(password)) {

                                System.out.println("Đăng nhập thành công");
                            }
                            else {
                                System.out.println("Sai mật khẩu");
                            }
                        }
                        else {
                            System.out.println("Tài khoản không tồn tại");
                        }
                    }
                    break;
                }
                case 2 : {
                    String sql = "SELECT * FROM user";
                    ResultSet resultSet = statement.executeQuery(sql);
                    System.out.println("-- Đăng ký --");
                    System.out.println("Nhập username ");
                    String username = ac.nextLine();
                    System.out.println("Nhập email ");
                    String email = ac.nextLine();
                    System.out.println("Nhập password ");
                    String password = ac.nextLine();
                    while(resultSet.next()){
                        String check = resultSet.getString("username");
                        String checkEmail = resultSet.getString("email");
                        if (username.equals(check)) {
                            System.out.println("Tên tài khoản này đã tồn tại trong hệ thống");
                            return;
                        }
                        if (email.equals(checkEmail)) {
                            System.out.println("Tên tài khoản này đã tồn tại trong hệ thống");
                            return;
                        }
                    }
                    String addStudent ="insert into user (username,email,password) values('"+username+"',"+email+","+password+")";
                    statement.executeUpdate(addStudent);
                    System.out.println("Thêm thành công");
                    break;
                }
                case 3 : {
                    String sql = "SELECT username FROM user";
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    System.out.println(resultSet.getString("username"));
                }
                    break;
                }
                case 4 : {
                    System.out.println("Mời bạn nhập ID người muốn xoá");
                    int id = sc.nextInt();
                    System.out.println("Bạn muốn xoá hẳn hay distable tạm thời tài khoản này (Chọn 1 hoặc 2)");
                    int check = sc.nextInt();
                    if(check == 1){
                        System.out.println("Bạn chọn xoá hẳn Oce đợi tí");
                        String sql = "DELETE FROM user WHERE id="+id;
                        statement.executeUpdate(sql);
                        System.out.println("Xoá thành công");
                    }
                    if(check == 2){
                        System.out.println("Bạn chọn distable tài khoản này");
                        String sql ="UPDATE user SET status=0 WHERE id="+id;
                        statement.executeUpdate(sql);
                        System.out.println("Distable thành công");

                    }
                }


            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
