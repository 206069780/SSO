package com.blog.sso.com.blog.sso;

final class DSZH {
    private static DSZH instance = null;
    private DSZH() {
    }
    public static DSZH getInstance() {
        if (instance == null) {
            instance = new DSZH();
        }
        return instance;
    }
}