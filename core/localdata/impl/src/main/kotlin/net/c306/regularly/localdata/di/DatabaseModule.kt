package net.c306.regularly.localdata.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import net.c306.regularly.localdata.TaskDatabase

@Module
@InstallIn(SingletonComponent::class)
internal interface DatabaseModule {
    companion object {
        @Provides
        @Singleton
        fun provideDatabase(applicationContext: Context): TaskDatabase = Room.databaseBuilder(
            context = applicationContext,
            klass = TaskDatabase::class.java,
            name = "database-name",
        )
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }
}