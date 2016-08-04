package copapc.shared;

import org.apache.commons.lang3.Validate;

/**
 * @author Guilherme Pacheco
 */
public class UrlUtil {

  /**
   * Formata nome para URLs.
   * @param nome Nome a ser formatado.
   * @return URL formatada.
   */
  public static String formatURL(final String nome) {
    String format = removerCaracteresEspeciais(nome);
    format = removerEspacos(format);
    format = format.replaceAll(" ", "-");
    return format.toLowerCase();
  }

  /**
   * Remove caracteres especiais.
   * @param string {@link String} a ser tratada.
   * @return {@link String} tratada.
   */
  public static String removerCaracteresEspeciais(String string) {
    Validate.notNull("String inválida");
    String temp = string;
    temp = temp.replaceAll("[ÂÀÁÄÃ]", "A");
    temp = temp.replaceAll("[âãàáä]", "a");
    temp = temp.replaceAll("[ÊÈÉË]", "E");
    temp = temp.replaceAll("[êèéë]", "e");
    temp = temp.replaceAll("[ÎÍÌÏ]", "I");
    temp = temp.replaceAll("[îíìï]", "i");
    temp = temp.replaceAll("[ÔÕÒÓÖ]", "O");
    temp = temp.replaceAll("[ôõòóö]", "o");
    temp = temp.replaceAll("[ÛÙÚÜ]", "U");
    temp = temp.replaceAll("[ûúùü]", "u");
    temp = temp.replaceAll("Ç", "C");
    temp = temp.replaceAll("ç", "c");
    temp = temp.replaceAll("[ýÿ]", "y");
    temp = temp.replaceAll("Ý", "Y");
    temp = temp.replaceAll("ñ", "n");
    temp = temp.replaceAll("Ñ", "N");
    temp = temp.replaceAll("[-+=*&;%$#@?!~´`ªº°¹²³£¢¬]", "");
    temp = temp.replaceAll("['\"]", "");
    temp = temp.replaceAll("[<>()\\{\\}]", "");
    temp = temp.replaceAll("['\\\\.,()|/]", "");
    temp = temp.replaceAll("[\\^]", "");
    return temp;
  }

  /**
   * Método remove excessos de espaços em branco.
   * @param string a ser tratada.
   * @return string formatada.
   */
  public static String removerEspacos(String string) {
    Validate.notNull(string, "String inválida");
    return string.trim().replaceAll("\\s+", " ");
  }

}
