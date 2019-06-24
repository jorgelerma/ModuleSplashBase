package com.globant.equattrocchio.cleanarchitecture.di.module

import dagger.Module

@Module(includes = [
    ActivityModule::class,
    ImagesContractModule::class
])
abstract class ApplicationInjectorsModule
