package com.microservice.profilemicroservice.util

import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class File {

    fun store(file: MultipartFile): ByteArray{
        return file.bytes;
    }
}