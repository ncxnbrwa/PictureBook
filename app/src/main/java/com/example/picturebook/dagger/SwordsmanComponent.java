package com.example.picturebook.dagger;

import dagger.Component;

/**
 * Created by ncx on 2021/6/15
 */
@Component(modules = SwordsmanModule.class)
interface SwordsmanComponent {
    SwordsMan getSwordsMan();
}
