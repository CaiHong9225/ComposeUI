package com.wtt.baselib.utils.encrypt

import android.os.Build
import androidx.annotation.RequiresApi
import com.wtt.baselib.utils.encrypt.KeyStoreUtil.KEY_ALIAS
import com.wtt.baselib.utils.toByteArray

/**
 * @description: $
 * @author $
 * @date $ $
 * @version V1.0
 */
class KeyStoreTest {

    @RequiresApi(Build.VERSION_CODES.M)
    fun encrypt(data: String) {
        val generateKey = KeyStoreUtil.getKeyGenerator(KEY_ALIAS).generateKey()
        val encryptAES = AESUtil.encryptAES(data.toByteArray(), generateKey)

        //然后能解密回来 sourceData = data
        val sourceData = encryptAES?.apply {
            decrypt(this)
        }
    }

    fun decrypt(encryptData: ByteArray) {
        val decryptKey = KeyStoreUtil.getKeyFromKeyStore(KEY_ALIAS)
        // 解密数据
        val decryptAES = decryptKey?.let { AESUtil.decryptAES(encryptData, it) }
    }
}