package com.natiqhaciyef.domain.domain.base

open class BaseUseCase<T: BaseRepository>(
    protected val repository: T
)
