package com.demo.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@SuppressWarnings("unchecked")
public class StringUtil {

	private static char field_delim = ',';
	private static char block_delim = '\n';
	private static Reader reader;
	private static boolean newline;

	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str))
			return true;
		return false;
	}

	public static boolean isTrimEmpty(String str) {
		if (str == null || "".equals(str))
			return true;
		if ("".equals(str.trim()))
			return true;
		return false;
	}

	public static String ltrim(String s) {
		int len = s.length();
		int st = 0;
		char[] val = s.toCharArray();

		while ((st < len) && (val[st] <= ' ')) {
			st++;
		}
		return (st > 0) ? s.substring(st, len) : s;
	}

	public static String rtrim(String s) {
		int len = s.length();
		int st = 0;
		char[] val = s.toCharArray();

		while ((st < len) && (val[len - 1] <= ' ')) {
			len--;
		}
		return (len < s.length()) ? s.substring(st, len) : s;
	}

	public static String mapToString(Map map) {
		StringBuffer buf = new StringBuffer();
		buf.append("{");

		Iterator<Entry> i = map.entrySet().iterator();
		boolean hasNext = i.hasNext();
		while (hasNext) {
			Entry e = i.next();
			Object key = e.getKey();
			Object value = e.getValue();
			if (key == map)
				buf.append("(this Map)");
			else
				buf.append(key);
			buf.append("=");
			if (value == null)
				value = "";
			if (value == map)
				buf.append("(this Map)");
			else {
				if (value instanceof Map)
					buf.append(mapToString((Map) value));
				else if (value instanceof List)
					buf.append(listToString((List) value));
				else if (fieldMaybePasswd((String) key) && !"".equals(value))
					buf.append("***");
				else
					buf.append(value);
			}
			hasNext = i.hasNext();
			if (hasNext)
				buf.append(", ");
		}

		buf.append("}");
		return buf.toString();
	}

	// �Ƿ��������ֶ�
	public static boolean fieldMaybePasswd(String key) {
		key = key.toLowerCase();
		String[] mayKeys = new String[] { "passwd", "password", "ckbkpw", "chbkpw", "tranpw", "pwd", "pin", "pswd", "acctpw", "usps", "userpw" };
		for (String mk : mayKeys) {
			if (key.contains(mk))
				return true;
		}
		if (key.endsWith("pw"))
			return true;
		return false;
	}

	public static String listToString(List list) {
		StringBuffer buf = new StringBuffer();
		buf.append("[");

		Iterator i = list.iterator();
		boolean hasNext = i.hasNext();
		while (hasNext) {
			Object o = i.next();
			if (o == list)
				buf.append("(this Collection)");
			else {
				if (o instanceof Map)
					buf.append(mapToString((Map) o));
				else if (o instanceof List)
					buf.append(listToString((List) o));
				else
					buf.append(o);
			}
			hasNext = i.hasNext();
			if (hasNext)
				buf.append(", ");
		}

		buf.append("]");
		return buf.toString();
	}

	public static String toHexTable(byte[] byteSrc) {
		return toHexTable(byteSrc, 16, 7);
	}

	private static String toHexTable(byte[] byteSrc, int lengthOfLine, int column) {
		StringBuffer hexTableBuffer = new StringBuffer(256);

		int lineCount = byteSrc.length / lengthOfLine;
		int totalLen = byteSrc.length;
		if (byteSrc.length % lengthOfLine != 0) {
			lineCount++;
		}

		for (int lineNumber = 0; lineNumber < lineCount; lineNumber++) {
			int startPos = lineNumber * lengthOfLine;

			byte[] lineByte = new byte[Math.min(lengthOfLine, totalLen - startPos)];
			System.arraycopy(byteSrc, startPos, lineByte, 0, lineByte.length);

			int columnA = column & 0x4;
			if (4 == columnA) {
				int count = 10 * lineNumber;

				String addrStr = Integer.toString(count);
				int len = addrStr.length();
				for (int i = 0; i < 8 - len; i++) {
					hexTableBuffer.append('0');
				}
				hexTableBuffer.append(addrStr);
				hexTableBuffer.append("h: ");
			}

			int columnB = column & 0x2;
			if (2 == columnB) {
				StringBuffer byteStrBuf = new StringBuffer();
				for (int i = 0; i < lineByte.length; i++) {
					String num = Integer.toHexString(lineByte[i] & 0xFF);
					if (num.length() < 2)
						byteStrBuf.append('0');
					byteStrBuf.append(num);
					byteStrBuf.append(' ');
				}

				hexTableBuffer.append(fillChar(byteStrBuf.toString(), ' ', 48, false));
				hexTableBuffer.append("  ; ");
			}

			int columnC = column & 0x1;
			if (1 == columnC) {
				for (int i = 0; i < lineByte.length; i++) {
					char c = (char) lineByte[i];

					if (c < ' ') {
						c = '^';
					} else if (c == ' ') {
						c = '.';
					} else if ((c >= '?') && (i < lineByte.length - 1)) {
						char c2 = (char) lineByte[(i + 1)];
						if (c2 >= '?') {
							String str = new String(lineByte, i, 2);
							hexTableBuffer.append(str);
							i++;
							continue;
						}
					}

					hexTableBuffer.append("");
					hexTableBuffer.append(c);
				}
			}
			if (lineNumber >= lineCount - 1)
				break;
			hexTableBuffer.append('\n');
		}
		return hexTableBuffer.toString();
	}

	public static String fillChar(String sSource, char ch, int nLen, boolean bLeft) {
		int nSrcLen = sSource.length();

		if (nSrcLen <= nLen) {
			StringBuffer buffer = new StringBuffer();
			if (bLeft) {
				for (int i = 0; i < nLen - nSrcLen; i++) {
					buffer.append(ch);
				}
				buffer.append(sSource);
			} else {
				buffer.append(sSource);
				for (int i = 0; i < nLen - nSrcLen; i++)
					buffer.append(ch);
			}
			return buffer.toString();
		}
		return sSource;
	}

	/**
	 * ��srcȡ�̶�����len���ִ����������ֽ����飬ʹ��GBK���룬����ռ�����ַ�
	 * 
	 * @param src
	 * @param len
	 * @param ch
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String fixLength(String src, char ch, int len) throws UnsupportedEncodingException {
		if (src == null)
			src = "";
		byte[] bs = src.getBytes("GBK");
		int srcLen = bs.length;
		if (srcLen == len) {
			return new String(src.getBytes("GBK"));
		} else if (srcLen < len) {
			StringBuffer bf = new StringBuffer();
			for (int i = 0; i < len - srcLen; i++) {
				bf.append(ch);
			}
			return new String((src + bf.toString()).getBytes("GBK"));
		} else {
			byte[] dest = new byte[len];
			System.arraycopy(bs, 0, dest, 0, len);
			return new String(dest);
		}
	}

	public static String[] csvToStringArray(String csv) {
		if ((csv == null) || (csv.length() == 0)) {
			return new String[0];
		}
		reader = new StringReader(csv);
		try {
			return readLine();
		} catch (IOException localIOException) {

		}
		return null;
	}

	private static String[] readLine() throws IOException {
		List<String> list = new LinkedList<String>();
		while (true) {
			String str = readField();
			if (str == null) {
				break;
			}
			list.add(str);
		}
		if (list.isEmpty()) {
			return null;
		}
		return ((String[]) list.toArray(new String[list.size()]));
	}

	private static String readField() throws IOException {
		if (newline) {
			newline = false;
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		boolean quoted = false;
		int last = -1;
		int ch = reader.read();

		if (ch == -1) {
			return null;
		}

		if (ch == 34) {
			quoted = true;
		} else {
			if (ch == block_delim) {
				return null;
			}
			if (ch == field_delim) {
				return "";
			}
			buffer.append((char) ch);
		}

		while ((ch = reader.read()) != -1) {
			if (ch == block_delim) {
				if (quoted && last == 34 || !quoted) {
					newline = true;
					break;
				}
			} else if (ch == field_delim) {
				if (quoted && last == 34 || !quoted)
					break;
			} else if ((ch == 34) && (quoted)) {
				if (last == 34) {
					last = -1;
				} else {
					last = 34;
				}

			} else {
				buffer.append((char) ch);
			}
		}
		return buffer.toString();
	}

	public static String sec2str(long second) {
		String str = "";
		String pattern = "00";
		long sec = second;
		long min = sec >= 60 ? sec / 60 : 0;
		sec = sec % 60;
		str = num2str(min, pattern) + ":" + num2str(sec, pattern);
		return str;
	}

	public static String num2str(Number n, String pattern) {
		DecimalFormat f = new DecimalFormat(pattern);
		return f.format(n);
	}

	public static String conc(List list, String seperator) {
		String str = "";
		if (list == null)
			return str;
		for (int i = 0; i < list.size(); i++) {
			if (i > 0)
				str += seperator;
			str += list.get(i);
		}
		return str;
	}

	public static String conc(String[] strs, String seperator, boolean ignoreEmptyVal) {
		if (strs == null)
			return "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strs.length; i++) {
			if (ignoreEmptyVal && StringUtil.isEmpty(strs[i]))
				continue;
			if (sb.length() > 0) {
				sb.append(seperator);
			}
			sb.append(strs[i]);
		}
		return sb.toString();
	}

	public static boolean isEmpty(Object obj) {
		if (obj == null)
			return true;
		if (!(obj instanceof String))
			return false;
		return ((String) obj).trim().length() == 0;
	}

	public static String repeat(String str, int times) {
		String r = "";
		for (int i = 0; i < times; i++)
			r += str;
		return r;
	}

	public static String asciiPaddingR(String str, int length, String padding) {
		return asciiPadding(str, length, padding, Align.LEFT);
	}

	/**
	 * @param str
	 * @param length
	 * @param padding
	 * @param align
	 * @return
	 */
	public static String asciiPadding(String str, int length, String padding, Align align) {
		String rst = Align.RIGHT.equals(align) ? asciiTrimL(str, length) : asciiTrimR(str, length);
		int alen = asciiLength(rst);
		if (alen == length)
			return rst;
		int leftp = 0;
		int rightp = 0;
		switch (align) {
		case LEFT:
			rightp = length - alen;
			break;
		case RIGHT:
			leftp = length - alen;
			break;
		default:
			leftp = (length - alen) / 2;
			rightp = length - alen - leftp;
		}
		int pl = asciiLength(padding);
		if (leftp > 0)
			rst = repeat(padding, leftp / pl) + rst;
		if (rightp > 0)
			rst = rst + repeat(padding, rightp / pl);
		return rst;

	}

	public static String asciiTrimL(String str, int length) {
		int alen = asciiLength(str);
		if (alen <= length)
			return str;
		StringBuffer result = new StringBuffer();
		alen = 0;
		for (int i = str.length() - 1; i >= 0; i--) {
			char c = str.charAt(i);
			alen += c > 127 ? 2 : 1;
			if (alen <= length)
				result.insert(0, c);
			else
				break;
		}
		return result.toString();
	}

	public static String asciiTrimR(String str, int length) {
		int alen = asciiLength(str);
		if (alen <= length)
			return str;
		String result = "";
		alen = 0;
		for (int i = 0; i < length; i++) {
			char c = str.charAt(i);
			alen += c > 127 ? 2 : 1;
			if (alen <= length)
				result += c;
			else
				break;
		}
		return result;
	}

	public static int asciiLength(CharSequence str) {
		int length = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			length += c > 127 ? 2 : 1;
		}

		return length;
	}

	public static int str2int(String s, int def) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return def;
		}
	}

	public static BigDecimal str2num(Object str) {
		return str2num(str, new BigDecimal(0));
	}

	public static BigDecimal str2num(Object o, BigDecimal defVal) {
		if (o == null || o.equals(""))
			return defVal;
		try {
			String s = "" + o;
			s = s.replaceAll("\\,", "");
			return new BigDecimal(s);
		} catch (Throwable e) {
			return defVal;
		}
	}

	public static void main(String[] args) {
		BigDecimal d = str2num("1,221.2");
		System.out.println(d);
	}

	enum Align {
		LEFT, CENTER, RIGHT
	}
}
