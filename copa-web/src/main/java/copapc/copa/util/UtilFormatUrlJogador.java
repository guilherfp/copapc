package copapc.copa.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import copapc.util.UrlUtil;

public class UtilFormatUrlJogador {

  public static void main(String[] args) throws SQLException {
    final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/copapc", "copapc", "C0p@PC");
    final PreparedStatement statSelect = connection.prepareStatement("select nome from jogador");
    final PreparedStatement statUpdate = connection.prepareStatement("update jogador set url = ? where nome = ?");
    final ResultSet rs = statSelect.executeQuery();
    while (rs.next()) {
      final String nome = rs.getString(1);
      System.out.println(nome);
      statUpdate.setString(1, UrlUtil.formatURL(nome));
      statUpdate.setString(2, nome);
      statUpdate.executeUpdate();
    }

  }

}
