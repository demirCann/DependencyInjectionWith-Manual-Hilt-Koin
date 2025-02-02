package com.example.disampleproject.util

import com.example.disampleproject.util.Constants.ERROR_OCCURRED
import com.example.disampleproject.util.Constants.HTTP_ERROR
import com.example.disampleproject.util.Constants.REQUEST_LIMIT_REACHED
import com.example.disampleproject.util.Constants.RESPONSE_NULL
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

val gson = Gson()

fun <T> apiFlow(
    call: suspend () -> Response<T>?
): Flow<ApiResult<T & Any>> = flow {

    emit(ApiResult.Loading)

    try {
        val c = call()
        c?.let {
            if (c.isSuccessful) {
                val body = c.body()
                if (body != null) {
                    emit(ApiResult.Success(body))
                } else {
                    emit(ApiResult.Error(ERROR_OCCURRED))
                }
            } else {
                val errorBody = c.errorBody()?.string()
                val errorResponse = gson.fromJson(errorBody, ApiErrorResponse::class.java)
                emit(ApiResult.Error(errorResponse.error))
            }
        } ?: emit(ApiResult.Error(RESPONSE_NULL))
    } catch (e: HttpException) {
        val errorBody = e.response()?.errorBody()?.string()
        val errorMessage = when (e.code()) {
            429 -> REQUEST_LIMIT_REACHED
            else -> errorBody ?: HTTP_ERROR
        }
        emit(ApiResult.Error(errorMessage))
    } catch (e: Exception) {
        emit(ApiResult.Error(e.message ?: ERROR_OCCURRED))

    } catch (e: IOException) {
        emit(ApiResult.Error(e.message ?: ERROR_OCCURRED))
    }
}.flowOn(Dispatchers.IO)

sealed class ApiResult<out T> {
    data class Success<out R>(val data: R?) : ApiResult<R>()
    data class Error(val message: String?) : ApiResult<Nothing>()
    data object Loading : ApiResult<Nothing>()
}

data class ApiErrorResponse(
    val error: String
)