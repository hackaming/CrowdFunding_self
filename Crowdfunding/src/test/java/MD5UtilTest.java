import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.crowdfunding.sjtu.utility.MD5Util;

public class MD5UtilTest {
	public static void main(String args[]) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		String password = "hackaming";
		System.out.println("The string before encrypt is:" + password);
		System.out.println("The string after MD5 encrypt is:" + MD5Util.MD5Encrypt(password));
	}

}
