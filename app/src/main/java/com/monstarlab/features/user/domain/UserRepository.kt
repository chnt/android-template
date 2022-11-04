package com.monstarlab.features.user.domain

import com.monstarlab.core.extensions.mapSuccess
import com.monstarlab.core.persistence.Repository
import com.monstarlab.features.user.data.storage.UserPreferenceStore
import com.monstarlab.features.user.data.api.UsersApi
import com.monstarlab.features.user.data.api.dtos.toUser
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UsersApi,
    private val userPreferenceStore: UserPreferenceStore
) : Repository() {

    suspend fun getUser(): User {
        return api.getUser()
            .mapSuccess {
                it.data.toUser()
            }.also {
                userPreferenceStore.add(it)
            }
    }
}
