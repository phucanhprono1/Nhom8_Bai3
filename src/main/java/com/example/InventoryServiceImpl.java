package com.example;

import javax.jws.WebService;

// Defining the web service interface
import java.sql.*;

@WebService(endpointInterface = "com.example.InventoryService")
public class InventoryServiceImpl implements InventoryService {
    private Connection conn;
    @Override
    public String checkInventory(String productId, int productQuantity) {
        String result = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment", "root", "root");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM inventory WHERE item_id = ?");
            stmt.setString(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int inStock = rs.getInt("quantity");
                if (productQuantity <= inStock) {
                    result = String.format("Sản phẩm %s có đủ hàng trong kho!", productId);
                } else {
                    result = String.format("Sản phẩm %s chỉ còn %d sản phẩm trong kho.", productId, inStock);
                }
            } else {
                result = String.format("Không tìm thấy mã sản phẩm %s trong kho.", productId);
            }

            rs.close();
            stmt.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }


}