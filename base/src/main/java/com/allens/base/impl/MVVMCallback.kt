package com.allens.base.impl


interface MVVMCallback<RE : Any, VM : Any> {

    fun createRepos(): RE

    fun createVMClass(): Class<VM>
}
