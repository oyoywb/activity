package com.oywb.weixin.activities.util;

import org.springframework.core.io.Resource;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.RSAPublicKeySpec;

/**
 * 从密钥库文件读取RSA密钥对
 *
 * @author sai
 * @since 2021/1/10
 */
public final class KeyStoreKeyFactory {

    private final Resource resource;

    private final char[] password;

    private KeyStore store;

    private final Object lock = new Object();

    public KeyStoreKeyFactory(Resource resource, char[] password) {
        this.resource = resource;
        this.password = password;
    }

    public KeyPair getKeyPair(String alias) {
        return getKeyPair(alias, password);
    }

    public KeyPair getKeyPair(String alias, char[] password) {
        try {
            synchronized (lock) {
                if (store == null) {
                    synchronized (lock) {
                        store = KeyStore.getInstance("jks");
                        store.load(resource.getInputStream(), this.password);
                    }
                }
            }
            RSAPrivateCrtKey key = (RSAPrivateCrtKey) store.getKey(alias, password);
            RSAPublicKeySpec spec = new RSAPublicKeySpec(key.getModulus(), key.getPublicExponent());
            PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(spec);
            return new KeyPair(publicKey, key);
        } catch (Exception e) {
            throw new IllegalStateException("不能从密钥库文件读取RSA密钥对: " + resource, e);
        }
    }

}
