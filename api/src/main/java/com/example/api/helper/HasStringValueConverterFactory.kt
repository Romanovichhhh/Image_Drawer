package com.example.api.helper

import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class HasStringValueConverterFactory : Converter.Factory() {
    override fun stringConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit,
    ): Converter<*, String>? = when {
        type is HasValue<*> && type.value is String -> Converter<HasValue<String>, String> {
            it.value
        }
        else -> super.stringConverter(type, annotations, retrofit)
    }
}