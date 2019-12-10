package com.redbox.mirumon.main.di.modules

import com.redbox.mirumon.main.domain.info.DeviceRepository
import com.redbox.mirumon.main.domain.info.DeviceService
import com.redbox.mirumon.main.presentation.common.overview.OverViewModel
import com.redbox.mirumon.main.presentation.common.software.SoftwareViewModel
import com.redbox.mirumon.main.presentation.device.DeviceViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val infoModule = module {
    viewModel { SoftwareViewModel(get()) }
    viewModel { OverViewModel(get()) }
    viewModel { DeviceViewModel(get()) }
    single { DeviceRepository(get()) }
    single { get<Retrofit>().create(DeviceService::class.java) }
}
