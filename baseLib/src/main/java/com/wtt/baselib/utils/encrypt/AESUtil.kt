package com.wtt.baselib.utils.encrypt

import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.*
import javax.crypto.spec.IvParameterSpec

/**
 * @description: $
 * @author $
 * @date $ $
 * @version V1.0
 */
object AESUtil {
    private const val IV_BLOCK_SIZE = 16;


    /**
     * 加密
     * @param encryptBytes 指定的数组
     * @param encryptKey 秘钥
     */
    fun encryptAES(encryptBytes: ByteArray, encryptKey: SecretKey): ByteArray? {
        try {
            //创建密码器
            val cipher = Cipher.getInstance("AES/CBC/PKCS7PADDING")
            //用密钥初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, encryptKey)
            val final = cipher.doFinal(encryptBytes)
            // iv占前16位,加密后的数据占后面
            return cipher.iv + final
        } catch (e: NoSuchPaddingException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: InvalidAlgorithmParameterException) {
            e.printStackTrace()
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 解密
     * @param decryptBytes 解密的字符串
     * @param decryptKey 秘钥
     *
     */
    fun decryptAES(decryptBytes: ByteArray, decryptKey: SecretKey): ByteArray? {
        try {
            // 先取出IV
            val iv = decryptBytes.copyOfRange(0, IV_BLOCK_SIZE)
            // 取出加密后的数据
            val decryptData = decryptBytes.copyOfRange(IV_BLOCK_SIZE, decryptBytes.size)
            val cipher = Cipher.getInstance("AES/CBC/PKCS7PADDING")
            cipher.init(Cipher.DECRYPT_MODE, decryptKey, IvParameterSpec(iv))
            return cipher.doFinal(decryptData)
        } catch (e: NoSuchPaddingException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: InvalidAlgorithmParameterException) {
            e.printStackTrace()
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        }
        return null
    }
}