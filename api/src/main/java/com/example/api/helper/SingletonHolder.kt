package com.example.api.helper

open class SingletonHolder<in A, out T : Any>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator

    @Volatile
    private var instance: T? = null

    /**
     * Get singleton instance, initialize if doesn't exists
     * @param arg argument to initialize singleton with
     * @return singleton instance, initializing with arg if necessary
     */
    fun getInstance(arg: A): T = instance
        ?: synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }

    /**
     * Initialize singleton with arg
     * @param arg argument to initialize singleton with
     */
    fun init(arg: A) {
        getInstance(arg)
    }

    /**
     * Get instance if available
     * @return singleton instance if available, null otherwise
     */
    fun getInstanceOrNull(): T? = instance

    /**
     * Get instance of crash with exception
     * @return singleton instance if available, throws IllegalArgumentException otherwise
     * @throws IllegalArgumentException if singleton wasn't initailized
     */
    @Throws(IllegalArgumentException::class)
    fun getInstanceOrFail(): T = requireNotNull(instance) {
        "Singleton ${this::class.qualifiedName} wasn't properly initialized"
    }
}