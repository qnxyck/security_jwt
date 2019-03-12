package com.ck.securityjwt.common.utils

import java.sql.Timestamp
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong

/**
 * 时钟 原子类型
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/8
 */
class SystemClock private constructor(private val period: Long) {

    // 获取原子Long类型
    private val now: AtomicLong = AtomicLong(System.currentTimeMillis())

    init {
        scheduleClockUpdating()
    }

    /**
     * 定时任务线程
     */
    private fun scheduleClockUpdating() {
        val scheduler = Executors.newSingleThreadScheduledExecutor {
            val thread = Thread(it, "System Clock")
            thread.isDaemon = true
            thread
        }
        // 定时执行
        scheduler.scheduleAtFixedRate({ now.set(System.currentTimeMillis()) }, period, period, TimeUnit.MILLISECONDS)
    }

    /**
     * 获取当前时间
     */
    private fun currentTimeMillis(): Long {
        return now.get()
    }

    /**
     * 实现单例
     */
    private object InstanceHolder {
        // 设置每毫秒执行一次
        val INSTANCE = SystemClock(1)
    }

    companion object {

        /**
         * 获取当前时间戳
         */
        fun now(): Long {
            return InstanceHolder.INSTANCE.currentTimeMillis()
        }

        /**
         * 当前时间 yyyy-MM-dd HH:mm:ss SSS
         */
        fun nowDate(): String {
            return Timestamp(InstanceHolder.INSTANCE.currentTimeMillis()).toString()
        }
    }
}