package com.example.activitymanager.Services;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public abstract class LoginModule {

    @Binds
    public abstract LoginService bindLoginService(
            LoginServiceSqlLite loginImplementation);
}
