package org.esia.specialinviter.internal

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

inline val <reified T> T.logger: Logger
    get() = LogManager.getLogger(T::class.java)
