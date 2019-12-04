import com.alibaba.druid.filter.config.ConfigTools;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class DruidUtilsTest {

    public static void main(String[] args) throws Exception {
        String [] keyPair = getPublicKeyAndPrivateKey(); // 获取公钥私钥

        String privateKey = keyPair[0]; //私钥
        String publicKey = keyPair[1];//公钥

        System.out.println("privateKey: " + privateKey);
        System.out.println("publicKey: " + publicKey);

        String passWord = "PBANK_USER";
        System.out.println("明文密码：" + passWord);

        String enPassWord = encrypt(privateKey, passWord);
        System.out.println("加密后密码： " + enPassWord);

        String dePassWord = decrypt(publicKey, enPassWord);
        System.out.println("解密后密码： " + dePassWord);
    }

    public static String [] getPublicKeyAndPrivateKey() throws NoSuchAlgorithmException, NoSuchProviderException {
        String [] keyPair = ConfigTools.genKeyPair(512);
        return keyPair;
    }

    /**
     *  私钥加密明文密码
     * @param privateKey  私钥
     * @param pwd 明文密码
     * @return 加密后密码
     * @throws Exception
     */
    public static String encrypt(String privateKey, String  pwd) throws Exception {
        String enPassword = ConfigTools.encrypt(privateKey, pwd);
        return enPassword;
    }

    /**
     * 公钥解密
     * @param publicKey 公钥
     * @param enpwd  加密后的密码
     * @return  解密后的密码
     * @throws Exception
     */
    public static String decrypt(String publicKey, String   enpwd) throws Exception {
        String password = ConfigTools.decrypt(publicKey, enpwd);
        return password;
    }
}