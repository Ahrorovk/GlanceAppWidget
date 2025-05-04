package com.ahrorovk.myapplication.core

object Constants {
    const val IS_IN_TEST_MODE = false
    const val OWNER_USER = "OWNER_USER"
    const val USER = "USER"
    const val OWNER_BOT = "OWNER_BOT"
    const val CHAT_ID_ARG = "CHAT_ID_ARG"
    const val BOT_CATEGORY_ID_ARG = "BOT_CATEGORY_ID_ARG"
    const val LANGUAGE_ID_ARG = "LANGUAGE_ID_ARG"
    const val BOT_ID_ARG = "BOT_ID_ARG"
    const val BASE_URL = "https://ask-me-anything-telegram-bot.onrender.com"
    val COINS_BY_DEFAULT = if (IS_IN_TEST_MODE) 99999 else 10

    private const val INTERSTITIAL_AD_UNIT_TEST = "ca-app-pub-3940256099942544/1033173712"
    private const val REWARDED_VIDEO_AD_UNIT_TEST = "ca-app-pub-3940256099942544/5224354917"
    private const val BANNER_AD_UNIT_TEST = "ca-app-pub-3940256099942544/6300978111"

    val BANNER_AD_UNIT =
        if (IS_IN_TEST_MODE) BANNER_AD_UNIT_TEST else "ca-app-pub-4517302532425246/9137864046"
    val INTERSTITIAL_AD_UNIT =
        if (IS_IN_TEST_MODE) INTERSTITIAL_AD_UNIT_TEST else "ca-app-pub-4517302532425246/3599145647"
    val REWARDED_VIDEO_AD_UNIT =
        if (IS_IN_TEST_MODE) REWARDED_VIDEO_AD_UNIT_TEST else "ca-app-pub-4517302532425246/2094492284"
    const val ACTIVITY_EXTRAS_KEY = "ACTIVITY_EXTRAS_KEY"
}