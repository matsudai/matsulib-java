//          Copyright 2018 matsudai
// Distributed under the Boost Software License, Version 1.0.
//    (See accompanying file LICENSE_1_0.txt or copy at
//          https://www.boost.org/LICENSE_1_0.txt)

package matsulib;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * 次のクラス間の相互変換を行う。<br>
 * <ul>
 * 	<li>java.sql.Date</li>
 * 	<li>java.time.LocalDate</li>
 * 	<li>java.lang.String</li>
 * </ul>
 * 
 * <pre>{@code
 * 	// サンプルコード
 * 	
 * 	String str1 = "2018-04-05";
 * 	String str2 = "2018/04/05";
 * 	java.sql.Date sd1 = java.sql.Date.valueOf("2018-06-03");
 * 	
 * 	// String -> LocalDate
 * 	LocalDate ld1 = LocalDateConverter.from(str1).toLocalDate();
 * 	LocalDate ld2 = LocalDateConverter.from(str2, "uuuu/MM/dd").toLocalDate();
 * 	LocalDate ld3 = LocalDateConverter.from(str1).toString("uuuu年MM月dd日 (E)");
 * 	
 * 	// String -> SQLDate
 * 	java.sql.Date sd2 = LocalDateConverter.from(str1).toLocalDate();
 * 	
 * 	// SQLDate -> String
 * 	String str3 = LocalDateConverter.from(sd1).toString();
 * 	
 * 	// SQLDate -> LocalDate
 * 	LocalDate ld4 = LocalDateConverter.from(sd1).toLocalDate();
 * }</pre>
 * 
 * @since 2018/04/18
 * @author matsudai
 *
 */
public class LocalDateConverter {

	/**
	 * 入力されたLocalDateクラスのインスタンスをインスタンス変数として持つ。
	 * コンストラクタは直接呼べない。
	 * fromメソッドを通して、変換器としてインスタンスを生成する。
	 * 
	 * @param localDate 日付。
	 */
	private LocalDateConverter(LocalDate localDate) {
		this.localDate = localDate;
	}

	private LocalDate localDate;
	
	/**
	 * 文字列で表される日付を内部に保持する、このクラスのインスタンスを生成する。
	 * 
	 * @param localDate : String 文字列で表される日付。
	 * @return このクラスのインスタンス。
	 * @throws DateTimeParseException 文字列を日付に変換できなかった場合。
	 */
	public static LocalDateConverter from(String localDate)
			throws DateTimeParseException {
		return new LocalDateConverter(LocalDate.parse(localDate));
	}
	
	/**
	 * 指定されたフォーマットによる文字列で表される日付を内部に保持する、このクラスのインスタンスを生成する。
	 * 
	 * @param localDate : String 文字列で表される日付。
	 * @param formatter : DateTimeFormatter 入力する文字列の、日付としてのフォーマット。
	 * @return このクラスのインスタンス。
	 * @throws DateTimeParseException 文字列を日付に変換できなかった場合。
	 */
	public static LocalDateConverter from(String localDate, DateTimeFormatter formatter)
			throws DateTimeParseException {
		return new LocalDateConverter(LocalDate.parse(localDate, formatter));
	}
	
	/**
	 * 指定されたフォーマットによる文字列で表される日付を内部に保持する、このクラスのインスタンスを生成する。
	 * 
	 * @param localDate : String 文字列で表される日付。
	 * @param formatPattern : String 文字列の、日付としてのフォーマット。
	 * @return このクラスのインスタンス。
	 * @throws DateTimeParseException 文字列を日付に変換できなかった場合。
	 * @throws IllegalArgumentException 日付のフォーマット指定が間違っていた場合。
	 */
	public static LocalDateConverter from(String localDate, String formatPattern)
			throws DateTimeParseException, IllegalArgumentException {
		return LocalDateConverter.from(localDate, DateTimeFormatter.ofPattern(formatPattern));
	}
	
	/**
	 * 引数にとった日付を内部に保持する、このクラスのインスタンスを生成する。
	 * 
	 * @param localDate : LocalDate 日付。
	 * @return このクラスのインスタンス。
	 */
	public static LocalDateConverter from(LocalDate localDate) {
		return new LocalDateConverter(localDate);
	}
	
	/**
	 * 引数にとった日付を内部に保持する、このクラスのインスタンスを生成する。
	 * 
	 * @param date : Date SQLで提供される日付型。
	 * @return このクラスのインスタンス。
	 */
	public static LocalDateConverter from(java.sql.Date date) {
		return new LocalDateConverter(date.toLocalDate());
	}
	
	/**
	 * 内部に保持している日付を文字列として返す。
	 * 
	 * @return 日付を文字列にしたもの。
	 */
	public String toString() {
		return this.localDate.toString();
	}
	
	/**
	 * 内部に保持している日付を文字列として返す。
	 * 
	 * @param formatter DateTimeFormatter 返す文字列の、日付としてのフォーマット。
	 * @return 指定したフォーマットで文字列を返す。
	 * @throws DateTimeException 指定したフォーマットで、日付を文字列に変換できなかった場合。
	 */
	public String toString(DateTimeFormatter formatter)
			throws DateTimeException {
		return formatter.format(this.localDate);
	}
	
	/**
	 * 内部に保持している日付を文字列として返す。
	 * 
	 * @param formatPattern String 返す文字列の、日付としてのフォーマット。
	 * @return 指定したフォーマットで文字列を返す。
	 * @throws DateTimeException 指定したフォーマットで、日付を文字列に変換できなかった場合。
	 * @throws IllegalArgumentException 日付のフォーマット指定が間違っていた場合。
	 */
	public String toString(String formatPattern)
			throws DateTimeException, IllegalArgumentException {
		return this.toString(DateTimeFormatter.ofPattern(formatPattern));
	}
	
	/**
	 * 内部に保持している日付をLocalDateクラスのインスタンスとして返す。
	 * 
	 * @return LocalDateクラスのインスタンス。
	 */
	public LocalDate toLocalDate() {
		return this.localDate;
	}
	
	/**
	 * 内部に保持している日付をSQLで提供される日付型として返す。
	 * @return java.sql.Dateクラスのインスタンス。
	 */
	public java.sql.Date toSQLDate(){
		return java.sql.Date.valueOf(this.localDate);
	}
}
