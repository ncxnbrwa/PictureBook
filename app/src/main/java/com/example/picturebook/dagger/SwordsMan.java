package com.example.picturebook.dagger;

import javax.inject.Inject;

/**
 * Created by ncx on 2021/6/15
 */
class SwordsMan {
    @Inject
    public SwordsMan() {
    }

    public String fighting() {
        return "天下熙熙皆为利来,天下攘攘皆为利往";
    }
}
