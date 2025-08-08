package com.example.common.content

object PagePath {
    object AppPage {
        const val JAVA_MAIN_PAGE = "/app/module/main/page"
    }

    object ModuleJavaPage {
        const val MAIN_PAGE = "/java/module/main/page"
    }

    object ModuleKotlinPage {
        const val MAIN_PAGE = "/kotlin/module/main/page"
        const val TEST_PAGE = "/kotlin/module/test/page"
        const val FUNCTION_PAGE = "/kotlin/module/function/page"
    }

    object ModuleFunctionPage {
        const val MAIN_PAGE = "/function/module/main/page"
        const val WEATHER_PAGE = "/function/module/weather/page"
    }

    object ModuleCommonPage {
        const val TEST_PAGE = "/common/module/test/page"
        const val H5_PAGE = "/common/module/h5/page"
    }

    object ModuleMainPage {
        const val WELCOME_PAGE = "/main/module/welcome/page"
        const val HOME_PAGE = "/main/module/home/page"
        const val FLOW_PAGE = "/main/module/flow/page"
        const val LIVE_DATA_PAGE = "/main/module/liveData/page"
        const val BIND_TEST_PAGE = "/main/module/bindTest/page"
        const val COMPOSE_TEST_PAGE = "/main/module/composeTest/page"
    }
}