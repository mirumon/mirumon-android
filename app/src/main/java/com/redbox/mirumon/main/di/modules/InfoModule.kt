package com.redbox.mirumon.main.di.modules

import com.redbox.mirumon.main.domain.info.InfoRepository
import com.redbox.mirumon.main.domain.info.InfoService
import com.redbox.mirumon.main.presentation.common.software.SoftwareViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val infoModule = module {
    viewModel { SoftwareViewModel(get()) }
    single { InfoRepository(get()) }
    single { get<Retrofit>().create(InfoService::class.java) }
}
