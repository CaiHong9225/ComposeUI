package com.wtt.baselib.utils.encrypt

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.annotation.RequiresApi
import java.security.KeyStore
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

/**
 * @description: $
 * @author $
 * @date $ $
 * @version V1.0
 */
object KeyStoreUtil {
    const val KEY_ALIAS: String = "myKey"

    /**
     * 生成一个秘钥生成器
     * KeyGenerator最终在生成Key的时候，会直接生成在KeyStore里
     * @param alias 秘钥别名
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun getKeyGenerator(alias: String): KeyGenerator {
        // 第一个参数指定加密算法,第二个参数指定Provider
        val keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore")
        val parameterSpec = KeyGenParameterSpec.Builder(
            alias,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT  //用于加密和解密
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_CBC)  // AEC_CBC
            .setUserAuthenticationRequired(false)   // 是否需要用户认证
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)  //AES算法的PADDING, 和前面的AESUtil里保持统一
            .build()
        keyGenerator.init(parameterSpec)
        return keyGenerator
    }

    /**
     *
     * 从KeyStore中拿到秘钥键值
     * @param alias 秘钥别名
     */
    fun getKeyFromKeyStore(alias: String): SecretKey? {
        // 参数为Provider
        val keyStore = KeyStore.getInstance("AndroidKeyStore")
        // 一定要先初始化
        keyStore.load(null)
        // 获取KeyStore中的所有Key的别名
        val aliases = keyStore.aliases()
        // KeyStore里没有key
        if (!aliases.hasMoreElements()) {
            return null
        }
        // Key的保护参数,这里为不需要密码
        val protParam: KeyStore.ProtectionParameter =
            KeyStore.PasswordProtection(null)
        // 通过别名获取Key
        val entry = keyStore.getEntry(alias, protParam) as KeyStore.SecretKeyEntry
        return entry.secretKey
    }
}